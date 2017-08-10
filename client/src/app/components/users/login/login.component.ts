import { Component } from '@angular/core';

import { User } from '../../../models/user.model';
import { UserService } from '../../../data/user.service';
import Data from '../../../data/data.service';
import { Router } from '@angular/router';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: User;
  token: any;
  
  constructor(private data: Data, private router: Router, private userService: UserService){
    this.user = new User();
  }

  onSubmit(loginFormUser){
    loginFormUser = this.user;   
    
    this.data.loginUser(this.user).subscribe((result) => {
      //this.userService.setToken(result.token); 
      
      console.log(result);
      //console.log(this.userService.getToken());
      this.router.navigateByUrl('/');
    });
  }
  
}