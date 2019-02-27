import { WebSocketService } from './../web-socket-service.service';
import { Component, OnInit,Input } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input()
  check1:String;

  value:String;

  info: any;
  
  public data: Array < any >= [];

  constructor(private trans:TransferServiceService,private token: TokenStorageService,private authService: AuthService,private router :Router,private webSocketService:WebSocketService) { }


  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      email: this.token.getUsername()
    };
    // Open connection with server socket
    let stompClient = this.webSocketService.connect();
    stompClient.connect({}, frame => {
     

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
  

  putSearchVal(){
    this.trans.searchValue=this.value;
  }

  logout() {
    this.token.signOut();
    window.location.reload();
    this.router.navigate([""]);
  }


}
