import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-unanswered-questions',
  templateUrl: './unanswered-questions.component.html',
  styleUrls: ['./unanswered-questions.component.css']
})
export class UnansweredQuestionsComponent implements OnInit {

  questions;

  constructor(private trans:TransferServiceService) { 
    this.trans.loaditems().subscribe((data) => {
      this.questions = data;
    });
  }

  ngOnInit() {
  }

}
