import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DatatrucksModel} from '../models/datatrucks.model';

@Injectable()
export class TrucksService implements OnInit {

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }

  getTrucks() {
    const user = JSON.parse(localStorage.getItem('user'));
    return this.http.get('http://localhost:8080/trucks?userId=' + user.id);
  }

  changeTruck(truck: DatatrucksModel) {
    console.log(truck);
    const user = JSON.parse(localStorage.getItem('user'));
    return this.http.put(`http://localhost:8000/${truck.id}`, truck);
  }

  deleteTruck(truck: DatatrucksModel) {
    const user = JSON.parse(localStorage.getItem('user'));
    return this.http.delete('http://localhost:8080/trucks?userId=' + user.id + '&truckId=' + truck.id);
  }

}
