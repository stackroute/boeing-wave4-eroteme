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
    public Question addAnswerCommentReply(int questionId, String answer, String comment, Replies replies) {
        return null;
    }

    @Override
    public Question addQuestionUpvote(int questionId) {
        return null;
    }

    @Override
    public Question addQuestionDownvote(int questionId) {
        return null;
    }

    @Override
    public Question addAnswerUpvote(int questionId, String answer) {
        return null;
    }

    @Override
    public Question addQuestionCommentLikes(int questionId, String comment) {
        return null;
    }

    @Override
    public Question addQuestionCommentReplyLikes(int questionId, String comment, String reply) {
        return null;
    }

    @Override
    public Question addAnswerCommentLikes(int questionId, String answer, String comment) {
        return null;
    }

    @Override
    public Question addAnswerCommentReplyLikes(int questionId, String answer, String comment, String reply) {
        return null;
    }

    @Override
    public Question addQuestionAnswerAccepted(int questionId, String answer) {
        return null;
    }
}
