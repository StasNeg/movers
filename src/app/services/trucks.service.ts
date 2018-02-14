import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DatatrucksModel} from '../models/datatrucks.model';

@Injectable()
export class TrucksService implements OnInit {
  url = 'http://localhost:8080/trucks';

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }


  getTrucks() {
    const user = JSON.parse(localStorage.getItem('user'));
    return this.http.get(this.url + '?userId=' + user.id);
  }

  changeTruck(truck: DatatrucksModel) {
    console.log(truck);
    const user = JSON.parse(localStorage.getItem('user'));
    return this.http.put(this.url + '/' + user.id, truck);

  }

  deleteTruck(truck: DatatrucksModel) {
    const user = JSON.parse(localStorage.getItem('user'));
    return this.http.delete(this.url + '?userId=' + user.id + '&truckId=' + truck.id);
  }

}