// user.service.ts
import { Injectable, EventEmitter } from '@angular/core';
import { EventService} from './event.service'
import { Router } from '@angular/router';

@Injectable()
export class UserService {
  token;
  user;
  purchasedFlower: Array<{}>;
  purchasedFlowerId;

  constructor(private eventService: EventService, private router: Router) {
  }

  setUser(user) {
    this.user = user; 
  }

  getUser() {
    return this.user;
  }

  setToken(token) {
    this.token = token;
    this.eventService.triggerUserLoggedIn(this.user.name);
  }
  
  getToken() {
    return this.token;
  }

  isLoggedIn() {
    return !!this.token;
  }

  setPurchasedFlowers(flower, purchaseFlowerId){
    //this.purchasedFlower.push(flower);
    this.purchasedFlower = flower;
    this.purchasedFlowerId = purchaseFlowerId;
  }

  getPurchasedFlower(){
    let dataForPurchasedFlowers = {
      purchase: this.purchasedFlower,
      purchasedFlowerId: this.purchasedFlowerId
    }
    return dataForPurchasedFlowers;
  }

  logout(){
    console.log('here')
    this.token = '';
    this.setToken(this.token)
    this.user = {};
    this.router.navigateByUrl('/');
  }
}