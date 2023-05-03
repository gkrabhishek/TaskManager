import { Component } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';
import { Router } from '@angular/router';
import { JsonPipe } from '@angular/common';
import { AuthserviceService } from '../authservice.service';

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrls: ['./employees-list.component.css']
})
export class EmployeesListComponent {
  employees:any;
  constructor(private service:JwtClientService,private router:Router,private auth:AuthserviceService){
   const resp= this.service.getAllEmployees();
   resp.subscribe((data)=>{
    this.employees=data;
   })
  }
  editTask(tasks:any){
      console.log(tasks)
      this.service.setEmployee(tasks);
      this.router.navigate(['/employee','task','manager']);
      console.log('from serivce',JSON.parse(this.service.getEmployee()|| '{}'));
  }
  onLogout(){
    this.auth.removeToken();
    this.router.navigate(['']);
  }
}
