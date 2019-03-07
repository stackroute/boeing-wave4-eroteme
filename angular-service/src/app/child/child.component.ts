import { Component, OnInit, Input } from '@angular/core';
import { QuestionAnswerCardComponent } from '../questionNode-answerNode-card/questionNode-answerNode-card.component';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent implements OnInit {
  count : number =0;
  downcount : number=0;
  //<app-child [title]=x.questionNode [description]=x.description [upvote]=x.upvotes [downvote]=x.downvote [answerNode]=y.answerNode [view]=y.views></app-child>

  @Input()
  questionNode;

  title;
  description;
  upvote;
  downvote;
  answerNode;
  view;

  constructor(private trans:TransferServiceService) {
  }

  ngOnInit() {
    console.log("in child component"+this.questionNode.toString());
    this.title=this.questionNode.questionNode;
    this.description=this.questionNode.description;
    this.upvote=this.questionNode.upvotes;
    this.downvote=this.questionNode.downvote;
    this.answerNode=this.questionNode.answers[0].answerNode;
    this.view=this.questionNode.answers[0].views;
  }
 
  putValue(){
    this.trans.value=this.questionNode;
  }

 }
