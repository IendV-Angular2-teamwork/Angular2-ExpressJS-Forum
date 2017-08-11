import { Injectable } from '@angular/core';

@Injectable()
export class NotificationService{
  notification;

  setNotification(res){
    this.notification = res;
  }

  getNotification(){
    return this.notification;
  }

}