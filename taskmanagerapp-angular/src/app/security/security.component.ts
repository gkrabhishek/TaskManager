import { Component, OnInit } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';

@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css']
})
export class SecurityComponent implements OnInit{

  authRequest:any={
    "email":"abhishek@gmail.com",
    "password":"123"
};

  response:any; 

  constructor(private sevice:JwtClientService){}
  ngOnInit(): void {
   // this.getAccessToken(this.authRequest);
  }

  // public getAccessToken(authRequest:any){
  //   let resp=this.sevice.generateToken(authRequest);
  //  // resp.subscribe(data=>console.log("Token :"+data));
  //  resp.subscribe(data=>{
  //   console.log(data)
  //   this.accessApi(data)})
  // }


  // public accessApi(token:any){
  //   let resp=this.sevice.welcome(token);
  //   resp.subscribe(data=>this.response=data);
  // }
}
