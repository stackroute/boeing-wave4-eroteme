import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-postalert',
  templateUrl: './postalert.component.html',
  styleUrls: ['./postalert.component.css']
})
export class PostalertComponent implements OnInit {

  constructor( private router :Router,private dialog:MatDialog) { }

  ngOnInit() {
  }


  op(){
    this.dialog.closeAll();
    this.router.navigate(["/profile"])
    }

}
