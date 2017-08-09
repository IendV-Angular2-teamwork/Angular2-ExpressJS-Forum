import { Component, OnInit } from '@angular/core';

import Data from '../../data/data.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'] 
})
export class HomeComponent implements OnInit {
  data: any;    

  constructor(private dataBase: Data){} 
  
  ngOnInit(){
    this.data = this.dataBase.getHomeData().then(data => this.data = data); 
    console.log(this.data)  
  }

  threadsToList(categoryId){
    
    this.data = this.dataBase.getTheadsByCategory(categoryId).then(data => this.data = data);    
  }
}