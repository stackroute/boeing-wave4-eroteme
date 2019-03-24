import{Injectable}from'@angular/core';
import {HttpClient, HttpHeaders}from '@angular/common/http';
import {Observable}from 'rxjs';
import {JwtResponse}from './jwt-response';
import {AuthLoginInfo} from './login-info';
import { SignUpInfo}from './signup-info';

const httpOptions = {
headers: new HttpHeaders({ 'Content-Type': 'application/json',
'Access-Control-Allow-Origin':'*'

})
};


const httpOptionsForRegistration={
  headers: new HttpHeaders({ 'Content-Type': 'text/html',
'Access-Control-Allow-Origin' :'*'

})

}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8092/user-authentication-service/api/auth/signin';
  private signupUrl = 'http://localhost:8092/user-profile-service/api/v1/signup';
  
  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    console.log("inside the service ");
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo){
    return this.http.post(this.signupUrl, info, httpOptionsForRegistration);
  }
  
}
