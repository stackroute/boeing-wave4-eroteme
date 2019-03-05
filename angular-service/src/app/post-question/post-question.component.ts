import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.css']
})
export class PostQuestionComponent implements OnInit {

  postValue;

  constructor() { }

  ngOnInit() {
  }

  postQuestion(){
    console.log('post question');
  }

}
