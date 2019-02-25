import { Component, OnInit } from '@angular/core';

import { TokenStorageService } from '../auth/token-storage.service';
import { WebSocketService } from '../web-socket-service.service';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info: any;
  public notifications = "";
  public notification = "";
  errorMessage = '';
  public data: Array < any >= [];
  constructor(private token: TokenStorageService,private authService: AuthService,private webSocketService: WebSocketService) {}
  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      email: this.token.getUsername() 
    };
    

  }
  myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
  }
  

  logout() {
    this.token.signOut();
    window.location.reload();
  }
}
