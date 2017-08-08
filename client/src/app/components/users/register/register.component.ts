import { Component } from '@angular/core';

import { User } from '../../../models/user.model';
import Data from '../../../data/data.service';


@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: User;
  isPasswordConfirmed: Boolean;
  
  constructor(private data: Data){
    this.user = new User('', '', '', '', '');
  }

  onSubmit(registerUserForm){
    registerUserForm = this.user;
    //this.data.registerUser(this.user); TODO: към сървъра трябва да се подаде сол и хеширана парола, трябва да се преработи сървъра според мен или да се хешира в клиента.
    console.log(this.user);
  }
}