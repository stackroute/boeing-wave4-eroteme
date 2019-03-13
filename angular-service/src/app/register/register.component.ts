import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';
import { FormControl, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup:FormGroup;
  isOptional = true;


  form: any = {};
  signupInfo: SignUpInfo;
  errorMessage = '';
  pass:string='';
  conf:string;

  // FirstName:String;
  // FirstName = new FormControl('', [Validators.required]);
  // LastName=new FormControl('');
  // email = new FormControl('', [Validators.required, Validators.email]);
  // password=new FormControl('', [Validators.required]);
  // confirm=new FormControl('',[Validators.required]);
  // toppings = new FormControl();
  toppingList: string[] = ['DataBinding', 'Pipes', 'Forms', 'Navigation', 'TypeScript', 'Testing','FundamentalArchitecture'];


  constructor(private authService: AuthService,private route:Router,private _formBuilder: FormBuilder,private dialog:MatDialog) { }
  getErrorFnameMessage(){
    // return this.firstFormGroup.controls.FirstName.hasError('required') ? 'You must enter a value':'';
  }
 
  getpassErrorMessage(){
    //return this.password.hasError('required') ? 'You must enter a value': '';
  }
 
  getconfirmErrorMessage(){
  //  if(this.password.value!=this.confirm.value) {
  //  return "Password Not Matching";
   // }
  }
 
  // getErrorMessage() {
  //   return this.email.hasError('required') ? 'You must enter a value' :
  //       this.email.hasError('email') ? 'Not a valid email' :
  //           '';
  // }

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      FirstName: ['', Validators.required],
       LastName: ['', Validators.required]
    });
    this.secondFormGroup = this._formBuilder.group({
      email:['',Validators.email],
     password:['',Validators.minLength(8)]
    });
    this.thirdFormGroup = this._formBuilder.group({
        toppings:['']
       });

   }  

   onSubmit(){

     this.pass= this.secondFormGroup.controls.password.value;
     this.conf= this.secondFormGroup.controls.confirm.value;
   }
  
  register():void {
    console.log(this.form);

    this.signupInfo = new SignUpInfo(
      // this.email.value,
      // this.password.value,
        // this.FirstName.value,
         // this.LastName.value,
      // this.toppings.value 
      this.secondFormGroup.controls.email.value,
      this.secondFormGroup.controls.password.value,
      this.firstFormGroup.controls.FirstName.value,
      this.firstFormGroup.controls.LastName.value,
      this.thirdFormGroup.controls.toppings.value
        );

      console.log(this.firstFormGroup.controls.FirstName.value);
      console.log(this.firstFormGroup.controls.LastName.value);
      console.log(this.secondFormGroup.controls.email.value);
      console.log(this.secondFormGroup.controls.password.value);
      console.log(this.thirdFormGroup.controls.toppings.value);





    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        alert("Registered Successfully!!!");
        this.route.navigate(["/login"]);

      },
      error => {
            if(error.status==201){
                      alert("Registered Successfully!!!");
                      this.dialog.closeAll();
                      this.route.navigate(["/login"]);
            }
        console.log(error);
        this.errorMessage = error.error.message;
      }
    );
  }
  backToLogin()
  {
    this.route.navigate([""]);
  }

}
