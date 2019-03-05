import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent implements OnInit {
  count : number =0;
  downcount : number=0;
   @Input() title :any;
   @Input() description:any;
   @Input() answer:any;
   @Input() upvote:any;
   @Input() downvote:any;
   @Input() view:any;

  constructor() { }

  ngOnInit() {
  }
 
  put()
  {
    
  }
 }
