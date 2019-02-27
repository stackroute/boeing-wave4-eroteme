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
    return this.http.get("http://localhost:8050/trendingQues");
}


loaditems1() {
  return this.http.get("http://localhost:8051/unansweredQues");
}

loaditems2() {
  return this.http.get("http://localhost:8052/User");
}

}
