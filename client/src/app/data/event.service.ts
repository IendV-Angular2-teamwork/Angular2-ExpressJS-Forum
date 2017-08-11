// user.service.ts
import { Injectable, EventEmitter } from '@angular/core';


@Injectable()
export class EventService {
  userLoggedIn = new EventEmitter();
  statisticChanged = new EventEmitter();

  constructor() {
  }

  triggerUserLoggedIn(data) {
    this.userLoggedIn.emit(data);
  }

  triggerStatisticChanged(data) {
    this.statisticChanged.emit(data); 
  }
}