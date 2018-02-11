import {Component, Input, OnInit} from '@angular/core';
import {UsersService} from "../../../../services/users.service";


@Component({
  selector: 'app-lurries',
  templateUrl: './lurries.component.html',
  styleUrls: ['./lurries.component.css']
})
export class LurriesComponent implements OnInit {
headers = UsersService.headers;
lurryInfo = UsersService.lurryInfo;
lurris=[];
numLurries;
  moverId=JSON.parse(localStorage.getItem('user')).id;
mover=JSON.parse(localStorage.getItem('user')).id;
stay=['SOUTH', 'CENTER', 'NORTH', 'EAST', 'ALL'];
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
    // const userId = JSON.parse(localStorage.getItem('user')).id;
    this.lurris.push(this.mover);
    console.log(this.stChange);
    console.log(this.lurris);
   }
}
