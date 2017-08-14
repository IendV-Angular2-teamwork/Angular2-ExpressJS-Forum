import { Component, OnInit } from '@angular/core';

import Data from '../../../data/data.service';
import { UserService } from '../../../data/user.service';

@Component({
  selector: 'user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  data: any;
  purchasedFlowers = [];
  total = 0;

  constructor(
    private dataBase: Data,
    private userService: UserService
  ){}

  ngOnInit(){
    this.dataBase
      .getProfileInfo()
      .then(data => this.data = data);
      console.log(this.userService.getPurchasedFlower())
    this.purchasedFlowers = this.userService.getPurchasedFlower();
    for(let purchase of this.purchasedFlowers) {
      this.total += purchase.flower.price;
    }
  }
}