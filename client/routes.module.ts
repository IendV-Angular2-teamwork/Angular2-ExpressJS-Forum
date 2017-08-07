import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { CreateThreadComponent } from './components/createThread/createThread.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'new-thread', component: CreateThreadComponent }
];

@NgModule({
  declarations: [
    CreateThreadComponent
  ],
  imports:[RouterModule.forRoot(routes), CommonModule],
  exports: [RouterModule]
})
export class AppRoutesModule {}