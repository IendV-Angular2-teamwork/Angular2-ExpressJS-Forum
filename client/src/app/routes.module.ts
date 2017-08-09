import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/users/login/login.component';
import { RegisterComponent } from './components/users/register/register.component';
import { CreateThreadComponent } from './components/createThread/createThread.component';
import {UserService} from './data/user.service';
import { LoggedInGuard } from './directives/logged.in.guard';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent, canActivate: [LoggedInGuard] },
  { path: 'new-thread', component: CreateThreadComponent, canActivate: [LoggedInGuard] }
];

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    CreateThreadComponent
  ],
  imports:[
    RouterModule.forRoot(routes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [RouterModule],
  providers: [UserService, LoggedInGuard]
})
export class AppRoutesModule {}