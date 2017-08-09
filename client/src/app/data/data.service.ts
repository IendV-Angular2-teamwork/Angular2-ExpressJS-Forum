import { Injectable }from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise'; 
import 'rxjs/add/operator/map'; 
import { User } from '../models/user.model';

const baseUrl = 'http://localhost:5000';

@Injectable()
export default class Data{
  data; 

  constructor (private http: Http) {}
  
  getHomeData(): Promise<Array<{}>> {
    return this.http
      .get(baseUrl)
      .toPromise()
      .then(resp => resp.json())
      .catch(err => { 
         console.log(err);
         return [];
      });
  }
    
  getCategoriesData(): Promise<Array<{}>>{
    return this.http
      .get(`${baseUrl}/categories`)
      .toPromise()
      .then(resp => resp.json())
      .catch(err => { 
         console.log(err);
         return [];
      });
  }

  getTheadsByCategory(categoryId): Promise<Array<{}>>{
    return this.http
      .get(`${baseUrl}/list/${categoryId}`)
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
    let body = {
      email: user.email,
      password: user.password
    }

    this.http
      .post(`${baseUrl}/auth/login`, body)
      .map(res => res.json())
      .subscribe(res => console.log(res));
    
    
  }
}