import { NavbarComponent } from './../navbar/navbar.component';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { MatDialog } from '@angular/material';
import { RegisterComponent } from '../register/register.component';
import { LoginpopupComponent } from '../loginpopup/loginpopup.component';
import { LoginComponent } from '../login/login.component';
import {TransferServiceService} from '../transfer-service.service';

@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.css']
})
export class PostQuestionComponent implements OnInit {

  postValue;

  toSendList = [];

  ques: string;

  description: string;

  topiclist = [{ "topic": "Fundamentals and Architecture" },
  { "topic": "Typescript" },
  { "topic": "Testing"},
  { "topic": "Server Side Communication"},
  { "topic": "Forms"},
  { "topic": "Navigation"},
  { "topic": "Pipes" },
  { "topic": "Data Binding"},
  { "topic": "Getting Started"}]
  selectedList=[];
  index;
  index1;
  str;

  constructor(private router :Router, private app: AppComponent, private http: HttpClient,private dialog:MatDialog,private trans:TransferServiceService) { }

  ngOnInit() {
  }

  postQuestion() {
    if (this.app.checkLoggedIn == null) {
      // alert("You need to login first");
      this.dialog.open(LoginpopupComponent);
      // this.route.navigate(["/login"]);
    }else{
    console.log(this.ques);
    console.log(this.description);
    console.log(this.toSendList);

    this.http.post("http://localhost:8082/result",
      {
        "question": this.ques,
        "action":"POST_QUESTION",
        "description": this.description,
        "topics": this.toSendList,
        "upvotes": 0,
        "timestamp": 82345,
        "downvotes": 0,
        "user": {
          "email": this.app.emailid,
          "firstName": this.app.emailid.split("@")[0],
          "imageUrl": "https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
        },
        "comment": [],
        "answer": []
      },
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'my-auth-token'
        })
  })
  .subscribe(
    data  => {
    console.log("POST Request is successful ", data);
    this.trans.result.next(data);
    console.log("wer \n",this.trans.result);
    },
    error  => {
    console.log("Error", error);
    });
    this.router.navigate(['/evaluation']);
    }
}

addTopic(toadd) {
  this.toSendList.push(toadd);
  this.index1=this.topiclist.indexOf(toadd);
  this.topiclist.splice(this.index,0);
  console.log(this.toSendList);
  
}
delTopic(topic){
  this.index=this.toSendList.indexOf(topic);
  this.toSendList.splice(this.index,1);
  console.log(this.toSendList); 
  this.str={"topic":topic};
  this.topiclist.push(this.str);
  
}
}
