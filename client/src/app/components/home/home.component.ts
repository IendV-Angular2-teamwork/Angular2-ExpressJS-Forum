import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Data } from '../../data/data.service';
import { UserService } from '../../data/user.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['../../css/common.styles.css', './home.component.css'] 
})
export class HomeComponent implements OnInit {
  data: any = [];    
  token;
  statistics: boolean = false;
  statisticsLabel: string = 'Show';

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
    let flowerId = flower.id;   
    this.router.navigateByUrl(`flowers/details/${flowerId}`);
  }

  flowerInfoReceived(flowersInfo){     
    this.data = flowersInfo;    
  }

  showStatistics(){
    if(!this.statistics){
      this.statistics = true;
      this.statisticsLabel = 'Hide';
    }else{
      this.statistics = false;
      this.statisticsLabel = 'Show';
    }
  }
}