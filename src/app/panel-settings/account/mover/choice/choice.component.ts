import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-choice',
  templateUrl: './choice.component.html',
  styleUrls: ['./choice.component.css']
})
export class ChoiceComponent implements OnInit {
@Input ()arr;
@Input ()header;
@Input ()name;
@Input ()lurry;
  constructor() { }

  ngOnInit() {
  }

}
