import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutesModule } from './routes.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule }from '@angular/http';

import  Data  from './data/data.service'; 
import { UserService }  from './data/user.service'; 
import { NotificationService } from './data/notification.service';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { SearchComponent } from './components/home/search/search.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    StatisticsComponent,
    SearchComponent   
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutesModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [Data, UserService, NotificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
