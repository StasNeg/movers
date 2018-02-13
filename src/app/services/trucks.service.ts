import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TruckModel} from '../models/truck.model';

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

  changeTruck(truck: TruckModel) {
    console.log(truck);

    const user = JSON.parse(localStorage.getItem('user'));

    return this.http.put(`http://localhost:8000/${100101}`, truck);
  }

  deleteTruck(truck) {
    const user = JSON.parse(localStorage.getItem('user'));
    return this.http.delete('http://localhost:8080/trucks?userId=' + user.id + '&truckId=' + truck.id);
    }

}
