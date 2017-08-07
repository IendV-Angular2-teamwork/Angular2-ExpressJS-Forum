import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/users/login/login.component';
import { RegisterComponent } from './components/users/register/register.component';
import { CreateThreadComponent } from './components/createThread/createThread.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'new-thread', component: CreateThreadComponent }
];

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    CreateThreadComponent
  ],
  imports:[RouterModule.forRoot(routes), CommonModule],
  exports: [RouterModule]
})
export class AppRoutesModule {}