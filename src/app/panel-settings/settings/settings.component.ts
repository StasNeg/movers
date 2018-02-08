import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  languages=['english', 'hebrew', 'russian'];
lang;
  constructor() { }

    ngOnInit() {
   }
  lan(l){ console.log(this.lang)}
  /*io(x) {return this.languages.indexOf.call(x)};*/
   }

