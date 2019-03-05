import { Component, OnInit} from '@angular/core';
import {TransferServiceService} from '../transfer-service.service';
@Component({
  selector: 'app-question-answerDTO-card',
  templateUrl: './question-answerDTO-card.component.html',
  styleUrls: ['./question-answerDTO-card.component.css']
})
export class QuestionAnswerCardComponent implements OnInit {

  showAllCommentsQuestion='';
  showAllCommentsAnswer='';
  present;
  vote;
  ans;
  val="";
  comm;
  comm1;
  val1="";

  
  constructor(private trans:TransferServiceService) { 
    this.showAllCommentsQuestion='Show Details';
    this.showAllCommentsAnswer='Show Details';
  }

  ngOnInit() {
    this.present=this.trans.value;
    this.vote=this.present.upvote-this.present.downvote;
  }

  toggle(answerDTO){
    this.ans=answerDTO;
    if(this.showAllCommentsAnswer==='Hide Details'){
      this.showAllCommentsAnswer='Show Details';
    }else{
      this.showAllCommentsAnswer='Hide Details'
    }
  }

  toggle1(){
    if(this.showAllCommentsQuestion==='Hide Details'){
      this.showAllCommentsQuestion='Show Details';
    }else{
      this.showAllCommentsQuestion='Hide Details'
    }
  }

  putval(comment:String){
    this.comm=comment;
   if(this.val===""){
     this.val="some";
   }else{
     this.val="";
   }
  }

  putval1(comment:String){
    this.comm1=comment;
   if(this.val1===""){
     this.val1="some";
   }else{
     this.val1="";
   }
  }
  //functions to use

  upvoteQuestion(){
    console.log('question upvote')
  }

  downvoteQuestion(){
    console.log('question downvote')
  }

  commentQuestion(){
    console.log('comment on question')
  }
  
  replyQuestionComment(){
    console.log('reply to comment of question')
  }

  upvoteAnswer(){
    console.log('answerDTO upvote');
  }

  downvoteAnswer(){
    console.log('downvote answerDTO')
  }

  commentAnswer(){
    console.log('comment on answerDTO');
  }

  replyAnswerComment(){
    console.log('reply to comment of answerDTO');
  }

  postanswer(){
    console.log('post answerDTO for the question');
  }
}
