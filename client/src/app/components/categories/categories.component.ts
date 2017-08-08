import { Component, OnInit, EventEmitter, Output } from '@angular/core';

import Data from '../../data/data.service';

@Component({
  selector: 'categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit{
  data: any;
    @Output() categoryId = new EventEmitter<string>();

  constructor(private dataBase: Data) {}

  ngOnInit(){
    this.data = this.dataBase.getCategoriesData().then(data => this.data = data);

    console.log(this.data)
    console.log('------------------------------')
  }

  listThreads(category){
    let categoryId = category._id;
    this.categoryId.emit(categoryId);   
  }
}