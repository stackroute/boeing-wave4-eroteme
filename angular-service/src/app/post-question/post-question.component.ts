import { NavbarComponent } from './../navbar/navbar.component';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { MatDialog } from '@angular/material';
import { RegisterComponent } from '../register/register.component';
import { LoginpopupComponent } from '../loginpopup/loginpopup.component';
import { LoginComponent } from '../login/login.component';

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

  topiclist = [{ "topic": "Components" },
  { "topic": "Component interaction" },
  { "topic": "Pipes" },
  { "topic": "Services" },
  { "topic": "Directives" },
  { "topic": "Routing" },
  { "topic": "Dependency Injection" },
  { "topic": "Modules" }]

  constructor(private router :Router, private app: AppComponent, private http: HttpClient,private dialog:MatDialog) { }

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
    this.http.post("http://52.66.134.21:8090/api/v1/question",
      {
        "question": this.ques,
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
        "comment": null,
        "answer": null
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
    alert("Your question is posted successfully");
    window.location.reload();
    },
    error  => {

    console.log("Error", error);

    }

    );
    }
}

  addTopic(toadd) {
    this.toSendList.push(toadd);
  }

}
