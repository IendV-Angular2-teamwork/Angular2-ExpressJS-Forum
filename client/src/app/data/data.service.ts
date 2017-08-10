import { Injectable }from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise'; 
import 'rxjs/add/operator/map'; 
import { User } from '../models/user.model';
import { UserService } from './user.service';

const baseUrl = 'http://localhost:5000';

@Injectable()
export default class Data{
  data; 

  constructor (private http: Http, private userService: UserService) {}

  getHomeData(): Promise<Array<{}>>  {
    return this.http
      .get(`${baseUrl}/flowers/all`)
      .toPromise()
      .then(resp => resp.json())
      .catch(err => { 
         console.log(err);
         return [];
      });
  }

  getStatistics(){
    return this.http
      .get(`${baseUrl}/stats`)
      .toPromise()
      .then(resp => resp.json())
      .catch(err => { 
         console.log(err);
         return [];
      });
  }  

  findFlowerById(flowerId){

    let token = this.userService.getToken();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', `bearer ${token}`);

    return this.http
      .get(`${baseUrl}/flowers/details/${flowerId}`, { headers })
      .toPromise()
      .then(resp => resp.json())
      .catch(err => { 
         console.log(err);
         return [];
      });
  }
    
  
  registerUser(user){ 
    let body = {      
      name: user.name,
      email: user.email,
      password: user.password
    };    

    this.http
      .post(`${baseUrl}/auth/signup`, body)
      .map(res => res.json())
      .subscribe(res => console.log(res));    
  }  

  loginUser(user){
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');

    let body = {
      email: user.email,
      password: user.password
    }

    return this.http
      .post(`${baseUrl}/auth/login`, body, { headers })
      .map(res => res.json())
      .map((res) => {
        if (res.success) {
          this.userService.setToken(res.token);
        }

        return res;
      });    
  }

  getProfileInfo(): Promise<Array<{}>> { 
    let token = this.userService.getToken();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', `bearer ${token}`);

   return this.http
      .get(`${baseUrl}/flowers/mine`, { headers })
      .toPromise()
      .then(resp => resp.json())
      .catch(err => { 
         console.log(err);
         return [];
      });
  }

  addFlower(flower){
    let body = {
      name: flower.name,
      category: flower.category,
      blossom: flower.blossom,
      price: flower.price,
      image: flower.image
    }

    let token = this.userService.getToken();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', `bearer ${token}`);

    this.http
      .post(`${baseUrl}/flowers/create`, body, { headers })
      .map(res => res.json())
      .subscribe(res => console.log(res));    
  }

  addReviewOfFlower(flowerReview, flowerId){
    let body = {
      rating: flowerReview.rating,
      comment: flowerReview.comment
    }

    let token = this.userService.getToken();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', `bearer ${token}`);

    this.http
      .post(`${baseUrl}/flowers/details/${flowerId}/reviews/create`, body, { headers })
      .map(res => res.json())
      .subscribe(res => console.log(res));
  }

  getReviewsForFlower(flowerId){
    let token = this.userService.getToken();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', `bearer ${token}`);

   return this.http
      .get(`${baseUrl}/flowers/details/${flowerId}/reviews`, { headers })
      .toPromise()
      .then(resp => resp.json())
      .catch(err => { 
         console.log(err);
         return [];
      });
  }

  deleteFlower(flowerId){ //TODO: Unautorize!??
    let token = this.userService.getToken();
    
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', `bearer ${token}`);

     this.http
      .post(`${baseUrl}/flowers/delete/${flowerId}/`, { headers })
      .map(res => res.json())
      .subscribe(res => console.log(res));
  }  
  
  postLike(flowerId){ //TODO: Unautorize!??
    let token = this.userService.getToken();

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', `bearer ${token}`);

    this.http
      .post(`${baseUrl}/flowers/details/${flowerId}/like`, { headers })
      .map(res => res.json())
      .subscribe(res => console.log(res));
    }  
} 