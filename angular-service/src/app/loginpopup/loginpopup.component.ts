import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-loginpopup',
  templateUrl: './loginpopup.component.html',
  styleUrls: ['./loginpopup.component.css']
})
export class LoginpopupComponent implements OnInit {

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,private router :Router,private dialog:MatDialog) { }

  ngOnInit() {
  }

  op(){
  this.dialog.closeAll();
  this.router.navigate(["/login"])
  }



}
