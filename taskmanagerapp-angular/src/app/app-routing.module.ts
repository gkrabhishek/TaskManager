import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { SignupComponent } from './signup/signup.component';
import { EmployeesListComponent } from './employees-list/employees-list.component';
import { EmployeeTaskMangerComponent } from './employee-task-manger/employee-task-manger.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { AuthserviceService } from './authservice.service';
import { AuthManagerService } from './auth-manager.service';

const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'home',component:HomeComponent,canActivate:[AuthserviceService]},
  {path:'employee/task/:id/update',component:EditTaskComponent,canActivate:[AuthserviceService]},//use this in both user and manager
  {path:'empolyees-list',component:EmployeesListComponent,canActivate:[AuthManagerService]},
  {path:'employee/task/manager',component:EmployeeTaskMangerComponent,canActivate:[AuthManagerService]},
  {path:'addTask',component:AddTaskComponent,canActivate:[AuthManagerService]},
  {path:'**',redirectTo:''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
