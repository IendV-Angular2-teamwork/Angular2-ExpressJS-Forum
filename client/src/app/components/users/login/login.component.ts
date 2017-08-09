import { Component } from '@angular/core';

import { User } from '../../../models/user.model';
import Data from '../../../data/data.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: User
  
  constructor(private data: Data){
    this.user = new User('', '', '', '');
  }

  onSubmit(loginFormUser){
    loginFormUser = this.user;    
    this.data.loginUser(this.user);
    console.log(this.user);
  }
}