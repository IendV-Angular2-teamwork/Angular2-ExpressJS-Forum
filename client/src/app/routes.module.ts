import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/users/login/login.component';
import { RegisterComponent } from './components/users/register/register.component';
import { UserProfileComponent } from './components/users/profile/user-profile.component';
import { FlowersComponent } from './components/flowers/flowers.component';
import { FlowerDetailsComponent } from './components/flowers/flowers-details.component';
import { FlowerReviewComponent } from './components/flowers/flower-review.component';
import { PurchaseComponent } from './components/purchase/purchase-form.component';
import { AboutComponent } from './components/about/about.component';

import { UserService } from './data/user.service';
import { EventService } from './data/event.service';
import { LoggedInGuard } from './directives/logged.in.guard';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent, /*canActivate: [LoggedInGuard]*/ },
  { path: 'new-flower', component: FlowersComponent,/* canActivate: [LoggedInGuard]*/ },
  { path: 'flowers/details/:id', component: FlowerDetailsComponent, canActivate: [LoggedInGuard] },
  { path: 'flowers/mine', component: UserProfileComponent, canActivate: [LoggedInGuard] },
  { path: 'flowers/details/:id/reviews/create', component: FlowerReviewComponent, canActivate: [LoggedInGuard] },
  { path: 'flower/purchase/:id', component: PurchaseComponent, canActivate: [LoggedInGuard] },
  { path: 'about', component: AboutComponent }
];

@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    UserProfileComponent,
    FlowersComponent,
    FlowerDetailsComponent,
    FlowerReviewComponent,
    PurchaseComponent,
    AboutComponent
  ],
  imports:[
    RouterModule.forRoot(routes),
    CommonModule,
    FormsModule,
    ReactiveFormsModule 
  ],
  exports: [RouterModule],
  providers: [UserService, LoggedInGuard, EventService]
})
export class AppRoutesModule {}