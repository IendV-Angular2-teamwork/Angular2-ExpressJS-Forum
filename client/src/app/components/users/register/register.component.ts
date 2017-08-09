import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../../../models/user.model';
import Data from '../../../data/data.service';


@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: User;
  
  constructor(private data: Data, private router: Router){
    this.user = new User('', '', '', '');
  }

  onSubmit(registerUserForm){
    registerUserForm = this.user;
    this.data.registerUser(this.user); 
    console.log(this.user);

    this.router.navigateByUrl('/login');
  }
}