// user.service.ts
import { Injectable, EventEmitter } from '@angular/core';


@Injectable()
export class UserService {
  userLoggedIn = new EventEmitter();
  token;

  constructor() {
  }

  setToken(token) {
    this.token = token;
    this.userLoggedIn.emit('pesho');
  }

  //TODO: setName???
  
  getToken() {
    return this.token;
  }

  isLoggedIn() {
    return !!this.token;
  }
}