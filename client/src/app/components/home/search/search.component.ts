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

  onSubmit(searchForm){ //TODO: заявката е правилна, връща се желания обект, но по някаква причина, не иска да се закачи за this.searchResult
    searchForm = this.search;    
    console.log(this.search);   
    this.dataBase
      .searchCar(this.search)
      .then(data => this.searchResult = data);
    console.log('search result v search.component');
    console.log(this.searchResult);
    this.sendFlowerInfoToHome.emit(this.searchResult);  
  }
}