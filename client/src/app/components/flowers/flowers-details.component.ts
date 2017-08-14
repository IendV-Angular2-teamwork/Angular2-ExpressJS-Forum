import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import Data from '../../data/data.service';
import { EventService } from '../../data/event.service';
import { UserService} from '../../data/user.service'

@Component({
  selector: 'flower-details',
  templateUrl: './flowers-details.component.html',
  styleUrls: ['./flowers-details.component.css']

})
export class FlowerDetailsComponent implements OnInit {
  flowerId: number;
  data = {name:'', id:'', category:'', image:''};
  reviewsData = {};
  isCreator = false;
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private dataBase: Data,
    private router: Router,
    private eventService: EventService,
    private userService: UserService 
  ){}

  ngOnInit(){
    
    this.activatedRoute.params.subscribe((params: Params) => {
        this.flowerId = params['id'];
        console.log(this.flowerId);
      });

    this.dataBase
      .findFlowerById(this.flowerId)
      .then(data => {
        this.data = data; 
        //console.log(this.userService.getUser())
        this.isCreator = data.createdBy === this.userService.getUser().email ? true : false;
      });
      
    this.dataBase
      .getReviewsForFlower(this.flowerId)
      .then(data => this.reviewsData = data);     
  }

  getFlowerId(flowerId){
    this.router.navigateByUrl(`flowers/details/${flowerId}/reviews/create`);
  }

  like(flowerId){
    this.dataBase
      .postLike(flowerId)
      .subscribe(res => {
        this.eventService.triggerNotificationFetched(res.message, res.success);
        this.router.navigateByUrl('/');
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

  buyFlower(flowerId){
    this.router.navigateByUrl(`flower/purchase/${flowerId}`);
    
  }
}