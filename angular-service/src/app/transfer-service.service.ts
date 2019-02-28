import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransferServiceService {
  

  value;

  searchValue;

  unans;
  
  constructor(private http:HttpClient) { }


  loaditems() {
    return this.http.get("http://52.66.134.21:8050/trendingQues");
}


loaditems1() {
  return this.http.get("http://52.66.134.21:8051/unansweredQues");
}

loaditems2() {
  return this.http.get("http://52.66.134.21:8052/User");
}

}
