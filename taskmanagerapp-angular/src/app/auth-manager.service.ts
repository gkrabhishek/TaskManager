import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtClientService } from './jwt-client.service';
import { AuthserviceService } from './authservice.service';

@Injectable({
  providedIn: 'root'
})
export class AuthManagerService implements CanActivate{

  constructor(private auth:AuthserviceService,private router:Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if(this.auth.isLoggedIn()&&this.auth.getEmployeeRole()==='manager'){
      return true;
    }
    this.router.navigate(['']);
    return false;
  }
}
