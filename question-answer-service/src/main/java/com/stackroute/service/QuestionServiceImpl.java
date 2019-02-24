package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.domain.Replies;
import com.stackroute.exceptions.*;

import com.stackroute.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question addQuestion(Question questionObject) throws QuestionAlreadyExistsException {
        if (questionRepository.existsByQuestion(questionObject.getQuestion())) {
            throw new QuestionAlreadyExistsException("Question already exists");
        }
        questionObject.setQuestionId(questionRepository.findAll().size() + 1);
        Question savedQuestion = questionRepository.save(questionObject);
        return savedQuestion;
    }

    @Override
    public Question addQuestionDescription(int questionId, String description) throws QuestionNotFoundException {

        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            question.setDescription(description);
            return questionRepository.save(question);
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    @Override
    public Question addAnswer(int questionId, List<Answer> answer) throws QuestionNotFoundException {
        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            if (question.getAnswer() != null) {
                List<Answer> answers = question.getAnswer();
                answers.addAll(answer);
                question.setAnswer(answers);
            } else {
                question.setAnswer(answer);
            }
            return questionRepository.save(question);
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    @Override
    public Question addQuestionComment(int questionId, List<Comment> comment) throws QuestionNotFoundException {
        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            if (question.getComment() != null) {
                List<Comment> comments = question.getComment();
                comments.addAll(comment);
                question.setComment(comments);
            } else {
                question.setComment(comment);
            }
            return questionRepository.save(question);
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    @Override
    public Question addQuestionCommentReply(int questionId, String comment, List<Replies> replies) throws QuestionNotFoundException,CommentNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Comment> comments = question.getComment();
            for (Comment comment1: comments) {
                if(comment1.getComment().equals(comment)){
                    flag = true;
                    if (comment1.getReplies()==null){
                        comment1.setReplies(replies);
                    }
                    else {
                        replies.addAll(comment1.getReplies());
                        comment1.setReplies(replies);
                    }
                }
            }
            if (flag){
                return questionRepository.save(question);
            }
            else {
                throw new CommentNotFoundException("Comment does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }

    }

    @Override
    public Question addAnswerComment(int questionId, String answer, List<Comment> comment) throws QuestionNotFoundException,AnswerNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    flag = true;
                    if (answer1.getComments()==null){
                        answer1.setComments(comment);
                    }
                    else {
                        comment.addAll(answer1.getComments());
                        answer1.setComments(comment);
                    }
                }
            }
            if (flag){
                return questionRepository.save(question);
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    @Override
    public Question addAnswerCommentReply(int questionId, String answer, List<Comment> comment)throws QuestionNotFoundException,AnswerNotFoundException,CommentNotFoundException {
        boolean answerFlag = false;
        boolean commentFlag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if (answers==null){
                throw new AnswerNotFoundException("Answer not found");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    answerFlag = true;
                    List<Comment> comments = answer1.getComments();
                    if (comments==null){
                        throw new CommentNotFoundException("Comment not found");
                    }
                    Comment comment2 = comment.get(0);
                    for (Comment comment1:comments) {
                        if(comment1.getComment().equals(comment2.getComment())){
                            commentFlag = true;
                            if(comment1.getReplies()==null){
                                comment1.setReplies(comment2.getReplies());
                            }
                            else{
                                List<Replies> repliesList = comment1.getReplies();
                                repliesList.addAll(comment2.getReplies());
                                comment1.setReplies(repliesList);
                            }
                        }
                    }
                }
            }
            if (answerFlag && commentFlag){
                return questionRepository.save(question);
            }
            else if (!commentFlag){
                throw new CommentNotFoundException("Comment not found");
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    @Override
    public Question addQuestionUpvote(int questionId) throws QuestionNotFoundException {
        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            int upvotes = question.getUpvotes();
            question.setUpvotes(upvotes+1);
            return questionRepository.save(question);
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    @Override
    public Question addQuestionDownvote(int questionId) throws QuestionNotFoundException {
        if (questionRepository.findByQuestionId(questionId) != null) {
            Question question = questionRepository.findByQuestionId(questionId);
            int downvotes = question.getDownvotes();
            question.setDownvotes(downvotes+1);
            return questionRepository.save(question);
        } else
            throw new QuestionNotFoundException("Question does not exists");
    }

    @Override
    public Question addAnswerUpvote(int questionId, String answer) throws QuestionNotFoundException,AnswerNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if(answers==null){
                throw new AnswerNotFoundException("Answer not exists");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    flag = true;
                    int upvotes = answer1.getUpvotes();
                    answer1.setUpvotes(upvotes+1);
                }
            }
            if (flag){
                return questionRepository.save(question);
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    @Override
    public Question addQuestionCommentLikes(int questionId, String comment) throws QuestionNotFoundException,CommentNotFoundException{
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Comment> comments = question.getComment();
            if (comments==null){
                throw new CommentNotFoundException("Comment does not exists");
            }
            for (Comment comment1: comments) {
                if(comment1.getComment().equals(comment)){
                    flag = true;
                    long likes = comment1.getLikes();
                    comment1.setLikes(likes+1);
                }
            }
            if (flag){
                return questionRepository.save(question);
            }
            else {
                throw new CommentNotFoundException("Comment does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    @Override
    public Question addQuestionCommentReplyLikes(int questionId, Comment comment) throws QuestionNotFoundException,CommentNotFoundException,ReplyNotFoundException{
        boolean commentFlag = false;
        boolean replyFlag  = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Comment> comments = question.getComment();
            if (comments==null){
                throw new CommentNotFoundException("Comment not found");
            }
            for (Comment comment1: comments) {
                if(comment1.getComment().equals(comment.getComment())){
                    commentFlag = true;
                    if (comment1.getReplies()==null){
                        throw new ReplyNotFoundException("Reply not found");
                    }
                    else {
                        List<Replies> repliesList = comment1.getReplies();
                        Replies replies = comment.getReplies().get(0);
                        for (Replies reply:repliesList) {
                            if(reply.getReply().equals(replies.getReply())) {
                                replyFlag = true;
                                long likes = reply.getLikes();
                                reply.setLikes(likes+1);
                            }
                        }
                    }
                }
            }
            if (commentFlag && replyFlag){
                return questionRepository.save(question);
            }
            else if (!replyFlag){
                throw new ReplyNotFoundException("Reply not found");
            }
            else {
                throw new CommentNotFoundException("Comment does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    @Override
    public Question addAnswerCommentLikes(int questionId, Answer answer) throws QuestionNotFoundException,AnswerNotFoundException,CommentNotFoundException{
        boolean answerFlag = false;
        boolean commentFlag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if (answers==null){
                throw new AnswerNotFoundException("Answer not found");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer.getAnswer())){
                    answerFlag = true;
                    List<Comment> comments = answer1.getComments();
                    if (comments==null){
                        System.out.println("qqqqqqqqqq");
                        throw new CommentNotFoundException("Comment not found");
                    }
                    Comment comment2 = comments.get(0);
                    for (Comment comment1:comments) {
                        if(comment1.getComment().equals(comment2.getComment())){
                            commentFlag = true;
                            long likes = comment1.getLikes();
                            comment1.setLikes(likes+1);
                        }
                    }
                }
            }
            if (answerFlag && commentFlag){
                return questionRepository.save(question);
            }
            else if (!commentFlag){
                throw new CommentNotFoundException("Comment not found");
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }

    @Override
    public Question addAnswerCommentReplyLikes(int questionId, String answer, String comment, String reply) {
        return null;
    }

    @Override
    public Question addQuestionAnswerAccepted(int questionId, String answer) throws QuestionNotFoundException,AnswerNotFoundException {
        boolean flag = false;
        if (questionRepository.findByQuestionId(questionId)!= null) {
            Question question = questionRepository.findByQuestionId(questionId);
            List<Answer> answers = question.getAnswer();
            if(answers==null){
                throw new AnswerNotFoundException("Answer not exists");
            }
            for (Answer answer1: answers) {
                if(answer1.getAnswer().equals(answer)){
                    flag = true;
                    if(!answer1.isAccepted()){
                        answer1.setAccepted(true);
                    }
                    else{
                        answer1.setAccepted(false);
                    }
                }
            }
            if (flag){
                return questionRepository.save(question);
            }
            else {
                throw new AnswerNotFoundException("Answer does not exists");
            }
        }
        else {
            throw new QuestionNotFoundException("Question does not exists");
        }
    }
}
