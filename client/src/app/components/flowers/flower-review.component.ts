import { Component } from '@angular/core';

import { FlowerReview } from '../../models/flower-review.model';

import Data from '../../data/data.service';

@Component({
  selector: 'flower-review',
  templateUrl: './flower-review.component.html',
  styleUrls: ['./flower-review.component.css']
})
export class FlowerReviewComponent {
  flowerReview: FlowerReview;

  constructor() {
    this.flowerReview = new FlowerReview();
    
  }

  onSubmit(flowerReviewForm){
    flowerReviewForm = this.flowerReview;

    console.log(this.flowerReview);

    //TODO: да се върже с датата.
  }
}