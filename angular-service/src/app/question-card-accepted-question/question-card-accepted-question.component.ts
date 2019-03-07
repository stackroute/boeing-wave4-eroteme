import { Component, OnInit, Input } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-questionNode-card-accepted-questionNode',
  templateUrl: './questionNode-card-accepted-questionNode.component.html',
  styleUrls: ['./questionNode-card-accepted-questionNode.component.css']
})
export class QuestionCardAcceptedQuestionComponent implements OnInit {

  @Input()
  passedQuestions;
  
  desc;

  size;


  constructor(private trans:TransferServiceService) {
   }

  ngOnInit() {
    this.size=this.passedQuestions.answers.length;
    this.desc=(this.passedQuestions.description ? this.passedQuestions.description : '(No Description)');
    this.assign();
  }

  put(){
    this.trans.value=this.passedQuestions;
  }

  assign(){
    console.log(this.passedQuestions.description.length);
  }

}
