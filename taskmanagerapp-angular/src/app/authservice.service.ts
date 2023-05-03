import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthserviceService implements CanActivate{

  constructor(private router:Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if(this.isLoggedIn()){
      return true;
    }
    this.router.navigate([''])
    return false;
  }
  private TOKEN_KEY = 'my_app_token';

  setToken(token: any) {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  removeToken() {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }
  getEmployeeRole(){
    const token:any =this.getToken();
    const decodedToken:any=jwtDecode(token);
    return decodedToken['role'];
  }
  extractPayloadFromToken(token: string) {
    const decodedToken:any =  jwtDecode(token);
    console.log('decode token ',decodedToken);
    console.log('decode token ',decodedToken['sub']);
    return decodedToken['sub'];
  }
  

}
