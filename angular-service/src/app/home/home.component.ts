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
     // Open connection with server socket
     let stompClient = this.webSocketService.connect();
     stompClient.connect({}, frame => {
      // let data: Array < any >= [];

   // Subscribe to notification topic
         stompClient.subscribe('/queue/'+this.token.getUsername(), notifications => {
 
     // Update notifications attribute with the recent messsage sent from the server
            this.data.push(notifications.body);
            console.log("note:"+this.data)
            //this.notification =this.notification+','+ notifications.body;  
            console.log(this.data.length);  
            
         })
     });
   

  }
  myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
  }
  

  logout() {
    this.token.signOut();
    window.location.reload();
  }
}
