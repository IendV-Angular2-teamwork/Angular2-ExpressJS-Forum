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
  purchasedFlowers = {purchase: {} , purchasedFlowerId: ''};

  constructor(
    private dataBase: Data,
    private userService: UserService
  ){}

  ngOnInit(){
    this.dataBase
      .getProfileInfo()
      .then(data => this.data = data);
    this.purchasedFlowers = this.userService.getPurchasedFlower();
    
    console.log(this.purchasedFlowers.purchase);
    console.log(this.purchasedFlowers.purchasedFlowerId);
  }
}