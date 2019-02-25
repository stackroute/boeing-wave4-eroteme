package com.stackroute.controller;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.exceptions.*;
import com.stackroute.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping(value = "api/v1")
public class QuestionController extends ResponseEntityExceptionHandler {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("question")
    public ResponseEntity<?> saveQuestion(@RequestBody Question question) throws QuestionAlreadyExistsException {
        questionService.addQuestion(question);
        return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
    }

    @PutMapping("question/{questionId}")
    public ResponseEntity<?> addDescription(@PathVariable int questionId, @RequestBody Question question) throws QuestionNotFoundException {
        questionService.addQuestionDescription(questionId, question.getDescription());
        return new ResponseEntity<String>("Successfully updated", HttpStatus.FOUND);
    }

    @PutMapping("question/answer/{questionId}")
    public ResponseEntity<?> addAnswer(@PathVariable int questionId, @RequestBody Question question) throws QuestionNotFoundException {
        questionService.addAnswer(questionId, question.getAnswer());
        return new ResponseEntity<String>("Successfully updated", HttpStatus.FOUND);
    }

    @PutMapping("question/comment/{questionId}")
    public ResponseEntity<?> addComment(@PathVariable int questionId, @RequestBody Question question) throws QuestionNotFoundException {
        questionService.addQuestionComment(questionId, question.getComment());
        return new ResponseEntity<String>("Successfully updated", HttpStatus.FOUND);
    }

    @PutMapping("question/comment/reply/{questionId}")
    public ResponseEntity<?> addCommentReply(@PathVariable int questionId, @RequestBody Comment comment) throws QuestionNotFoundException, CommentNotFoundException {
        questionService.addQuestionCommentReply(questionId,comment.getComment(),comment.getReplies());
        return new ResponseEntity<String>("Successfully updated",HttpStatus.FOUND);
    }

    @PutMapping("question/answer/comment/{questionId}")
    public ResponseEntity<?> addAnswerComment(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException {
        questionService.addAnswerComment(questionId,answer.getAnswer(),answer.getComments());
        return new ResponseEntity<String>("Successfully updated",HttpStatus.FOUND);
    }

    @PutMapping("question/answer/comment/reply/{questionId}")
    public ResponseEntity<?> addAnswerCommentReply(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException, CommentNotFoundException{
        questionService.addAnswerCommentReply(questionId,answer.getAnswer(),answer.getComments());
        return new ResponseEntity<String>("Successfully updated",HttpStatus.FOUND);
    }
    @PutMapping("question/upvote/{questionId}")
    public ResponseEntity<?> addQuestionUpvote(@PathVariable int questionId) throws QuestionNotFoundException {
        questionService.addQuestionUpvote(questionId);
        return new ResponseEntity<String>("Successfully updated",HttpStatus.FOUND);
    }

    @PutMapping("question/downvote/{questionId}")
    public ResponseEntity<?> addQuestionDownvote(@PathVariable int questionId) throws QuestionNotFoundException {
        questionService.addQuestionDownvote(questionId);
        return new ResponseEntity<String>("Successfully updated",HttpStatus.FOUND);
    }

    @PutMapping("question/answer/upvote/{questionId}")
    public ResponseEntity<?> addAnswerUpvote(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException {
        questionService.addAnswerUpvote(questionId,answer.getAnswer());
        return new ResponseEntity<String>("Successfully updated",HttpStatus.OK);
    }

    @PutMapping("question/answer/accept/{questionId}")
    public ResponseEntity<?> addAnswerAccept(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, AnswerNotFoundException {
        questionService.addQuestionAnswerAccepted(questionId,answer.getAnswer());
        return new ResponseEntity<String>("Successfully updated",HttpStatus.OK);
    }

    @PutMapping("question/comment/likes/{questionId}")
    public ResponseEntity<?> addQuestionCommentLikes(@PathVariable int questionId, @RequestBody Comment comment) throws QuestionNotFoundException, CommentNotFoundException {
        questionService.addQuestionCommentLikes(questionId,comment.getComment());
        return new ResponseEntity<String>("Successfully updated",HttpStatus.OK);
    }

    @PutMapping("question/answer/comment/likes/{questionId}")
    public ResponseEntity<?> addQuestionAnswerCommentLikes(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException, CommentNotFoundException, AnswerNotFoundException {
        questionService.addAnswerCommentLikes(questionId,answer);
        return new ResponseEntity<String>("Successfully updated",HttpStatus.OK);
    }

    @PutMapping("question/comment/reply/likes/{questionId}")
    public ResponseEntity<?> addQuestionCommentReplyLikes(@PathVariable int questionId, @RequestBody Comment comment) throws QuestionNotFoundException,CommentNotFoundException, ReplyNotFoundException {
        questionService.addQuestionCommentReplyLikes(questionId,comment);
        return new ResponseEntity<String>("Successfully updated",HttpStatus.OK);
    }

    @PutMapping("question/answer/comment/reply/likes/{questionId}")
    public ResponseEntity<?> addAnswerCommentReplyLikes(@PathVariable int questionId, @RequestBody Answer answer) throws QuestionNotFoundException,AnswerNotFoundException,CommentNotFoundException,ReplyNotFoundException {
        questionService.addAnswerCommentReplyLikes(questionId,answer);
        return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
    }
}