import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homejumbotron',
  templateUrl: './homejumbotron.component.html',
  styleUrls: ['./homejumbotron.component.css']
})
export class HomejumbotronComponent implements OnInit {


  modal:boolean;
  toggle:String;
  constructor(private router :Router) { }

  ngOnInit() {
    this.modal=false;
    this.toggle='Know More';
  }

  show(){
    if(this.modal==false) {
    this.modal=true;
    this.toggle='Show less'
  }
    else{
      this.toggle='Know More';
    this.modal=false; }  

  }

}
