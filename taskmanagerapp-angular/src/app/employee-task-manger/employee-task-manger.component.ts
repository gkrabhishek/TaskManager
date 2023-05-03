import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-task-manger',
  templateUrl: './employee-task-manger.component.html',
  styleUrls: ['./employee-task-manger.component.css']
})
export class EmployeeTaskMangerComponent implements OnInit,OnChanges{
  lists:any;
  constructor(private service:JwtClientService,private router:Router){

    const resp=service.getAllTask();
    resp.subscribe((data)=>{
      this.lists=data;
    },(e)=>{
      console.log(e);
    })

    // const employee:any=service.getEmployee();
    // this.lists=JSON.parse(employee);
    // this.lists=this.lists['tasks'];
    // console.log(typeof employee)
    // console.log("inside task manager ",this.lists);
  }
  ngOnChanges(changes: SimpleChanges): void {
    
  }
  ngOnInit(): void {
    const resp=this.service.getAllTask();
    resp.subscribe((data)=>{
      this.lists=data;
    },(e)=>{
      console.log(e);
    })
  }

  
  myFunc(data:any){
    console.log("my data",data);
    this.service.sendTask(data);
    this.router.navigate(['/employee/task/',data.id,'update'])
}

onDelete(id:number){
  const resp=this.service.onDeleteTask(id);
  resp.subscribe((data)=>{
    console.log('ondelete ',data);
  },(error)=>{
    console.log(error)
  },()=>{
    this.ngOnInit();
  })
}

addTask(){
  this.router.navigate(['\addTask'])
}


}
