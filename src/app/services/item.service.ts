import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {DataTo} from '../interfaces/data-to';
import {DataToProperties} from '../interfaces/data-to-properties';
import {AddressItemsData} from "../interfaces/addressItemsData";
import {Move} from "../interfaces/move";

@Injectable()
export class ItemService {
  private itemsOrder: any;

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

  setItemsOrder(items) {
    this.itemsOrder = items;
  }

  getItemsOrder() {
    return this.itemsOrder;
  }

  getShowingData(requestData: AddressItemsData) {
    let result = [];
    for (let i = 0; i < requestData.moves.length; i++) {
      let index = this.hasAddressOut(result, requestData.moves[i]);
      if (index === -1) {
        this.addAllItem(result, requestData.moves[i]);
      } else {
        this.addItems(result[index], requestData.moves[i]);
      }

    }
    return result;
  }

  private hasAddressOut(result, move) {
    for (let i = 0; i < result.length; i++) {
      if (result[i].addressIn.seqnumber === move.addressIn.seqnumber) {
        return i;
      }
    }
    return -1;
  }

  private addAllItem(result, move: Move) {
    result.push({
      addressIn: move.addressIn,
      items: []
    })
    this.addItems(result[result.length - 1], move);
  }

  private addItems(result, move: Move) {
    move.rooms.forEach(room => {
      if (room.items !== null) {
        room.items.forEach(room => {
          result.items.push(room)
        });
      }
    })
  }
}
