import { HttpClient} from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-notify-answerNode-card',
  templateUrl: './notify-answerNode-card.component.html',
  styleUrls: ['./notify-answerNode-card.component.css']
})
export class NotifyAnswerCardComponent implements OnInit {

  showAllCommentsQuestion = '';
  showAllCommentsAnswer = '';
  present;
  vote;
  ans;
  val = "";
  comm;
  comm1;
  val1 = "";
  answerNode;
  questionComm;
  QuestionCommentReply;
  commentAnswer1:string;
  replyanswercomment:string;



  constructor(private trans: TransferServiceService, private app: AppComponent, private route: Router, private http: HttpClient) {
    this.showAllCommentsQuestion = 'Show Details';
    this.showAllCommentsAnswer = 'Show Details';
  }

  ngOnInit() {
    this.present = this.trans.value;
    this.vote = this.present.upvotes - this.present.downvote;
  }

  toggle(answerNode) {
    this.ans = answerNode;
    if (this.showAllCommentsAnswer === 'Hide Details') {
      this.showAllCommentsAnswer = 'Show Details';
    } else {
      this.showAllCommentsAnswer = 'Hide Details'
    }
  }

  toggle1() {
    if (this.showAllCommentsQuestion === 'Hide Details') {
      this.showAllCommentsQuestion = 'Show Details';
    } else {
      this.showAllCommentsQuestion = 'Hide Details'
    }
  }

  putval(comment: String) {
    this.comm = comment;
    if (this.val === "") {
      this.val = "some";
    } else {
      this.val = "";
    }
  }

  putval1(comment: String) {
    this.comm1 = comment;
    if (this.val1 === "") {
      this.val1 = "some";
    } else {
      this.val1 = "";
    }
  }
  //functions to use

  upvoteQuestion() {
    console.log(this.app.checkLoggedIn);
    if (this.app.checkLoggedIn == null) {
      alert("You need to login first");
      this.route.navigate(["/login"]);
    }
    else {
      console.log('questionNode upvote');
      this.http.put("http://localhost:8090/api/v1/questionNode/upvote/" + this.present.questionId,{});
    }
  }

  downvoteQuestion() {
    if (this.app.checkLoggedIn == null) {
      alert("You need to login first");
      this.route.navigate(["/login"]);
    }
    else {
      console.log('questionNode downvote');
      this.http.put("http://localhost:8090/api/v1/questionNode/downvote/"+this.present.questionId,{});
    }
  }

  commentQuestion() {
    if (this.app.checkLoggedIn == null) {
      alert("You need to login first");
      this.route.navigate(["/login"]);
    }
    else {
      console.log('comment on questionNode');
      console.log(this.answerNode);
      console.log('post answerNode for the questionNode');
      this.http.put("http://localhost:8090/api/v1/questionNode/answerNode/" + this.present.questionId,
        {
          "comment": this.questionComm,
          "timestamp": 9876543,
          "likes": 0,
          "userNode": {
            "email": "anjo@gmail.com",
            "firstName": "anjo",
            "imageurl": "https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
          },
          "replies": null
        },
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'my-auth-token'
          })
        })
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
            alert("Comment added successfully");
          },
          error => {

            console.log("Error", error);

          })
    }

  }

replyQuestionComment(presentcomment:string) {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('reply to comment of questionNode');
      this.http.put("http://localhost:8090/api/v1/questionNode/comment/reply/" + this.present.questionId,
      {
        "comment":presentcomment,
            "replies":[
              {
                "reply":this.QuestionCommentReply,
                "likes":0,
                "timestamp":64783,
                "userNode":{
                  "email":"anjo@gmail.com",
                  "firstName":"anjo",
                  "imageurl":"https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
                }
              }
                ]
      },
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'my-auth-token'
          })
        })
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
            alert("Reply made successfully");
          },
          error => {

            console.log("Error", error);

          })
  }
}

upvoteAnswer(ans1) {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('answerNode upvote');
    this.http.put("http://localhost:8080/api/v1/questionNode/answerNode/upvote/"+this.present.questionId,{
      "answerNode": ans1
    })
  }
}

downvoteAnswer() {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('downvote answerNode');
  }
}

commentAnswer(ans) {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('comment on answerNode');
      this.http.put("http://localhost:8090/api/v1/questionNode/answerNode/comment/" + this.present.questionId,
      {
        "answerNode": ans,
        "comments": [
      {
        "comment":this.commentAnswer1,
        "timestamp":4373648,
        "likes": 0,
        "userNode":{
              "email":"aishu@gmail.com",
              "firstName":"aishu",
              "imageurl":"https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
            },
        "replies": null
      }
  ]
},
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'my-auth-token'
          })
        })
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
            alert("Comment added to answerNode successfully");
          },
          error => {

            console.log("Error", error);

          })
  }
}

replyAnswerComment(ans,comm) {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log('reply to comment of answerNode');
    this.http.put("http://localhost:8090/api/v1/questionNode/answerNode/comment/reply/" + this.present.questionId,
    {
      "answerNode": ans,
      "comments": [
              {
                "comment":comm,
                "replies":[
                      {
                      "reply":this.replyanswercomment,
                      "likes":0,
                      "timestamp":64783,
                      "userNode":{
                          "email":"anjo@gmail.com",
                          "firstName":"anjo",
                          "imageurl":"https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
                          }
                      }
                    ]
              }
            ]
},
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'my-auth-token'
          })
        })
        .subscribe(
          data => {
            console.log("POST Request is successful ", data);
            alert("Reply made successfully");
          },
          error => {

            console.log("Error", error);

          })
  }
}

postanswer() {
  if (this.app.checkLoggedIn == null) {
    alert("You need to login first");
    this.route.navigate(["/login"]);
  }
  else {
    console.log(this.answerNode);
    console.log('post answerNode for the questionNode');
    this.http.put("http://localhost:8090/api/v1/questionNode/answerNode/" + this.present.questionId,
      {
        "answerNode": this.answerNode,
        "accepted": "false",
        "upvotes": 0,
        "views": 0,
        "timestamp": 445678,
        "userNode": {
          "email": " angel@gmail.com",
          "firstName": "angel",
          "imageUrl": "https://i.pinimg.com/originals/0c/de/1f/0cde1ffe66ebf04eda41a30a4ef05a26.jpg"
        },
        "comments": null
      },
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'my-auth-token'
        })
      })
      .subscribe(
        data => {
          console.log("POST Request is successful ", data);
          alert("ANswer updated successfully");
        },
        error => {

          console.log("Error", error);

        })
  }
}


acceptedAnswer(answertoAccept){
  this.http.put("http://localhost:8080/api/v1/questionNode/answerNode/accept"+this.present.questionId,{
    "answerNode": answertoAccept
  })
}


}
