package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.*;
import com.stackroute.searchservice.exceptions.ConceptNotFoundException;
import com.stackroute.searchservice.model.Comment;
import com.stackroute.searchservice.model.QuestionDTO;
import com.stackroute.searchservice.repository.SearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    private SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    //Overrided method for saving searched questions//
    @Override
    public void saveTopic(Topic topic) {
        searchRepository.save(topic);
    }

    //Overrided method for getAllTopics//
    @Override
    public List<Topic> getAllTopics() {
        return searchRepository.findAll();
    }

    //Overrided method for getQuestionsByTopic//
    @Override
    public List<Topic> getQuestionsByTopic(String topic) {
        return searchRepository.findByTopic(topic);
    }

    //Overrided method for getQuestion//

    @Override
    public List<Topic> getQuestion(List<String> topic) throws ConceptNotFoundException {
        return topic.stream().flatMap(t -> searchRepository.findByTopic(t).stream()).collect(Collectors.toList());
    }




       /*
   method to get the search result in appropriate way
    */

    @Override
    public List<Question> getQuestionInside(List<String> topic, String question) throws ConceptNotFoundException{
        List<Topic> foundQuestion = topic.stream().flatMap(t -> searchRepository.findByTopic(t).stream()).collect(Collectors.toList());
        log.info("question found... "+foundQuestion.get(0));


        //dictionary

        Map<String, Float> dictionary = new HashMap<String, Float>();
        List<Question> resultList = new ArrayList<>();


//        System.out.println("question found... "+foundQuestion.get(1).getQuestions().get(i).getQuestion());
        for(int i=0;i<foundQuestion.get(0).getQuestions().size();i++) {
            log.info("question found... "+foundQuestion.get(0).getQuestions().get(i).getQuestion());

//            System.out.println("size of question found.. "+foundQuestion.size());

            log.info("user input "+question);
            int distFromLevenshtein = getLevenshteinDistance(foundQuestion.get(0).getQuestions().get(i).getQuestion(), question);
//

            log.info("distance param   "+distFromLevenshtein);
//
//            //getting size of question in db and from user

            int lengthOne = foundQuestion.get(0).getQuestions().get(i).getQuestion().length();
            log.info("db question length "+lengthOne);
            int lengthTwo = question.length();
            log.info("user question length "+lengthTwo);
            float m;
            float formula;


            //comparing the lengths

            if(lengthOne >= lengthTwo){
                m = lengthOne;
                log.info("size of question db: "+m);
            }
            else {
                m = lengthTwo;
                log.info("size of question from user "+m);
            }



            formula = (1-((float)distFromLevenshtein/m));
            if(formula >= 0.25){
                dictionary.put(foundQuestion.get(0).getQuestions().get(i).getQuestion(),formula);

            }
        }

        //reversing the values in dict

        LinkedHashMap<String, Float> reverseSortedMap = new LinkedHashMap<>();
        dictionary.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        log.info("reverse string: "+reverseSortedMap);
        log.info("Reverse Sorted Dict  : " + reverseSortedMap.keySet());

        //returing
        reverseSortedMap.keySet().stream().forEach(key -> {
            for(int i=0;i<foundQuestion.get(0).getQuestions().size();i++) {
                if (foundQuestion.get(0).getQuestions().get(i).getQuestion().equalsIgnoreCase(key)) {
                    System.out.println("in if.. "+foundQuestion.get(0).getQuestions().get(i));
                    resultList.add(foundQuestion.get(0).getQuestions().get(i));
                }
            }
        });

        log.info("in result list  "+resultList);

        return resultList;
    }



    @Override
    public String questionOfPost(QuestionDTO questionDTO) {
        List<String> topicList = questionDTO.getTopics();
        topicList.replaceAll(String::toLowerCase);
        questionDTO.getTopics().forEach(topic -> {
            Question question = new Question();
            question.setQuestion(questionDTO.getQuestion());
            question.setDescription(questionDTO.getDescription());
            question.setUpvotes(questionDTO.getUpvotes());
            question.setQuestionId(questionDTO.getQuestionId());

            question.setTimestamp(questionDTO.getTimestamp());
            question.setDownvote(questionDTO.getDownvotes());
            List<Comment> commentList = questionDTO.getComment();
            List<Comments> commentsList = new ArrayList<>();
            if (commentList != null) {
                for (Comment comment : commentList) {
                    List<Replies> replies = new ArrayList<>();
                    for (com.stackroute.searchservice.model.Replies reply : comment.getReplies()) {
                        User user = new User(reply.getUser().getEmailaddress(), reply.getUser().getFirstname(), reply.getUser().getImageurl());
                        Replies replies1 = new Replies(reply.getReply(), reply.getLikes(), reply.getTimestamp(), user);
                        replies.add(replies1);
                    }
                    User user = new User(questionDTO.getUser().getEmailaddress(), questionDTO.getUser().getFirstname(), questionDTO.getUser().getImageurl());
                    Comments comments = new Comments(comment.getComment(), comment.getTimestamp(), comment.getLikes(), replies, user);
                    commentsList.add(comments);
                }
            }
            question.setComments(commentsList);
            List<Answer> answerList = new ArrayList<>();
            if (questionDTO.getAnswer() != null) {
                for (com.stackroute.searchservice.model.Answer answer : questionDTO.getAnswer()) {

                    List<Comments> answerCommentsList = new ArrayList<>();
                    if (commentList != null) {
                        for (Comment comment : commentList) {
                            List<Replies> replies = new ArrayList<>();
                            for (com.stackroute.searchservice.model.Replies reply : comment.getReplies()) {
                                User user = new User(reply.getUser().getEmailaddress(), reply.getUser().getFirstname(), reply.getUser().getImageurl());
                                Replies replies1 = new Replies(reply.getReply(), reply.getLikes(), reply.getTimestamp(), user);
                                replies.add(replies1);
                            }
                            User user = new User(questionDTO.getUser().getEmailaddress(), questionDTO.getUser().getFirstname(), questionDTO.getUser().getImageurl());
                            Comments comments = new Comments(comment.getComment(), comment.getTimestamp(), comment.getLikes(), replies, user);
                            answerCommentsList.add(comments);
                        }
                    }
                    User userAnswer = new User(answer.getUser().getEmailaddress(), answer.getUser().getFirstname(), answer.getUser().getImageurl());
                    Answer answerDomain = new Answer(answer.getAnswer(), answer.isAccepted(), answerCommentsList, answer.getUpvotes(), answer.getViews(), answer.getTimestamp(), userAnswer);
                    answerList.add(answerDomain);
                }
            }
            question.setAnswers(answerList);
            User user = new User(questionDTO.getUser().getEmailaddress(), questionDTO.getUser().getFirstname(), questionDTO.getUser().getImageurl());
            question.setUser(user);
            Question ques = new Question();
            int flag = 0;
            Topic topicDoc = searchRepository.findById(topic).orElse(new Topic());
            for (int i = 0; i < topicDoc.getQuestions().size(); i++) {
                log.info("inside if statement");
                System.out.println(topicDoc.getQuestions().get(i));

                //checking if question exists in data if yes update it
                if (topicDoc.getQuestions().get(i).getQuestion().equalsIgnoreCase(question.getQuestion())) {
                    System.out.println(topicDoc.getQuestions().get(i).getQuestion().equalsIgnoreCase(question.getQuestion()));
                    log.info("I am in IF");
                    log.info("print true here");
                    topicDoc.getQuestions().get(i).setQuestionId(question.getQuestionId());
                    topicDoc.getQuestions().get(i).setDownvote(question.getDownvote());
                    topicDoc.getQuestions().get(i).setTimestamp(question.getTimestamp());
                    topicDoc.getQuestions().get(i).setComments(question.getComments());
                    topicDoc.getQuestions().get(i).setAnswers(question.getAnswers());
                    topicDoc.getQuestions().get(i).setUpvotes(question.getUpvotes());
                    topicDoc.getQuestions().get(i).setDescription(question.getDescription());
                    topicDoc.getQuestions().get(i).setQuestion(question.getQuestion());
                    topicDoc.getQuestions().get(i).setUser(question.getUser());
                    searchRepository.save(topicDoc);
                    flag = 1;
                    log.info("record is updated in DB");
                }
            }

            //adding data to mongodb

            if (flag != 1) {
                log.info("in else");
                ques.setQuestionId((question.getQuestionId()));
                ques.setDownvote(question.getDownvote());
                ques.setTimestamp(question.getTimestamp());
                ques.setComments(question.getComments());
                ques.setAnswers(question.getAnswers());
                ques.setUpvotes(question.getUpvotes());
                ques.setDescription(question.getDescription());
                ques.setQuestion(question.getQuestion());
                ques.setUser(question.getUser());
                log.info("getting question " + ques);
                topicDoc.getQuestions().add(ques);
                searchRepository.save(topicDoc);
                log.info("new data saved");
            }
        });
        return "success";
    }

    // LevenshteinDistance algorithm


    public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        int n = s.length(); // length of s
        int m = t.length(); // length of t

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        if (n > m) {
            // swap the input strings to consume less memory
            CharSequence tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }

        int p[] = new int[n + 1]; //'previous' cost array, horizontally
        int d[] = new int[n + 1]; // cost array, horizontally
        int _d[]; //placeholder to assist in swapping p and d

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char t_j; // jth character of t
        int cost; // cost

        for (i = 0; i <= n; i++) {
            p[i] = i;
        }

        for (j = 1; j <= m; j++) {
            t_j = t.charAt(j - 1);
            d[0] = j;

            for (i = 1; i <= n; i++) {
                cost = s.charAt(i - 1) == t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }


}