import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../../data/user.service';

@Component({
  selector: 'nav-bar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  loggedIn = false; 
  username = "";

  constructor(private userService: UserService, private router: Router){  
    this.loggedIn = userService.isLoggedIn()
    console.log(this.loggedIn)
  }

  ngOnInit() {
    this.userService.userLoggedIn.subscribe(
      (name) => {
        this.loggedIn = this.userService.isLoggedIn();
        this.username=name;
      }
    );
  }

  getUser(){
    this.router.navigateByUrl(`flowers/mine`);
  }
}