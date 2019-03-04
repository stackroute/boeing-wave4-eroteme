import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-accepted-ques',
  templateUrl: './accepted-ques.component.html',
  styleUrls: ['./accepted-ques.component.css']
})
export class AcceptedQuesComponent implements OnInit {

  questions;
  
  //Here we have to take from the Accepted user section
  constructor(private trans:TransferServiceService) {
    this.trans.loaditems1().subscribe((data) => {
      this.questions = data;
    });
   }


  ngOnInit() {
  }

}
