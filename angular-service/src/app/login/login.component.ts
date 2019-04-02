import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthLoginInfo } from '../auth/login-info';
import { Router } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { RegisterComponent } from './../register/register.component';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  private loginInfo: AuthLoginInfo;
  email=new FormControl('',[Validators.required]);
  password=new FormControl('',Validators.required);

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService,private router :Router,private dialog:MatDialog) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
    }
  }


  open(){
    this.dialog.open(RegisterComponent);
  }
  
  validate() {
    console.log("nnn ",this.form);

    this.loginInfo = {
      "email":this.email.value,
     "password": this.password.value
    }

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        console.log("data  is ",data);
        console.log("token is  ",data.accessToken);
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.email);
        this.isLoginFailed = false;
        this.isLoggedIn = true;          
        this.home();
        window.location.reload();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  signup(){
    this.router.navigate(["/register"]);
  }

  home(){
    console.log("reloaded after login")

    this.router.navigate(['']);  
  }

  getMyStyles() {
    let myStyles = {
       'background-image': 'url(\'assets/images/trust-tru-katsande-333658-unsplash.jpg \')'
    };
    return myStyles;

}
}
