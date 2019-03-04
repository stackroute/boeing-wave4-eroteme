import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnviormentVariabelsService } from './enviorment-variabels.service';

@Injectable({
  providedIn: 'root'
})
export class TransferServiceService {

  value;

  searchValue;

  unans;
  
  constructor(private http:HttpClient,private env:EnviormentVariabelsService) { }


  loaditems() {
    return this.http.get(this.env.trendingQues);
}


loaditems1() {
  return this.http.get(this.env.unansweredQues);
}

loaditems2() {
  return this.http.get(this.env.User);
}

}
