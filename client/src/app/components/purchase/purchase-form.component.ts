import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { Data } from '../../data/data.service';
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
    this.eventService.triggerNotificationFetched(`Thank you for your purchase! We will contact you soon at ${this.purchase.email}!`, true);
    this.userService.setPurchasedFlowers(purchaseForm, this.data);
    this.dataBase.deleteFlower(this.flowerId)
      .subscribe(data => {
        this.router.navigateByUrl('/');
      });    
  }
}