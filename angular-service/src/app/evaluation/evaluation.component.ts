import{Component, OnInit, OnChanges}from '@angular/core';
import {TransferServiceService}from '../transfer-service.service';
import { Router}from '@angular/router';

@Component({
selector: 'app-evaluation',
templateUrl: './evaluation.component.html',
styleUrls: ['./evaluation.component.css']
})
export class EvaluationComponent implements OnInit {
response;

constructor(private trans:TransferServiceService,private route:Router ) { }

  ngOnInit() {
    this.trans.result.subscribe((data)=>{
      this.response=data;
      console.log("mmm\n",data);
      console.log("jjj\n",this.response);
    }
    );

  }

  redirectToHomePage(){
    this.route.navigate(['/']);

  }

  


}
