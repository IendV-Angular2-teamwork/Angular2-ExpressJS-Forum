import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import Data from '../../data/data.service';
import { EventService } from '../../data/event.service';

@Component({
  selector: 'flower-details',
  templateUrl: './flowers-details.component.html',
  styleUrls: ['./flowers-details.component.css']

})
export class FlowerDetailsComponent implements OnInit {
  flowerId: number;
  data = {name:'', id:'', category:'', image:''};
  reviewsData: any;
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private dataBase: Data,
    private router: Router,
    private eventService: EventService
  ){}

  ngOnInit(){
    
    this.activatedRoute.params.subscribe((params: Params) => {
        this.flowerId = params['id'];
        console.log(this.flowerId);
      });

    this.dataBase
      .findFlowerById(this.flowerId)
      .then(data => this.data = data);
      
    this.dataBase
      .getReviewsForFlower(this.flowerId)
      .then(data => this.reviewsData = data);  

    //console.log(this.data);  
    //console.log(`reviews ${this.reviewsData}`)    
  }

  getFlowerId(flowerId){
    this.router.navigateByUrl(`flowers/details/${flowerId}/reviews/create`);
  }

  like(flowerId){
    this.dataBase
      .postLike(flowerId)
      .subscribe(res => {
        this.eventService.triggerNotificationFetched(res.message, res.success);
        //TODO redirect
      });
  }

  delete(flowerId){
    this.dataBase
    .deleteFlower(flowerId)
    .subscribe(res => {
      this.eventService.triggerNotificationFetched(res.message, res.success);
      this.router.navigateByUrl('/');
    });
  }
}