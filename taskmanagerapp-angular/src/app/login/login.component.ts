import { Component } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';
import { Router } from '@angular/router';
import { AuthserviceService } from '../authservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  mylogindata:any;

  constructor(private service:JwtClientService,private router:Router,private auth:AuthserviceService){

  }

  onSubmit(ref:any){
    this.mylogindata=ref.value.userdata;
    console.log(ref.value.userdata)
    console.log(ref)
    const resp=this.service.generateToken(this.mylogindata);
  
    resp.subscribe((data:any)=>{
      this.auth.setToken(data);
      this.auth.extractPayloadFromToken(data);
      console.log('role ',this.auth.getEmployeeRole());
      console.log("token",data);
      if(this.auth.getEmployeeRole()==='user')
        this.router.navigate(['/home'])
      else{
        this.router.navigate(['/empolyees-list'])
      }
      
    },(err)=>{
      console.log(err)
    },()=>{
      //this.router.navigate(['/home'])
    }
    );
  }
}
