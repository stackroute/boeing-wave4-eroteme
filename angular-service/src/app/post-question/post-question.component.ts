import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-post-questionNode',
  templateUrl: './post-questionNode.component.html',
  styleUrls: ['./post-questionNode.component.css']
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

  constructor(private route: Router, private app: AppComponent, private http: HttpClient) { }

  ngOnInit() {
  }

  postQuestion() {
    if (this.app.checkLoggedIn == null) {
      alert("You need to login first");
      this.route.navigate(["/login"]);
    }else{
    console.log(this.ques);
    console.log(this.description);
    console.log(this.toSendList);
    this.http.post("http://localhost:8090/api/v1/questionNode",
      {
        "questionNode": this.ques,
        "description": this.description,
        "topics": this.toSendList,
        "upvotes": 0,
        "timestamp": 82345,
        "downvotes": 0,
        "userNode": null,
        "comment": null,
        "answerNode": null
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
    alert("Your questionNode is posted successfully");
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
