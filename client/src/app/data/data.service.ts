import { Injectable }from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';

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
}