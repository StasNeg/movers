import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {Request} from '../models/request.model';
import {Injectable} from '@angular/core';
import {LoginService} from "../login.service";
import {UsersService} from "./users.service";
import {JsonModel} from "../models/json.model";


@Injectable()
export class RequestService {
  requests = [new Request()];
  urlCustomer: string = 'http://localhost:8080/dashboard/requests/customer/getrecent?token=';
  urlMover: string = 'http://localhost:8080/dashboard/requests/mover/getrecent?token=';

  constructor(private http: HttpClient, private loginService: LoginService, private userService: UsersService) {
  }

  getRequests() {
    let url;
    const type = JSON.parse(localStorage.getItem('user')).type;
    const taleUrl = +JSON.parse(localStorage.getItem('user')).id;
    if (type === 'customer') {
      url = this.urlCustomer;
    }
    else {
      url = this.urlMover
    }
    const path = '' + url + this.loginService.token + taleUrl + '';
    console.log(path);
    return this.http.get<JsonModel>(path)
      .map((request: JsonModel) => {
        this.requests = request.data;
        return this.requests;
      });
  }
}


