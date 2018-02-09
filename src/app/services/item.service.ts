import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {DataTo} from '../interfaces/data-to';
import {DataToProperties} from '../interfaces/data-to-properties';

@Injectable()
export class ItemService {

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  });

  constructor(private http: HttpClient) {
  }

  getAppartmentsType() {
    return this.http.get<DataTo>('http://localhost:8080/roomstype/all', {headers: this.headers});
  }

  getItemsType(roomType) {
    return this.http.get<DataTo>('http://localhost:8080/itemtype/roomtype?room=' + roomType, {headers: this.headers});
  }

  getItemsProperties(id) {
    return this.http.get<DataToProperties>('http://localhost:8080/item_properties/value?idItemType=' + id, {headers: this.headers});
  }
}
