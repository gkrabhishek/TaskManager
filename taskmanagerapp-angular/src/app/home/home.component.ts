import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { JwtClientService } from '../jwt-client.service';
import { AuthserviceService } from '../authservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
    lists:any;
    user:any;
    token:any;
    constructor(private service:JwtClientService,private auth:AuthserviceService,private router:Router,private cdr:ChangeDetectorRef){
   
    }
    ngOnInit(): void {
      console.log("Before home call")
        this.welcome();
     // this.listAllEmployees();
     this.listAllTask();
    this.cdr.detectChanges();
      
    }
    message:any;
    welcome(){
        const resp=this.service.welcome();
        resp.subscribe((data :any)=>{
          this.user=data['message'];
            console.log('welcome ',data['message']);
        },(err)=>{
          console.log(err);
        });
    }

    listAllTask(){
      const resp=this.service.getAllTask();
      resp.subscribe(data=>{
        this.lists=data;
        console.log(data)
       // this.cdr.detectChanges();
      },(err)=>{
        console.log(err)
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
    onLogout(){
      this.auth.removeToken();
      
      this.router.navigate(['']);
    }
}
