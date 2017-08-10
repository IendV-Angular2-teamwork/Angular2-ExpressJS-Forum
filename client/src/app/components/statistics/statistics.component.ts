import { Component, OnInit } from '@angular/core';

import Data from '../../data/data.service';

@Component({
  selector: 'statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit{ 
  data;

  constructor(private dataBase: Data){}

  ngOnInit(){
    this.dataBase
      .getStatistics()
      .then(data => this.data = data);

    //console.log(this.data);  
  }
}