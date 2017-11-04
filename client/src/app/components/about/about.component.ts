import { Component } from '@angular/core';

import { Subscribe } from '../../models/subscribe.model';
import { EventService } from '../../data/event.service';

@Component({
    selector: 'about', 
    templateUrl: './about.component.html',
    styleUrls: ['../../css/common.styles.css', './about.component.css']
})
export class AboutComponent{
    subscribe: Subscribe;

    constructor(
        private eventService: EventService
    ){
        this.subscribe = new Subscribe();
    }

    onSubmit(subscribeForm){
        subscribeForm.email = this.subscribe;
        this.eventService.triggerNotificationFetched('Subscribe successful!', true);
        this.subscribe = new Subscribe();
    }
}