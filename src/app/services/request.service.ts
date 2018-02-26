import {HttpClient} from "@angular/common/http";
import "rxjs/add/operator/map";
import {Request} from "../models/request.model";
import {Injectable} from "@angular/core";
import {JsonModel} from "../models/json.model";
import {DataTo} from "../interfaces/data-to";


@Injectable()
export class RequestService {
  requests = [new Request()];
  urlCustomer: string = 'http://localhost:8080/dashboard/requests/customer/getrecent?token=';
  urlMover: string = 'http://localhost:8080/dashboard/requests/mover/getrecent?token=';
  urlMoverRequestDetail: string = 'http://localhost:8080/dashboard/requests/mover/getdetail?token=';
  constructor(private http: HttpClient) {
  }
  getRequests() {
    let url;
    const type = JSON.parse(localStorage.getItem('user')).type;
    const taleUrl = +JSON.parse(localStorage.getItem('user')).id;
    if (type === 'customer') {
      url = this.urlCustomer;
    } else {
      url = this.urlMover;
    }
    const path = '' + url + taleUrl + '';
    return this.http.get<JsonModel>(path)
      .map((request: JsonModel) => {
        this.requests = request.data;
        return this.requests;
      });
  }

  getRequestsDetail(token) {
    const path = '' + this.urlMoverRequestDetail + token;
    return this.http.get(path)
      .map((request: DataTo) => {
        return request.data;
      });
  }
}


