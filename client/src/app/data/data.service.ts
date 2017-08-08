import { Injectable }from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise'; 
import { User } from '../models/user.model';

const baseUrl = 'http://localhost:3000';

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

  registerUser(user){ //TODO
    let body = {
      username: user.username,
      firstName: user.firstName,
      lastName: user.lastName,
      password: user.password
    }
    this.http.post(`${baseUrl}/users/register`, body);

    console.log('regna se');
  }

  
}