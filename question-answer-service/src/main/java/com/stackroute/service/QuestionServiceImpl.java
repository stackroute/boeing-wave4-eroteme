package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.domain.Replies;
import com.stackroute.exceptions.CommentAlreadyExistsException;
import com.stackroute.exceptions.QuestionAlreadyExistsException;
import com.stackroute.exceptions.QuestionNotFoundException;

public class QuestionServiceImpl implements QuestionService{
    @Override
    public Question addQuestion(Question questionObject) throws QuestionAlreadyExistsException {
        return null;
    }

    @Override
    public Question addAnswer(int questionId, Answer answer) throws QuestionNotFoundException {
        return null;
    }

    @Override
    public Question addQuestionComment(int questionId, Comment comment) throws CommentAlreadyExistsException, QuestionNotFoundException {
        return null;
    }

    @Override
    public Question addQuestionCommentReply(int questionId, String comment, Replies replies) {
        return null;
    }

    @Override
    public Question addAnswerComment(int questionId, String answer, Comment comment) {
        return null;
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
