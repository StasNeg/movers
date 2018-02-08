import {Injectable} from '@angular/core';
import {DataTo} from '../interfaces/data-to';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {RequestData} from '../interfaces/request-data';

@Injectable()
export class ConnectionsService {

  private url = 'http://localhost:8080';

  // TODO REMOVE ACCESS-CONTROL '*'
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  });

  constructor(private http: HttpClient) {
  }

  getRequestInfo(id: number) {
    return this.http.get<DataTo>(this.url + '/request/get?id=' + id, {headers: this.headers});
  }

  assignRequestToMover(mover_id: number, request_id: number) {
    return this.http.post<DataTo>(this.url + '/request/assign', {mover_id: mover_id, request_id: request_id}, {headers: this.headers});
  }

  getTotalCostEstimate(requestData: RequestData) {
    return this.http.post<DataTo>(this.url + '/request/estimate/get', requestData, {headers: this.headers});
  }

  saveRequest(requestData: RequestData) {
    return this.http.post<DataTo>(this.url + '/request/save', requestData, {headers: this.headers});
  }
}
