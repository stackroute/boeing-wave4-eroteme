import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-homeunanswered',
  templateUrl: './homeunanswered.component.html',
  styleUrls: ['./homeunanswered.component.css']
})
export class HomeunansweredComponent implements OnInit {

  questionss;

  constructor(private trans:TransferServiceService,private app:AppComponent) { 
    if(this.app.checkLoggedIn== null){
      this.trans.GuestUnansweredQues().subscribe((data)=>{
        this.questionss=data;
      })
    }else{
      console.log("logged in");
      this.trans.LoggedInUnansweredQues(this.app.emailid).subscribe((data)=>{
        console.log(data);
        this.questionss=data;
      })
    }  
  }

  ngOnInit() {
  }
}
