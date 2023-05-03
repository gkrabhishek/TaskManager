import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SecurityComponent } from './security/security.component';
import {HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './login/login.component'
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { EmployeesListComponent } from './employees-list/employees-list.component';
import { EditTaskComponent } from './edit-task/edit-task.component';
import { SignupComponent } from './signup/signup.component';
import { EmployeeTaskMangerComponent } from './employee-task-manger/employee-task-manger.component';
import { AddTaskComponent } from './add-task/add-task.component';

@NgModule({
  declarations: [
    AppComponent,
    SecurityComponent,
    LoginComponent,
    HomeComponent,
    EmployeesListComponent,
    EditTaskComponent,
    SignupComponent,
    EmployeeTaskMangerComponent,
    AddTaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
