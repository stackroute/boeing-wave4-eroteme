import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-hometrendquestions',
  templateUrl: './hometrendquestions.component.html',
  styleUrls: ['./hometrendquestions.component.css']
})
export class HometrendquestionsComponent implements OnInit {
  questionss;

  constructor(private trans:TransferServiceService,private app:AppComponent) {
    if(this.app.checkLoggedIn==null){
      this.trans.GuestTrendingQues().subscribe((data)=>
      {
        this.questionss=data;
      })
    }else{
      this.trans.LoggedInTrendingQues(this.app.emailid).subscribe((data)=>{
        this.questionss=data;
      })
    }

   }

  ngOnInit() {
  }

}
