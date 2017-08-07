import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutesModule } from './routes.module';

import Data from './data/data.service';

import { HttpModule }from '@angular/http';


import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutesModule
  ],
  providers: [Data],
  bootstrap: [AppComponent]
})
export class AppModule { }
