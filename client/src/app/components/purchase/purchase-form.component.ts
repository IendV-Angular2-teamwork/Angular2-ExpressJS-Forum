import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import Data from '../../data/data.service';
import { EventService } from '../../data/event.service';
import { UserService } from '../../data/user.service';

import { Purchase } from '../../models/purchase.model';

@Component({
  selector: 'purchase',
  templateUrl: './purchase-form.component.html',
  styleUrls: ['../../css/common.styles.css', './purchase-form.component.css']
})
export class PurchaseComponent implements OnInit {
  data: any = {};
  flowerId: number;
  visibleForm: boolean = false;
  purchase: Purchase;

  constructor(
    private dataBase: Data,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private eventService: EventService,
    private userService: UserService
  ){
    this.purchase = new Purchase();
  }

  ngOnInit(){
    this.activatedRoute.params.subscribe((params: Params) => {
        this.flowerId = params['id'];      
      });

      this.dataBase
      .findFlowerById(this.flowerId)
      .then(data => {
        this.data = data; 
        console.log('buy');       
        console.log(this.data)
      });
  }

  cancelOrder(){
    this.router.navigateByUrl(`flowers/details/${this.flowerId}`)
  }

  showForm(){
    if(!this.visibleForm){
      this.visibleForm = true;      
    }else{
      this.visibleForm = false;      
    }
  }

  onSubmit(purchaseForm){
    purchaseForm = this.purchase;    
    this.eventService.triggerNotificationFetched(`Order accepted! We contact with you at ${this.purchase.email}! Have a nice day!`, true);
    this.userService.setPurchasedFlowers(purchaseForm, this.flowerId);
    this.dataBase.deleteFlower(this.flowerId)
      .subscribe(data => {
        this.router.navigateByUrl('/');
      });    
  }
}