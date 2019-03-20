import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnviormentVariabelsService } from './enviorment-variabels.service';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransferServiceService {

  value;

  searchValue;

  unans;

  public result:any= new Subject();


constructor(private http:HttpClient,private env:EnviormentVariabelsService) { }


  GuestTrendingQues() {
    return this.http.get(this.env.trendingQuesGuest);
}

  LoggedInTrendingQues(emailid){
    return this.http.get(this.env.trendingQuesLoggedIn+emailid);
  }

  GuestUnansweredQues(){
   return this.http.get(this.env.unansweredQuesGuest);
  }

  LoggedInUnansweredQues(emailid){
    return this.http.get(this.env.unansweredQuesLoggedin+emailid);
  }

  LoggedInAcceptedQues(emailid){
    return this.http.get(this.env.acceptedAnswersLoggedIn+emailid)
  }

  GetUserData(emailid) {
    return this.http.get(this.env.User+emailid);
  }

  GetSearchResult(){
  return this.http.get(this.env.searchServiceQuestion);
}

}
