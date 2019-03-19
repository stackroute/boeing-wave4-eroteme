import { Component, OnInit, Input } from '@angular/core';
import {QuestionAnswerCardComponent}from '../question-answer-card/question-answer-card.component';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent implements OnInit {
  count : number =0;
  downcount : number=0;
//<app-child [title]=x.question [description]=x.description [upvote]=x.upvotes [downvote]=x.downvote [answer]=y.answer [view]=y.views></app-child>

  @Input()
  question;
  

  title;
  description;
  upvote;
  downvote;
  answer;
  view;

  constructor(private trans:TransferServiceService) {
  }

  ngOnInit() {
    console.log(this.question);
    this.title=this.question.question;
    this.description=this.question.description;
    this.upvote=this.question.upvotes;
    this.downvote=this.question.downvote;
    this.answer=this.question.answers[0].answer;
    // console.log("uu testing"+this.answer);
    this.view=this.question.answers[0].views;
  }
 
  putValue(){
    console.log(this.question);
    this.trans.value=this.question;
    // console.log("testig the child" +this.question);
  }

 }
