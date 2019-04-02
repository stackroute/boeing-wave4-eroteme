package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.domain.Replies;
import com.stackroute.exceptions.*;

import java.io.IOException;
import java.util.List;

public interface QuestionService {
    Question addQuestion(Question questionObject) throws QuestionAlreadyExistsException, IOException;

    Question addAnswer(int questionId, Answer answer) throws QuestionNotFoundException, IOException;

    Question getQuestion(int questionId) throws QuestionNotFoundException;

    Question getByQuestionString(String questionString);

    Question addQuestionComment(int questionId, Comment comment) throws QuestionNotFoundException, IOException;

    Question addQuestionCommentReply(int questionId, String comment, List<Replies> replies) throws QuestionNotFoundException, CommentNotFoundException, IOException;

    Question addAnswerComment(int questionId, String answer, List<Comment> comment) throws QuestionNotFoundException, AnswerNotFoundException, IOException;

    Question addAnswerCommentReply(int questionId, String answer, List<Comment> comment) throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException, IOException;

    Question addQuestionUpvote(int questionId) throws QuestionNotFoundException, IOException;

    Question addQuestionDownvote(int questionId) throws QuestionNotFoundException, IOException;

    Question addAnswerUpvote(int questionId, String answer) throws QuestionNotFoundException, AnswerNotFoundException, IOException;

    Question addAnswerDownvote(int questionId, String answer) throws QuestionNotFoundException, AnswerNotFoundException, IOException;

    Question addQuestionCommentLikes(int questionId, String comment) throws QuestionNotFoundException, CommentNotFoundException, IOException;

    Question addQuestionCommentReplyLikes(int questionId, Comment comment) throws QuestionNotFoundException, CommentNotFoundException, ReplyNotFoundException, IOException;

    Question addAnswerCommentLikes(int questionId, Answer answer) throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException, IOException;

    Question addAnswerCommentReplyLikes(int questionId, Answer answer) throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException, ReplyNotFoundException, IOException;

    Question addQuestionAnswerAccepted(int questionId, String answer) throws QuestionNotFoundException, AnswerNotFoundException, IOException;

    List<Question> getAllQuestions();
}