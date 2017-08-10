import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import Data from '../../data/data.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'] 
})
export class HomeComponent implements OnInit {
  data: any;    

  constructor(private dataBase: Data, private router: Router){} 
  
  ngOnInit(){
    this.dataBase.getHomeData().then(data => this.data = data); 
    console.log(this.data)  
  }  

  getFlowerId(flower){
    let flowerId = flower.id;   
    this.router.navigateByUrl(`flowers/details/${flowerId}`);
  }
}