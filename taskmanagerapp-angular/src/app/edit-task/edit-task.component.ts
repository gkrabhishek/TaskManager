import { Component, Input, OnInit } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit{
  
  public task:any;
  constructor(private service:JwtClientService,private route:ActivatedRoute,private location:Location){
    this.task=service.getTask();
    // const resp=service.getTask();
    // resp.subscribe((data:any)=>{
    //   console.log(data);  
    //   this.task=data;
    // })
  }


  ngOnInit(): void {
    console.log("this",this.task)
  }
  updateTask(data:any){
      console.log(data.value.task)
      console.log(this.route.snapshot.params['id'])
      const id=parseInt(this.route.snapshot.params['id']);
      const resp=this.service.updateTask(id,data.value.task);
      resp.subscribe((data:any)=>{
        this.service.sendTask(data);
        console.log(data);
        this.location.back();
      },()=>{
        this.location.back();
      })
  }
  onCancel(){
    this.location.back();
  }
}
