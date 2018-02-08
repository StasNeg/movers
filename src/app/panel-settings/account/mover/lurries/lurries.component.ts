import {Component, Input, OnInit} from '@angular/core';
import {UsersService} from "../../../../services/users.service";


@Component({
  selector: 'app-lurries',
  templateUrl: './lurries.component.html',
  styleUrls: ['./lurries.component.css']
})
export class LurriesComponent implements OnInit {
  /*engine=[3.2,3.8,4.5,5.3];
volume = [1, 3, 6, 9];
nl;
lurries;*/
headers = UsersService.headers;
lurryInfo = UsersService.lurryInfo;
lurris=[];
numLurries;
mover=this.ds.getUserIn().name;
stay=['center', 'north', 'south', 'east', 'all'];
constructor(private ds: UsersService) { }
stChange
  ngOnInit() {
  }
/*numLurries(){
    this.lurries = [];
    for (let i=0; i<this.nl; i++) this.lurries.push({});
  }*/
  setNumberr(){
    this.lurris = [];
    for (let i=0; i<this.numLurries; i++)
      this.lurris.push({type: '',works: '',usage: ''});
  }
  check(){
    if (this.lurris.length===0) {return true; }
    for (let i=0; i<this.lurris.length; i++) {
      if (this.lurris[i].type === '' || this.lurris[i].works === '' || this.lurris[i].usage === '') {
        return true;
      }
    }return false;
  }
  submit(){
    console.log(this.stChange);
    console.log(this.lurris);
   }
}
