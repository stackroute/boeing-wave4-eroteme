package com.stackroute.controller;

import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.domain.Replies;
import com.stackroute.exceptions.CommentNotFoundException;
import com.stackroute.exceptions.QuestionAlreadyExistsException;
import com.stackroute.exceptions.QuestionNotFoundException;
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

    @PostMapping("ques")
    public ResponseEntity<?> saveQuestion(@RequestBody Question question) throws QuestionAlreadyExistsException {
        questionService.addQuestion(question);
        return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
    }

    @PutMapping("ques/{questionId}")
    public ResponseEntity<?> addDescription(@PathVariable int questionId, @RequestBody Question question) throws QuestionNotFoundException {
        questionService.addQuestionDescription(questionId, question.getDescription());
        return new ResponseEntity<String>("Successfully updated", HttpStatus.FOUND);
    }

    @PutMapping("quesans/{questionId}")
    public ResponseEntity<?> addAnswer(@PathVariable int questionId, @RequestBody Question question) throws QuestionNotFoundException {
        questionService.addAnswer(questionId, question.getAnswer());
        return new ResponseEntity<String>("Successfully updated", HttpStatus.FOUND);
    }

    @PutMapping("quescomment/{questionId}")
    public ResponseEntity<?> addComment(@PathVariable int questionId, @RequestBody Question question) throws QuestionNotFoundException {
        questionService.addQuestionComment(questionId, question.getComment());
        return new ResponseEntity<String>("Successfully updated", HttpStatus.FOUND);
    }
    @PutMapping("quescommentreply/{questionId}")
    public ResponseEntity<?> addCommentReply(@PathVariable int questionId, @RequestBody Comment comment) throws QuestionNotFoundException, CommentNotFoundException {
        questionService.addQuestionCommentReply(questionId,comment.getComment(),comment.getReplies());
        return new ResponseEntity<String>("Successfully updated",HttpStatus.FOUND);
    }
}
