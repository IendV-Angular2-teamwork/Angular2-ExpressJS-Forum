import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import Data from '../../data/data.service';

@Component({
  selector: 'flower-details',
  templateUrl: './flowers-details.component.html',
  styleUrls: ['./flowers-details.component.css']

})
export class FlowerDetailsComponent implements OnInit {
  flowerId: number;
  data: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private dataBase: Data,
    private router: Router
  ){}

  ngOnInit(){
    
    this.activatedRoute.params.subscribe((params: Params) => {
        this.flowerId = params['id'];
        console.log(this.flowerId);
      });

    this.dataBase
      .findFlowerById(this.flowerId)
      .then(data => this.data = data);        
  }

  getFlowerId(flowerId){
    this.router.navigateByUrl(`flowers/details/${flowerId}/reviews/create`);
  }
}