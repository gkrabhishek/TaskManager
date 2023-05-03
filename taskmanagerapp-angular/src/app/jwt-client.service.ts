import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthserviceService } from './authservice.service';
import { BehaviorSubject, Subject } from 'rxjs';
import { JsonPipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  // private editTaskSource=new Subject<Object>();
  // editTask$=this.editTaskSource.asObservable();
   
  constructor(private http:HttpClient ,private auth :AuthserviceService) { 
    
  }

  //task=new BehaviorSubject<any>(null);
  sendTask(message:string){
    const dataJson=JSON.stringify(message);
    localStorage.setItem("task",dataJson);
    // this.task.next(message);
    // localStorage.setItem("task",JSON.stringify(message));
  }
  getTask():any{
    const data:any=localStorage.getItem("task")
    return JSON.parse(data);
    // return this.task.asObservable();
  }

  setEmployee(employee:any){
    
    localStorage.setItem("employee",JSON.stringify(employee));
  }
  getEmployee(){
    return localStorage.getItem("employee");
  }

  public generateToken(request:any){
    console.log("from jwt",request)
    return  this.http.post("http://localhost:8081/user/login",request,{responseType:'text' as 'json'});
  
  }

  public welcome(){
    const token = this.auth.getToken();
    
    let tokenStr='Bearer '+token;
    console.log(tokenStr)

    
    //const headers=new HttpHeaders().set("Authorization",tokenStr)
    
    return this.http.get("http://localhost:8081/user/home",{
      headers: new HttpHeaders().set("Authorization",tokenStr)
    });
  }


  public getAllTask(){
    const token:any=this.auth.getToken();
    let tokenStr='Bearer '+token;
    let currentUser=this.auth.extractPayloadFromToken(token);
    const headers=new HttpHeaders().set("Authorization",tokenStr);

    if(this.auth.getEmployeeRole()=='manager'){
      const employeeString:any=this.getEmployee();
      const employee=JSON.parse(employeeString);
      currentUser=employee['email'];
      console.log("current user changed by manager ", currentUser);
    } 


    console.log('header ',token)
    return this.http.get("http://localhost:8081/user/"+currentUser+"/getAllTasks",{
      headers:headers});
    // return this.http.get("http://localhost:8081/user/"+currentUser+"/getAllTasks");
  }


  public updateTask(id:number,data:any){
    console.log("id ",id);
    console.log("data ",data)
    const token:any=this.auth.getToken();
    let tokenStr='Bearer '+token;
    const headers=new HttpHeaders().set("Authorization",tokenStr);
    return this.http.patch("http://localhost:8081/user/task/"+id+"/update",data,{
      headers:headers})
  }


  public onDeleteTask(id:number){
    const token:any=this.auth.getToken();
    let tokenStr='Bearer '+token;
    const headers=new HttpHeaders().set("Authorization",tokenStr);
    return this.http.delete("http://localhost:8081/user/task/"+id+"/delete",{
      headers:headers
    })
  }

  public signup(newUser:any){
      return this.http.post("http://localhost:8081/user/signup",newUser,{responseType:'text' as 'json'})
  }

  public getAllEmployees(){
    const token :any=this.auth.getToken();
    let tokenStr='Bearer '+token;
    const headers=new HttpHeaders().set("Authorization",tokenStr);
    return this.http.get("http://localhost:8081/user/getUsers",{headers:headers})
  }

  public addTask(task:any){
    const token:any=this.auth.getToken();
    let tokenStr='Bearer '+token;
    const headers=new HttpHeaders().set("Authorization",tokenStr);
    const employeeString:any=this.getEmployee();
    const employee=JSON.parse(employeeString);
    const id:any=employee['userId'];
    console.log("id=",id)
    return this.http.post("http://localhost:8081/user/"+id+"/addTask",task,{headers:headers})
  }
 
}
