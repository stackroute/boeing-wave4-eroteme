import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TransferServiceService } from '../transfer-service.service';


@Component({
  selector: 'app-notify',
  templateUrl: './notify.component.html',
  styleUrls: ['./notify.component.css']
})
export class NotifyComponent implements OnInit {
  @Input() notification:string;
  constructor(private router :Router,private http:HttpClient,private trans:TransferServiceService) { }

  ngOnInit() {
  }
  

  mydata;
  navigate(){
    //do your any operations
    let note=this.notification.split('\"');
    console.log(note[1]);
    console.log("making get request");
    this.http.get("http://localhost:8092/question-answer-service/api/v1/"+note[1]).subscribe(
      (data) => {
        console.log("POST Request is successful ", data);
        this.mydata=data;
      },
      (error) => {

        console.log("Error", error);
        this.mydata=error;
        console.log(this.mydata.error);
        this.router.navigate(['/questionAnswerCard']);
        this.trans.value=this.mydata.error;

      });
      console.log("ggg ",this.mydata);
  }
  

}
