import { Component } from '@angular/core';

import { Flower } from '../../models/flower.model';
import Data from '../../data/data.service';

@Component({
  selector: 'flowers',
  templateUrl: './flowers.component.html',
  styleUrls: ['./flowers.component.css']
})
export class FlowersComponent{
  flower: any;

  constructor(private data: Data){
    this.flower = new Flower();
  }

  onSubmit(flowerFromForm){
    flowerFromForm = this.flower;
    this.data.addFlower(this.flower);
    console.log(this.flower);
    this.flower = new Flower();
  } 
}