import { Component, OnInit, OnChanges } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.css']
})
export class ParentComponent implements OnInit, OnChanges{

  variable: any;

  constructor(private trans: TransferServiceService) {

  }

  ngOnInit() {
    this.trans.GetSearchResult().subscribe((data) => {
      console.log(data);
      //console.log("inside parent");
      //console.log(data[0].questions, "Data taht we r getting form the backend");
      // console.log(data[0]["questions"]);
      this.variable = data[0].questions;
      console.log("this is variable", this.variable);
    }) 
  }

  ngOnChanges() {
   
  }
}
