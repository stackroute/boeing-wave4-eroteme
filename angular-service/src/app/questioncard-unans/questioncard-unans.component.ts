import{Component, OnInit, Input}from '@angular/core';
import {TransferServiceService}from '../transfer-service.service';

@Component({
selector: 'app-questioncard-unans',
templateUrl: './questioncard-unans.component.html',
styleUrls: ['./questioncard-unans.component.css']
})
export class QuestioncardUnansComponent implements OnInit {

@Input()
passedQuestions;

desc;




constructor(private trans:TransferServiceService) {
   }

  ngOnInit() {
    this.desc=(this.passedQuestions.description ? this.passedQuestions.description : '(No Description)');
    //this.assign();
  }

  put(){
    this.trans.value=this.passedQuestions;
  }

  //assign(){
    //console.log(this.passedQuestions.description.length);
  //}

}
