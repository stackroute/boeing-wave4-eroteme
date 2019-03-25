import { Component, OnInit } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { AppComponent } from '../app.component';
import { pipe } from 'rxjs';


@Component({
  selector: 'app-trending-questions',
  templateUrl: './trending-questions.component.html',
  styleUrls: ['./trending-questions.component.css']
})
export class TrendingQuestionsComponent implements OnInit {

  questions;

  constructor(private trans:TransferServiceService,private app:AppComponent) {
    // if(this.app.checkLoggedIn==null){
    //   this.trans.GuestTrendingQues().subscribe((data)=>
    //   {
    //     this.questions=data;
    //   })
    // }else{
    //   this.trans.LoggedInTrendingQues(this.app.emailid).subscribe((data)=>{
    //     this.questions=data;
    //   })
    // }
    this.questions=[{
      "question": " What is a RouterOutlet?",
      "description":"common question about angular-Routing",
      "vote":10,
      "topics":["uday","uthukota"],
      "timestamp":456900,
      "downvote":1,
      "answer":[
          {
           "answer":"RouterOutlet is a substitution for templates rendering the components. In other words, it represents or renders the components on a template at a particular location.",
                 "accepted":"true",
           "comments":[
               {
            "comment":"okay answer",
            "timestamp":234342,
            "likes":7,
            "replies":[
               {
                "reply":"elaborate more",
                 "likes":5,
                 "timestamp":789000,
                                         "user":{
                                              "firstname":"uday",
                                              "emailaddress":"uady@gmail.com",
                                              "imageurl":"https://cdn.pixabay.com/photo/2012/04/12/12/32/router-29825__340.png"
                                              }
               }
              ],
                              "user":{
                                   "firstname":"zakir",
                                   "emailaddress":"zakir@gmail.com",
                                   "imageurl":"https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                                   }
               }
              ],
           "upvotes":6,
           "views":2,
           "timestamp":6789023,
                 "user":{
                      "firstname":"pardhu",
                      "emailaddress":"pardhu@gmail.com",
                      "imageurl":"https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                      }
      
            }
            ],
      
      "comments":[
          {
           "comment":"helpful",
           "timestamp":456600,
           "likes":4,
           "replies":[
              {
              "reply":2,
              "likes":7,
              "timestamp":234200
              }
             ]
          }
        ],
      "user":{
           "firstname":"mounika",
           "emailaddress":"mounika@gmail.com",
           "imageurl":"https://www.w3schools.com/angular/pic_angular.jpg"
           }
      },
      
        
        {
      "question":"What is Routing in AngularJS?",
      "description":"commanly asked question",
      "upvotes":3,
      "timestamp":678905,
      "downvote":2,
      "answers":[
          {
           "answer":"If you want to navigate to different pages in your application, but you also want the application to be a SPA (Single Page Application), with no page reloading, you can use the ngRoute module.The ngRoute module routes your application to different pages without reloading the entire application.",
                 "accepted":"true",
           "comments":[
               {
            "comment":"okay answer",
            "timestamp":234400,
            "likes":10,
            "replies":[
               {
                "reply":"elaborate more",
                 "likes":2,
                 "timestamp":789065,
                                         "user":{
                                              "firstname":"naneeth",
                                              "emailaddress":"naneeth@gmail.com",
                                              "imageurl":"https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                                              }
               }
              ],
                              "user":{
                                   "firstname":"neethu",
                                   "emailaddress":"neetu@gmail.com",
                                   "imageurl":"https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                                   }
               }
              ],
           "upvotes":10,
           "views":2,
           "timestamp":6789100,
                 "user":{
                      "firstname":"harry",
                      "emailaddress":"harry@gmail.com",
                      "imageurl":"https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                      }
      
            }
            ],
      
      "comments":[
          {
           "comment":"helpful",
           "timestamp":456700,
           "likes":1,
           "replies":[
              {
              "reply":4,
              "likes":4,
              "timestamp":234567
              }
             ]
          }
        ],
      "user":{
           "firstname":"ximanta",
           "emailaddress":"ximanta@gmail.com",
           "imageurl":"https://www.w3schools.com/angular/pic_angular.jpg"
           }
      
      },
       {
      "question": "Explain ng-bind directive.",
      "description":"commanly asked question",
      "upvotes":10,
      "timestamp":678905,
      "downvote":12,
      "answers":[
          {
           "answer":"The ng-bind directive tells AngularJS to replace the content of an HTML element with the value of a given variable, or expression.",
                 "accepted":"true",
           "comments":[
               {
            "comment":"okay answer",
            "timestamp":234529,
            "likes":5,
            "replies":[
               {
                "reply":"elaborate more",
                 "likes":2,
                 "timestamp":789065,
                                         "user":{
                                              "firstname":"amit",
                                              "emailaddress":"amit@gmail.com",
                                              "imageurl":"https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                                              }
               }
              ],
                              "user":{
                                   "firstname":"sunithi",
                                   "emailaddress":"sunithi@gmail.com",
                                   "imageurl":"https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                                   }
               }
              ],
           "upvotes":10,
           "views":2,
           "timestamp":6789023,
                 "user":{
                      "firstname":"amrit",
                      "emailaddress":"amrit@gmail.com",
                      "imageurl":"https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                      }
      
            }
            ],
      
      "comments":[
          {
           "comment":"helpful",
           "timestamp":456700,
           "likes":4,
           "replies":[
              {
              "reply":6,
              "likes":9,
              "timestamp":234201
              }
             ]
          }
        ],
      "user":{
           "firstname":"shalini",
           "emailaddress":"shalini@gmail.com",
           "imageurl":"https://www.w3schools.com/angular/pic_angular.jpg"
           }
      
      },
      {
      "question": "how is routing useful in angular??",
      "description":"important question about routing-angular",
      "upvotes":3,
      "timestamp":953743,
      "downvote":0,
      "answers":[
          {
           "answer":"The sample routing application does not include routing by default. When you use the Angular CLI to create a project that will use routing, set the --routing option for the project or app, and for each NgModule. ... You can then use routing in any NgModule that you add to the project or app.",
                 "accepted":"true",
           "comments":[
               {
            "comment":"good answer",
            "timestamp":531222,
            "likes":10,
            "replies":[
               {
                "reply":"elaborate more",
                 "likes":4,
                 "timestamp":789211,
                                         "user":{
                                              "firstname":"hima",
                                              "emailaddress":"hima@gmail.com",
                                              "imageurl":"https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/9b7e9421-72d6-4f22-9a66-3ab381efbb99/d53xf9q-275ea0d7-f50a-4d22-99c4-271b255798e2.jpg"
                                              }
               }
              ],
                              "user":{
                                   "firstname":"rohit",
                                   "emailaddress":"rohit@gmail.com",
                                   "imageurl":"https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/9b7e9421-72d6-4f22-9a66-3ab381efbb99/d53xf9q-275ea0d7-f50a-4d22-99c4-271b255798e2.jpg"
                                   }
               }
              ],
           "upvotes":9,
           "views":12,
           "timestamp":8791112,
                 "user":{
                      "firstname":"sushmita",
                      "emailaddress":"sushmita@gmail.com",
                      "imageurl":"https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/9b7e9421-72d6-4f22-9a66-3ab381efbb99/d53xf9q-275ea0d7-f50a-4d22-99c4-271b255798e2.jpg"
                      }
      
            }
            ],
      
      "comments":[
          {
           "comment":"helpful",
           "timestamp":65195122,
           "likes":6,
           "replies":[
              {
              "reply":6,
              "likes":9,
              "timestamp":5466344
              }
             ]
          }
        ],
      "user":{
           "firstname":"gaurav",
           "emailaddress":"gaurav@gmail.com",
           "imageurl":"https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/9b7e9421-72d6-4f22-9a66-3ab381efbb99/d53xf9q-275ea0d7-f50a-4d22-99c4-271b255798e2.jpg"
           }
      }
      ]
  //     {
  //       "questionId":1,
  //       "question": "What are some qqqof the important concepts in Angular?",
  //       "answers": [
  //         {
  //           "answer": "Some of the important concepts in angular are data binding and component interaction",
  //           "comments": [
  //             {
  //               "comment": "Can you mention1 other important topics ?",
  //               "timestamp": 1550166525,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "comment": "Can you mention other important topics ?",
  //               "timestamp": 1550166525,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Anushka",
  //             "emailaddress": "anushka@marvel.com",
  //             "imageurl": ""
  //           },
  //           "upvotes": 200,
  //           "downvote": 3,
  //           "views": 2000,
  //           "timestamp": 1550166527
  //         },
  //         {
  //           "answer": "Directives are one of the most important concepts of angular",
  //           "comments": [
  //             {
  //               "comment": "Are directives the most difficult ones ?",
  //               "timestamp": 1550166537,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "comment": "What about reactive programming ?",
  //               "timestamp": 1550166538,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Anushka",
  //             "emailaddress": "anushka@marvel.com",
  //             "imageurl": ""
  //           },
  //           "upvotes": 200,
  //           "downvote": 3,
  //           "views": 2000,
  //           "timestamp": 1550166527
  //         }
  //       ],
  
  //       "user": {
  //         "firstname": "Anjali",
  //         "emailaddress": "anjali@marvel.com",
  //         "imageurl": ""
  //       },
  //       "upvote": 3,
  //       "timestamp": 1550166528,
  //       "topics":["pipe1","pipe2"],
  //       "downvote": 0,
  //       "description": "Loresdk vd kndf v joiro iohoijeoi 0gieh ue vuehueieuhe rhi i i jibuh bk jb i j jhb ruhrc 45giu ug 4tgugiuhgtgniu gtgtgiut guthg uirhg iruh rtughiruhgrgh ig tgiurhgrug iurgh iurh viurg hriug rtugriutghiugh hiurtghiurg itugh htg guihg iutg",
  //       "comments": [
  //         {
  //           "comment": "Can you state the question more clearly ?",
  //           "timestamp": 1550166529,
  //           "likes": 10,
  //           "replies": [
  //             {
  //               "reply": "I think the details mentioned are enough to answer the question",
  //               "likes": 3,
  //               "timestamp": 1550166530,
  //               "user": {
  //                 "firstname": "Akanksha",
  //                 "emailaddress": "akanksha@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "reply": "Some details maybe missing",
  //               "likes": 3,
  //               "timestamp": 1550166536,
  //               "user": {
  //                 "firstname": "Prachi",
  //                 "emailaddress": "prachi@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Antara",
  //             "emailaddress": "antara@marvel.com",
  //             "imageurl": ""
  //           }
  //         },
  
  //         {
  //           "comment": "I think the question is not clear",
  //           "timestamp": 1550166531,
  //           "likes": 11,
  //           "replies": [
  //             {
  //               "reply": "I think the details mentioned suffice",
  //               "likes": 3,
  //               "timestamp": 1550166532,
  //               "user": {
  //                 "firstname": "Shalmali",
  //                 "emailaddress": "shalmali@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "reply": "Dude the question is crystal clear",
  //               "likes": 3,
  //               "timestamp": 1550166533,
  //               "user": {
  //                 "firstname": "Kajal",
  //                 "emailaddress": "kajal@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Aarushi",
  //             "emailaddress": "aarushi@marvel.com",
  //             "imageurl": ""
  //           }
  //         }
  //       ]
  //     },
  //     {
  //       "questionId":2,
  //       "question": "What are some of tweorrg f54oiumfh0vhe important concepts in Angular?",
  //       "answers": [
  //         {
  //           "answer": "Some of the important concepts in angular are data binding and component interaction",
  //           "comments": [
  //             {
  //               "comment": "Can you mention other important topics ?",
  //               "timestamp": 1550166525,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "comment": "Can you mention other important topics ?",
  //               "timestamp": 1550166525,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Anushka",
  //             "emailaddress": "anushka@marvel.com",
  //             "imageurl": ""
  //           },
  //           "upvotes": 200,
  //           "downvote": 3,
  //           "views": 2000,
  //           "timestamp": 1550166527
  //         },
  //         {
  //           "answer": "Directives are one of the most important concepts of angular",
  //           "comments": [
  //             {
  //               "comment": "Are directives the most difficult ones ?",
  //               "timestamp": 1550166537,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "comment": "What about reactive programming ?",
  //               "timestamp": 1550166538,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Anushka",
  //             "emailaddress": "anushka@marvel.com",
  //             "imageurl": ""
  //           },
  //           "upvotes": 200,
  //           "downvote": 3,
  //           "views": 2000,
  //           "timestamp": 1550166527
  //         }
  //       ],
  
  //       "user": {
  //         "firstname": "Anjali",
  //         "emailaddress": "anjali@marvel.com",
  //         "imageurl": ""
  //       },
  //       "upvote": 3,
  //       "timestamp": 1550166528,
  //       "downvote": 0,
  //       "description": "Loresdk vd kndf v joiro iohoijeoi 0gieh ue vuehueieuhe rhi i i jibuh bk jb i j jhb ruhrc 45giu ug 4tgugiuhgtgniu gtgtgiut guthg uirhg iruh rtughiruhgrgh ig tgiurhgrug iurgh iurh viurg hriug rtugriutghiugh hiurtghiurg itugh htg guihg iutg",
  //       "comments": [
  //         {
  //           "comment": "Can you state the question more clearly ?",
  //           "timestamp": 1550166529,
  //           "likes": 10,
  //           "replies": [
  //             {
  //               "reply": "I think the details mentioned are enough to answer the question",
  //               "likes": 3,
  //               "timestamp": 1550166530,
  //               "user": {
  //                 "firstname": "Akanksha",
  //                 "emailaddress": "akanksha@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "reply": "Some details maybe missing",
  //               "likes": 3,
  //               "timestamp": 1550166536,
  //               "user": {
  //                 "firstname": "Prachi",
  //                 "emailaddress": "prachi@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Antara",
  //             "emailaddress": "antara@marvel.com",
  //             "imageurl": ""
  //           }
  //         },
  
  //         {
  //           "comment": "I think the question is not clear",
  //           "timestamp": 1550166531,
  //           "likes": 11,
  //           "replies": [
  //             {
  //               "reply": "I think the details mentioned suffice",
  //               "likes": 3,
  //               "timestamp": 1550166532,
  //               "user": {
  //                 "firstname": "Shalmali",
  //                 "emailaddress": "shalmali@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "reply": "Dude the question is crystal clear",
  //               "likes": 3,
  //               "timestamp": 1550166533,
  //               "user": {
  //                 "firstname": "Kajal",
  //                 "emailaddress": "kajal@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Aarushi",
  //             "emailaddress": "aarushi@marvel.com",
  //             "imageurl": ""
  //           }
  //         }
  //       ]
  //     },
  //     {
  //       "questionId":3,
  //       "question": "What are scepmiroung8i5f6 hct7y6ome qqqof the important concepts in Angular?",
  //       "answers": [
  //         {
  //           "answer": "Some of the important concepts in angular are data binding and component interaction",
  //           "comments": [
  //             {
  //               "comment": "Can you mention other important topics ?",
  //               "timestamp": 1550166525,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "comment": "Can you mention other important topics ?",
  //               "timestamp": 1550166525,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Anushka",
  //             "emailaddress": "anushka@marvel.com",
  //             "imageurl": ""
  //           },
  //           "upvotes": 200,
  //           "downvote": 3,
  //           "views": 2000,
  //           "timestamp": 1550166527
  //         },
  //         {
  //           "answer": "Directives are one of the most important concepts of angular",
  //           "comments": [
  //             {
  //               "comment": "Are directives the most difficult ones ?",
  //               "timestamp": 1550166537,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "comment": "What about reactive programming ?",
  //               "timestamp": 1550166538,
  //               "likes": 10,
  //               "replies": [
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 },
  //                 {
  //                   "reply": "Why don't you google ?",
  //                   "likes": 3,
  //                   "timestamp": 1550166526,
  //                   "user": {
  //                     "firstname": "Akanksha",
  //                     "emailaddress": "akanksha@marvel.com",
  //                     "imageurl": ""
  //                   }
  //                 }
  //               ],
  //               "user": {
  //                 "firstname": "Antara",
  //                 "emailaddress": "antara@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Anushka",
  //             "emailaddress": "anushka@marvel.com",
  //             "imageurl": ""
  //           },
  //           "upvotes": 200,
  //           "downvote": 3,
  //           "views": 2000,
  //           "timestamp": 1550166527
  //         }
  //       ],
  
  //       "user": {
  //         "firstname": "Anjali",
  //         "emailaddress": "anjali@marvel.com",
  //         "imageurl": ""
  //       },
  //       "upvote": 3,
  //       "timestamp": 1550166528,
  //       "downvote": 0,
  //       "description": "Loresdk vd kndf v joiro iohoijeoi 0gieh ue vuehueieuhe rhi i i jibuh bk jb i j jhb ruhrc 45giu ug 4tgugiuhgtgniu gtgtgiut guthg uirhg iruh rtughiruhgrgh ig tgiurhgrug iurgh iurh viurg hriug rtugriutghiugh hiurtghiurg itugh htg guihg iutg",
  //       "comments": [
  //         {
  //           "comment": "Can you state the question more clearly ?",
  //           "timestamp": 1550166529,
  //           "likes": 10,
  //           "replies": [
  //             {
  //               "reply": "I think the details mentioned are enough to answer the question",
  //               "likes": 3,
  //               "timestamp": 1550166530,
  //               "user": {
  //                 "firstname": "Akanksha",
  //                 "emailaddress": "akanksha@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "reply": "Some details maybe missing",
  //               "likes": 3,
  //               "timestamp": 1550166536,
  //               "user": {
  //                 "firstname": "Prachi",
  //                 "emailaddress": "prachi@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Antara",
  //             "emailaddress": "antara@marvel.com",
  //             "imageurl": ""
  //           }
  //         },
  
  //         {
  //           "comment": "I think the question is not clear",
  //           "timestamp": 1550166531,
  //           "likes": 11,
  //           "replies": [
  //             {
  //               "reply": "I think the details mentioned suffice",
  //               "likes": 3,
  //               "timestamp": 1550166532,
  //               "user": {
  //                 "firstname": "Shalmali",
  //                 "emailaddress": "shalmali@marvel.com",
  //                 "imageurl": ""
  //               }
  //             },
  //             {
  //               "reply": "Dude the question is crystal clear",
  //               "likes": 3,
  //               "timestamp": 1550166533,
  //               "user": {
  //                 "firstname": "Kajal",
  //                 "emailaddress": "kajal@marvel.com",
  //                 "imageurl": ""
  //               }
  //             }
  //           ],
  //           "user": {
  //             "firstname": "Aarushi",
  //             "emailaddress": "aarushi@marvel.com",
  //             "imageurl": ""
  //           }
  //         }
  //       ]
  //     }
  //   ]

  //  }
    }
  ngOnInit() {
    console.log(this.questions);
  }

}
