import { Component, OnInit } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
  public message:any;
  success=false;
  error=false;

  constructor(private service:JwtClientService,private location:Location){
    
  }
  ngOnInit(): void {
    this.success=false;
    this.error=false;
  }

  onSubmit(data:any){
    this.success=false;
    this.error=false;
      console.log(data.value.newUser)
      const resp=this.service.signup(data.value.newUser);
      resp.subscribe((data:any)=>{ 
        this.success=true;
        this.message=JSON.parse(data);
      },(error)=>{
        this.error=true;
        this.message=JSON.parse(error.error);
      },()=>{
        this.success=false;
        this.error=false;
        this.ngOnInit();
      })
  }
  onCancel(){
    this.location.back();
  }
}
