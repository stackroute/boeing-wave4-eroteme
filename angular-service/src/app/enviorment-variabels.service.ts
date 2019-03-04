import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnviormentVariabelsService {

  trendingQues:string;

  unansweredQues:string;

  User:string;
  
  constructor() {
    this.trendingQues="http://localhost:8050/trendingQues";

    this.unansweredQues="http://localhost:8051/unansweredQues";
  
    this.User="http://localhost:8052/User";
   }
   
}
