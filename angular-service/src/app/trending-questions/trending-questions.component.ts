import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';


@Component({
  selector: 'app-trending-questions',
  templateUrl: './trending-questions.component.html',
  styleUrls: ['./trending-questions.component.css']
})
export class TrendingQuestionsComponent implements OnInit {

  questions;

  constructor(private trans:TransferServiceService) {
    this.trans.loaditems1().subscribe((data) => {
      this.questions = data;
    });
   }

  ngOnInit() {
  }

}
