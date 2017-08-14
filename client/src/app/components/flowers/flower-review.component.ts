import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';


import { FlowerReview } from '../../models/flower-review.model';

import Data from '../../data/data.service';
import { EventService } from '../../data/event.service';

@Component({
  selector: 'flower-review',
  templateUrl: './flower-review.component.html',
  styleUrls: ['../../css/common.styles.css', './flower-review.component.css']
})
export class FlowerReviewComponent implements OnInit {
  flowerReview: FlowerReview;
  flowerId: number;

  constructor( 
    private dataBase: Data,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private eventService: EventService
  ) {
    this.flowerReview = new FlowerReview();    
  }

  ngOnInit(){
     this.activatedRoute.params.subscribe((params: Params) => {
        this.flowerId = params['id'];
        //console.log(this.flowerId);
      });
  }

  onSubmit(flowerReviewForm){
    flowerReviewForm = this.flowerReview;   

    this.dataBase
      .addReviewOfFlower(this.flowerReview, this.flowerId)
      .subscribe(res => {
        this.eventService.triggerNotificationFetched(res.message, res.success);        
      });
      
    this.router.navigateByUrl(`flowers/details/${this.flowerId}`); 
  }
}