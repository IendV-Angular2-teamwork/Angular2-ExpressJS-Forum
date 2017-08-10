import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../../data/user.service';
import { EventService} from '../../data/event.service';

@Component({
  selector: 'nav-bar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  loggedIn = false; 
  username = "";

  constructor(private userService: UserService, private router: Router, 
                private eventService: EventService){  
    this.loggedIn = userService.isLoggedIn()
    console.log(this.loggedIn)
  }

  ngOnInit() {
    this.eventService.userLoggedIn.subscribe(
      (name) => {
        this.loggedIn = this.userService.isLoggedIn();
        this.username=name;
      }
    );
  }

  logout(){
    console.log('logout');
    this.userService.logout();
  }
  getUser(){
    this.router.navigateByUrl(`flowers/mine`);
  }
}