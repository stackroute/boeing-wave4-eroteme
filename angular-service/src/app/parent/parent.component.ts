import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.css']
})
export class ParentComponent implements OnInit {

  variable;

  constructor() {
    this.variable = {
      "topic": "Angular",
      "questions": [
        {
          "question": "What is Springboot?",
          "description": "Spring Boot is a Framework from “The Spring Team” to ease the bootstrapping and development of new Spring Applications.",
          "upvotes": 3,
          "timestamp": 456789,
          "downvote": 3,
          "answers": [
            {
              "answer": "Angular is a platform that makes it easy to build applications with the web. Angular combines declarative templates, dependency injection, end to end tooling, and integrated best practices to solve development challenges.",
              "accepted": "true",
              "comments": [
                {
                  "comment": "okay answer",
                  "timestamp": 234567,
                  "likes": 5,
                  "replies": [
                    {
                      "reply": "elaborate more",
                      "likes": 2,
                      "timestamp": 789065,
                      "user": {
                        "firstname": "Zakir",
                        "emailaddress": "zakir@gmail.com",
                        "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                      }
                    }
                  ],
                  "user": {
                    "firstname": "zakir",
                    "emailaddress": "zakir@gmail.com",
                    "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                  }
                }
              ],
              "upvotes": 6,
              "views": 2,
              "timestamp": 6789023,
              "user": {
                "firstname": "veena",
                "emailaddress": "veena@gmail.com",
                "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
              }

            }
          ],

          "comments": [
            {
              "comment": "helpful",
              "timestamp": 456789,
              "likes": 1,
              "replies": [
                {
                  "reply": 2,
                  "likes": 3,
                  "timestamp": 234567
                }
              ]
            }
          ],
          "user": {
            "firstname": "Reshma",
            "emailaddress": "reshma@gmail.com",
            "imageurl": "https://www.w3schools.com/angular/pic_angular.jpg"
          }
        },


        {
          "question": "Why Springboot must be used?",
          "description": "Spring framework provides a wide verity of features addressing the modern business needs via its portfolio projects.",
          "upvotes": 3,
          "timestamp": 678905,
          "downvote": 2,
          "answers": [
            {
              "answer": "Spring Boot is a brand new framework from the team at Pivotal, designed to simplify the bootstrapping and development of a new Spring application.",
              "accepted": "true",
              "comments": [
                {
                  "comment": "okay answer",
                  "timestamp": 234567,
                  "likes": 5,
                  "replies": [
                    {
                      "reply": "elaborate more",
                      "likes": 2,
                      "timestamp": 789065,
                      "user": {
                        "firstname": "Zakir",
                        "emailaddress": "zakir@gmail.com",
                        "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                      }
                    }
                  ],
                  "user": {
                    "firstname": "zakir",
                    "emailaddress": "zakir@gmail.com",
                    "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                  }
                }
              ],
              "upvotes": 6,
              "views": 2,
              "timestamp": 6789023,
              "user": {
                "firstname": "sajal",
                "emailaddress": "sajal@gmail.com",
                "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
              }

            }
          ],

          "comments": [
            {
              "comment": "helpful",
              "timestamp": 456789,
              "likes": 1,
              "replies": [
                {
                  "reply": 2,
                  "likes": 3,
                  "timestamp": 234567
                }
              ]
            }
          ],
          "user": {
            "firstname": "Reshma",
            "emailaddress": "reshma@gmail.com",
            "imageurl": "https://www.w3schools.com/angular/pic_angular.jpg"
          }

        },

        {
          "question": "Why Springboot must be used?",
          "description": "Spring framework provides a wide verity of features addressing the modern business needs via its portfolio projects.",
          "upvotes": 3,
          "timestamp": 678905,
          "downvote": 2,
          "answers": [
            {
              "answer": "Spring Boot is a brand new framework from the team at Pivotal, designed to simplify the bootstrapping and development of a new Spring application.",
              "accepted": "true",
              "comments": [
                {
                  "comment": "okay answer",
                  "timestamp": 234567,
                  "likes": 5,
                  "replies": [
                    {
                      "reply": "elaborate more",
                      "likes": 2,
                      "timestamp": 789065,
                      "user": {
                        "firstname": "Zakir",
                        "emailaddress": "zakir@gmail.com",
                        "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                      }
                    }
                  ],
                  "user": {
                    "firstname": "zakir",
                    "emailaddress": "zakir@gmail.com",
                    "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                  }
                }
              ],
              "upvotes": 6,
              "views": 2,
              "timestamp": 6789023,
              "user": {
                "firstname": "sajal",
                "emailaddress": "sajal@gmail.com",
                "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
              }

            }
          ],

          "comments": [
            {
              "comment": "helpful",
              "timestamp": 456789,
              "likes": 1,
              "replies": [
                {
                  "reply": 2,
                  "likes": 3,
                  "timestamp": 234567
                }
              ]
            }
          ],
          "user": {
            "firstname": "Reshma",
            "emailaddress": "reshma@gmail.com",
            "imageurl": "https://www.w3schools.com/angular/pic_angular.jpg"
          }

        },

        {
          "question": "Why Springboot must be used?",
          "description": "Spring framework provides a wide verity of features addressing the modern business needs via its portfolio projects.",
          "upvotes": 3,
          "timestamp": 678905,
          "downvote": 2,
          "answers": [
            {
              "answer": "Spring Boot is a brand new framework from the team at Pivotal, designed to simplify the bootstrapping and development of a new Spring application.",
              "accepted": "true",
              "comments": [
                {
                  "comment": "okay answer",
                  "timestamp": 234567,
                  "likes": 5,
                  "replies": [
                    {
                      "reply": "elaborate more",
                      "likes": 2,
                      "timestamp": 789065,
                      "user": {
                        "firstname": "Zakir",
                        "emailaddress": "zakir@gmail.com",
                        "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                      }
                    }
                  ],
                  "user": {
                    "firstname": "zakir",
                    "emailaddress": "zakir@gmail.com",
                    "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                  }
                }
              ],
              "upvotes": 6,
              "views": 2,
              "timestamp": 6789023,
              "user": {
                "firstname": "sajal",
                "emailaddress": "sajal@gmail.com",
                "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
              }

            }
          ],

          "comments": [
            {
              "comment": "helpful",
              "timestamp": 456789,
              "likes": 1,
              "replies": [
                {
                  "reply": 2,
                  "likes": 3,
                  "timestamp": 234567
                }
              ]
            }
          ],
          "user": {
            "firstname": "Reshma",
            "emailaddress": "reshma@gmail.com",
            "imageurl": "https://www.w3schools.com/angular/pic_angular.jpg"
          }

        },


        {
          "question": "Explain ng-bind directive.",
          "description": "The ng-bind directive tells AngularJS to replace the content of an HTML element with the value of a given variable, or expression. If the value of the given variable, or expression, changes, the content of the specified HTML element will be changed as well.",
          "upvotes": 3,
          "timestamp": 678905,
          "downvote": 2,
          "answers": [
            {
              "answer": "The ng-bind directive tells AngularJS to replace the content of an HTML element with the value of a given variable, or expression. If the value of the given variable, or expression, changes, the content of the specified HTML element will be changed as well.",
              "accepted": "true",
              "comments": [
                {
                  "comment": "okay answer",
                  "timestamp": 234567,
                  "likes": 5,
                  "replies": [
                    {
                      "reply": "elaborate more",
                      "likes": 2,
                      "timestamp": 789065,
                      "user": {
                        "firstname": "Zakir",
                        "emailaddress": "zakir@gmail.com",
                        "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                      }
                    }
                  ],
                  "user": {
                    "firstname": "zakir",
                    "emailaddress": "zakir@gmail.com",
                    "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
                  }
                }
              ],
              "upvotes": 6,
              "views": 2,
              "timestamp": 6789023,
              "user": {
                "firstname": "sajal",
                "emailaddress": "sajal@gmail.com",
                "imageurl": "https://i.ebayimg.com/images/g/vlUAAOxyx0JTf9Cg/s-l300.jpg"
              }

            }
          ],

          "comments": [
            {
              "comment": "helpful",
              "timestamp": 456789,
              "likes": 1,
              "replies": [
                {
                  "reply": 2,
                  "likes": 3,
                  "timestamp": 234567
                }
              ]
            }
          ],
          "user": {
            "firstname": "Reshma",
            "emailaddress": "reshma@gmail.com",
            "imageurl": "https://www.w3schools.com/angular/pic_angular.jpg"
          },

        }
      ]
    }
  }

  ngOnInit(){}
}
