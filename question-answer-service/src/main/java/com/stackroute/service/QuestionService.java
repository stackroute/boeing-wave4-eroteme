package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.domain.Replies;
import com.stackroute.exceptions.QuestionAlreadyExistsException;
import com.stackroute.exceptions.QuestionNotFoundException;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question questionObject) throws QuestionAlreadyExistsException;

    Question addQuestionDescription(int questionId, String description) throws QuestionNotFoundException;

    Question addAnswer(int questionId, List<Answer> answer) throws QuestionNotFoundException;

    Question addQuestionComment(int questionId, List<Comment> comment) throws QuestionNotFoundException;

    Question addQuestionCommentReply(int questionId, String comment, Replies replies);

    Question addAnswerComment(int questionId, String answer, Comment comment);

    Question addAnswerCommentReply(int questionId, String answer, String comment, Replies replies);

    Question addQuestionUpvote(int questionId);

    Question addQuestionDownvote(int questionId);

    Question addAnswerUpvote(int questionId, String answer);

    Question addQuestionCommentLikes(int questionId, String comment);

    Question addQuestionCommentReplyLikes(int questionId, String comment, String reply);

    Question addAnswerCommentLikes(int questionId, String answer, String comment);

    Question addAnswerCommentReplyLikes(int questionId, String answer, String comment, String reply);

    Question addQuestionAnswerAccepted(int questionId, String answer);
}
