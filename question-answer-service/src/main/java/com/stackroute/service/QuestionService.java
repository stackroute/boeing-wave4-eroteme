package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.domain.Replies;
import com.stackroute.exceptions.CommentAlreadyExistsException;
import com.stackroute.exceptions.QuestionAlreadyExistsException;
import com.stackroute.exceptions.QuestionNotFoundException;

public interface QuestionService {
    public Question addQuestion(Question questionObject) throws QuestionAlreadyExistsException;
    public Question addAnswer(int questionId,Answer answer) throws QuestionNotFoundException;
    public Question addQuestionComment(int questionId, Comment comment) throws CommentAlreadyExistsException,QuestionNotFoundException;
    public Question addQuestionCommentReply(int questionId, String comment, Replies replies);
    public Question addAnswerComment(int questionId, String answer, Comment comment);
    public Question addAnswerCommentReply(int questionId,String answer,String comment,Replies replies);
    public Question addQuestionUpvote(int questionId);
    public Question addQuestionDownvote(int questionId);
    public Question addAnswerUpvote(int questionId,String answer);
    public Question addQuestionCommentLikes(int questionId, String comment);
    public Question addQuestionCommentReplyLikes(int questionId,String comment,String reply);
    public Question addAnswerCommentLikes(int questionId,String answer,String comment);
    public Question addAnswerCommentReplyLikes(int questionId,String answer,String comment,String reply);
    public Question addQuestionAnswerAccepted(int questionId,String answer);
}
