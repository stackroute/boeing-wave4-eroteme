import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registersuccess',
  templateUrl: './registersuccess.component.html',
  styleUrls: ['./registersuccess.component.css']
})
export class RegistersuccessComponent implements OnInit {

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,private router :Router,private dialog:MatDialog) { }

  ngOnInit() {
  }

  op(){
    this.dialog.closeAll();
    this.router.navigate(["/login"])
    }
}
