package com.stackroute.service;

//Actions that says for what event the questionDTO is sent through rabbitmq
public enum Actions {
    QUESTION_COMMENT,
    ANSWER_COMMENT,
    POST_QUESTION,
    QUESTION_ANSWER,
    QUESTION_COMMENT_REPLY,
    ANSWER_COMMENT_REPLY,
    QUESTION_UPVOTE,
    QUESTION_DOWNVOTE,
    ANSWER_UPVOTE,
    ANSWER_DOWNVOTE,
    QUESTION_COMMENT_LIKE,
    QUESTION_COMMENT_REPLY_LIKE,
    ANSWER_COMMENT_LIKE,
    ANSWER_COMMENT_REPLY_LIKE,
    ANSWER_ACCEPT;

    Actions() {
    }
}
