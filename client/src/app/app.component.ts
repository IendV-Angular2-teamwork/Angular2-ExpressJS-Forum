import { Component } from '@angular/core';
import {UserService} from './data/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';   
  loggedIn = false; 
  username = "";

constructor(private userService: UserService){  
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
}
