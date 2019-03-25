import { RegisterComponent } from './../register/register.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { WebSocketService } from './../web-socket-service.service';
import { Component, OnInit, Input } from '@angular/core';
import { TransferServiceService } from '../transfer-service.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input()
  check1: String;

  value: String;

  length1: number;
  length2: number = 0;

  info: any;

  public data: Array<any> = [];

  constructor(private trans: TransferServiceService, private token: TokenStorageService, private authService: AuthService, private router: Router, private webSocketService: WebSocketService, private http: HttpClient,private dialog:MatDialog) { }
  str;

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      email: this.token.getUsername()
    };
    // Open connection with server socket
    let stompClient = this.webSocketService.connect();
    stompClient.connect({}, frame => {


      // Subscribe to notification topic
      stompClient.subscribe('/queue/' + this.token.getUsername(), notifications => {

        // Update notifications attribute with the recent messsage sent from the server
        // let arr = new Array<any>(2);
        this.data.push(notifications.body);
        console.log("length is " + this.data.length);
        if(this.data.length>0){
          this.str='!';
        }
      })
    });
    //hits the controller of notification service to recieve notifications
        console.log("hit the controller")
        setTimeout( () => { /*Your Code*/
        this.http.get("http://localhost:8010/api/v1/"+this.token.getUsername()).subscribe((data2)=>{
          console.log(data2);
          },error=>{
          console.log(error);
        });
      }, 2000 );

  }

  myfunction() {
    this.str='';
  }

  open(){

      this.dialog.open(RegisterComponent);
  }

  putSearchVal() {
    this.trans.searchValue = this.value;
    console.log("abc    "+this.value);
    this.http.post("http://localhost:8070/api/v1/"+this.value,{});
      //this.router.navigate(["/searchresult"]);
      this.router.navigateByUrl('/myprofile', {skipLocationChange: true}).then(()=>
      this.router.navigate(["/searchresult"]));

  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }


}
