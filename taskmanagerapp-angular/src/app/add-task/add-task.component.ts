import { Component } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {
  constructor(private service:JwtClientService,private location:Location)
  {

  }
  addTask(ref:any){
    console.log(ref);
    console.log(ref.value.task);
    const resp=this.service.addTask(ref.value.task);
    resp.subscribe((data)=>{
        console.log(data);
      this.location.back();
    },(error)=>{
      console.log(error)
    })
  }
  onCancel(){
    this.location.back();
  }
}
