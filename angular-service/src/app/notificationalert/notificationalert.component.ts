import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notificationalert',
  templateUrl: './notificationalert.component.html',
  styleUrls: ['./notificationalert.component.css']
})
export class NotificationalertComponent implements OnInit {

  constructor(private router :Router,private dialog:MatDialog) { }

  ngOnInit() {
  }

  op(){
    this.dialog.closeAll();
    this.router.navigate(["/profile"])
    }
  

}
