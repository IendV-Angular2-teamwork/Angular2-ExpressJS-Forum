import { Component, Output, EventEmitter } from '@angular/core';

import { Search } from '../../../models/search.model';
import Data from '../../../data/data.service';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent{
  search: Search;
  searchResult: Array<{}>;  
  @Output() sendFlowerInfoToHome = new EventEmitter<Array<{}>>()

  constructor(private dataBase: Data){
    this.search = new Search();
  }

  onSubmit(searchForm){ 
    searchForm = this.search; 
    this.dataBase
      .searchCar(this.search)
      .then(data => {this.searchResult = data; this.sendFlowerInfoToHome.emit(this.searchResult);});
    
      
  }
}