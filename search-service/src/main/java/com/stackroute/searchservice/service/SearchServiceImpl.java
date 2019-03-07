package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.*;
import com.stackroute.searchservice.exceptions.ConceptNotFoundException;
import com.stackroute.searchservice.model.Comment;
import com.stackroute.searchservice.model.QuestionDTO;
import com.stackroute.searchservice.repository.SearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import com.stackroute.searchservice.model.Answer;


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

    @Override
    public List<Question> questionOfTopic(String topic, String question) throws ConceptNotFoundException {
        return null;
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
                System.out.println("inside if statement");
                System.out.println(topicDoc.getQuestions().get(i));
//            flag=++flag;
//            flag=1;
                //checking if question exists in data if yes update it
                if (topicDoc.getQuestions().get(i).getQuestion().equalsIgnoreCase(question.getQuestion())) {
                    System.out.println(topicDoc.getQuestions().get(i).getQuestion().equalsIgnoreCase(question.getQuestion()));
                    System.out.println("I am in IF");
                    System.out.println("print true here");
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
                    System.out.println("record is updated in DB");
                }
            }

            //adding data to mongodb

            if (flag != 1) {
                System.out.println("in else");
                ques.setDownvote(question.getDownvote());
                ques.setTimestamp(question.getTimestamp());
                ques.setComments(question.getComments());
                ques.setAnswers(question.getAnswers());
                ques.setUpvotes(question.getUpvotes());
                ques.setDescription(question.getDescription());
                ques.setQuestion(question.getQuestion());
                ques.setUser(question.getUser());
                System.out.println("getting question " + ques);
                topicDoc.getQuestions().add(ques);
                searchRepository.save(topicDoc);
                System.out.println("new data saved");
            }
        });
        return "success";
    }

}