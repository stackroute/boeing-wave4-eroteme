import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnviormentVariabelsService {

  trendingQuesGuest:string;

  trendingQuesLoggedIn:string;

  unansweredQuesGuest:string;

  unansweredQuesLoggedin:string;

  acceptedAnswersLoggedIn:string;

  searchServiceQuestion:string;

  User:string;
  
  constructor() {
    
    this.trendingQuesGuest="http://52.66.134.21:8081/guest/trending";

    this.trendingQuesLoggedIn="http://52.66.134.21:8081/member/trending?username=";

    this.unansweredQuesGuest="http://52.66.134.21:8081/guest/unanswered";

    this.unansweredQuesLoggedin="http://52.66.134.21:8081/member/unanswered/";

    this.acceptedAnswersLoggedIn="http://52.66.134.21:8081/member/acceptedanswers?username=";
  
    this.User="http://52.66.134.21:8091/getall/";

    this.searchServiceQuestion="http://52.66.134.21:8099/api/v1/relevant";

   }
}
