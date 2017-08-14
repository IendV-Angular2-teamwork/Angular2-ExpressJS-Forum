import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import Data from '../../data/data.service';
import { UserService } from '../../data/user.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'] 
})
export class HomeComponent implements OnInit {
  data: any = [];    
  token;

  constructor(
    private dataBase: Data, 
    private router: Router, 
    private userService: UserService
  ){} 
  
  ngOnInit(){
    this.dataBase.getHomeData().then(data => {this.data = data;}); 
    this.token = this.userService.getToken();    
  }  

  getFlowerId(flower){
    this.userService.setUser(flower.createdBy);
    let flowerId = flower.id;   
    this.router.navigateByUrl(`flowers/details/${flowerId}`);
  }

  flowerInfoReceived(flowersInfo){     
    this.data = flowersInfo;    
  }
}