import {Component, OnInit} from '@angular/core';
import {ItemService} from '../services/item.service';
import {ItemAddressData} from '../interfaces/itemAddress-data';
import {MatDialog} from '@angular/material';
import {DialogComponent} from './dialog/dialogItem/dialog.component';
import {DialogRoomComponent} from './dialog/dialogRoom/dialogRoom.component';
import {Room} from '../interfaces/room';
import {AddressItemsData} from "../interfaces/addressItemsData";
import {Address} from "../interfaces/address";
import {forEach} from "@angular/router/src/utils/collection";
import {Router} from "@angular/router";

@Component({
  selector: 'app-room-items',
  templateUrl: './room-items-maim-menu.component.html',
  styleUrls: ['./room-items-main-menu.component.css']
})
export class RoomItemsMainMenuComponent implements OnInit {
  //array for color (addresses and items)
  color = ['yellow', 'blue', 'pink', 'green', 'purple', 'orange', 'brown', 'dark-blue'];
  //main array type:ItemAddressData (watch into interface)
  arrayItems: ItemAddressData [] = [];
  roomType;
  // array with addresses
  addresses;
  // array with items which are displayed in part TO on every address
  itemsTo;
  // array with items which are displayed in part FROM on every address
  items = [];
  // array with type "0_0_0 : 0"
  // where key (all from arrayItems):
  // first 0 - number in address array,
  // second 0 - number in room array,
  // third 0 - number in itemsArray,
  //value: number in address which represent address TO go that item
  itemsToIndex = [];
  //current highlighted address
  addressesCurrentIndex = -1;
  //current highlighted room
  roomTypeCurrentIndex = -1;
  //variable for close all modal forms
  open = false;
  //variable for roomType array
  roomTypeTotal;
  //variable for init addresses From localStorage
  addressesLocalStorage;
  //finalJson for calculating
  dataItem: AddressItemsData = new AddressItemsData();
  //for test!!!!! show final JSON for Michael
  finalJson;


  constructor(private itemService: ItemService, public dialog: MatDialog, private router: Router) {
  }

  ngOnInit() {
    //init addresses
    this.addressesLocalStorage = JSON.parse(localStorage.getItem('adresses'));
    this.addresses = [];
    this.addresses.push(this.addressesLocalStorage.addressFrom);
    this.addressesLocalStorage.addressesTo.forEach((i) => this.addresses.push(i));
    //get roomType from server, init arrayItems
    this.itemService.getAppartmentsType().subscribe((responce) => {
      this.roomTypeTotal = [];
      this.roomTypeTotal.push(...responce.data);
      this.createArrayItems();
    });
  }

  //init arrayItems Method
  createArrayItems() {
    for (let i = 0; i < this.addresses.length; i++) {
      const tempRooms: Room[] = [];
      for (let j = 0; j < 3; j++) {
        tempRooms[j] = {
          id: j,
          roomType: this.roomTypeTotal[j],
          items: []
        };
      }
      const item: ItemAddressData = {
        city: this.addresses[i].city,
        street: this.addresses[i].street,
        rooms: tempRooms
      };
      this.arrayItems.push(item);
    }
  }

  //get color for address
  getColor(i) {
    return this.color[i];
  }

  //get color for TO items
  getColorTo() {
    return this.color[this.addressesCurrentIndex];
  }

  //get color for FROM items
  getColorFrom(i) {
    return this.color[this.itemsToIndex[this.createIndexForItemsTo(i)]];
  }

  //onClick onRoomChange
  onRoomTypeChange(i) {
    this.roomTypeCurrentIndex = i;
    this.showItems();
  }

  //onClick onAddressChange
  onAddressChange(i) {
    this.addressesCurrentIndex = i;
    this.roomType = [];
    for (let i = 0; i < this.arrayItems[this.addressesCurrentIndex].rooms.length; i++) {
      this.roomType.push(this.arrayItems[this.addressesCurrentIndex].rooms[i].roomType);
    }
    this.itemsTo = this.getAllItemsTo();
    if (this.roomTypeCurrentIndex !== -1 && this.roomType.length <= this.roomTypeCurrentIndex) {
      this.items = [];
      this.roomTypeCurrentIndex = -1;
      return;
    }
    this.showItems();
  }

  //init items array for display items From
  showItems() {
    if (!this.canAddItem()) {
      return;
    }
    this.items = this.arrayItems[this.addressesCurrentIndex].rooms[this.roomTypeCurrentIndex].items;
  }

  closeAllDialog() {
    if (this.open) {
      this.dialog.closeAll();
      this.open = false;
    }
  }

  //methods for work with dialog window
  openRoomDialog() {
    this.open = true;
    this.dialog.closeAll();
    const dialogRef = this.dialog.open(DialogRoomComponent, {
      position: {
        left: '350px'
      },
      height: '400px',
      width: '600px',
      data: this.roomTypeTotal
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {

        this.arrayItems[this.addressesCurrentIndex].rooms.push({
          id: this.arrayItems[this.addressesCurrentIndex].rooms.length,
          roomType: result,
          items: []
        });
        this.onAddressChange(this.addressesCurrentIndex);
      }
    });
  }

  openItemDialog(): void {
    this.open = true;
    this.dialog.closeAll();
    let dialogRef = this.dialog.open(DialogComponent, {
      position: {
        left: '350px'
      },
      height: '400px',
      width: '400px',
      data: {
        roomType: this.arrayItems[this.addressesCurrentIndex].rooms[this.roomTypeCurrentIndex].roomType
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        this.arrayItems[this.addressesCurrentIndex].rooms[this.roomTypeCurrentIndex].items.push({
          id: result.item.id,
          name: result.item.name,
          propertyWithType: result.property,
          property: this.arrayFrom(result.property)
        });
        this.itemsToIndex[this.createIndexForItemsTo(this.arrayItems[this.addressesCurrentIndex].rooms[this.roomTypeCurrentIndex].items.length - 1)] = this.addresses.length - 1;
      }
    });
  }

  //init array properties in items
  private arrayFrom(property) {
    let result = [];
    for (let i in  property) {
      result.push(property[i]);
    }
    return result;
  }

  //validation address
  isSelectedAddress() {
    return this.addressesCurrentIndex >= 0 && this.addressesCurrentIndex !== this.addresses.length - 1;
  }

  //validation add item
  canAddItem() {
    if (this.addressesCurrentIndex < 0 || this.roomTypeCurrentIndex < 0 || this.addressesCurrentIndex === this.addresses.length - 1) {
      return false;
    }
    return true;
  }

  //create index for itemsToIndex
  createIndexForItemsTo(indexItem) {
    return (this.addressesCurrentIndex + '_' + this.roomTypeCurrentIndex + '_' + indexItem);
  }

  //get item for itemsTo array
  getItemByIndexItemTo(index: String) {
    let indexes = index.split('_');
    return this.arrayItems[+indexes[0]].rooms[+indexes[1]].items[+indexes[2]];
  }

  //create itemTo for current address
  private getAllItemsTo() {
    let items = [];
    for (var key in this.itemsToIndex) {
      if (this.itemsToIndex[key] === this.addressesCurrentIndex) {
        items.push(this.getItemByIndexItemTo(key));
      }
    }
    return items;
  }

  //change destinations for item - itemIndex, destination - addressToIndex
  changeAddressTo(itemIndex, addressToIndex) {
    if (addressToIndex > this.addressesCurrentIndex) {
      this.itemsToIndex[this.createIndexForItemsTo(itemIndex)] = addressToIndex;
    }
  }

  //remove item from arrayItems, rearrange itemToIndexArray
  deleteItem(itemIndex) {
    let index = this.createIndexForItemsTo(itemIndex);
    let tempArray = []
    this.arrayItems[this.addressesCurrentIndex].rooms[this.roomTypeCurrentIndex].items.splice(itemIndex, 1);
    for (var key in this.itemsToIndex) {
      if (index !== key) {
        let newKey;
        let splitArray = key.split('_')
        if (+splitArray[0] === this.addressesCurrentIndex && +splitArray[1] === this.roomTypeCurrentIndex && +splitArray[2] > itemIndex) {
          newKey = this.createIndexForItemsTo(+splitArray[2] - 1)
        } else {
          newKey = key;
        }
        tempArray[newKey] = this.itemsToIndex[key];
      }
    }
    this.itemsToIndex = tempArray;
  }

  //click on calculate - create Object for calculate parcel charge
  finishAddItem() {
    this.dataItem.customerId = JSON.parse(localStorage.getItem('user')).id;
    this.dataItem.place_type = JSON.parse(localStorage.getItem('adresses')).typeOfAppartment;
    this.dataItem.move_date = '2018-01-18';
    this.dataItem.move_time = '19:00';
    this.dataItem.personal = false;
    this.dataItem.addresses = [];
    for (let i = 0; i < this.addresses.length; i++) {
      this.dataItem.addresses.push({
        seqnumber: i,
        latitude: this.addresses[i].lat,
        longitude: this.addresses[i].lng,
        city: this.addresses[i].city,
        street: this.addresses[i].street,
        building: this.addresses[i].street_number,
        apartment: null,
        floor: this.addresses[i].floor,
        lift: this.isLift(this.addresses[i].lift)
      });
    }
    this.dataItem.moves = [];
    for (let key in this.itemsToIndex) {
      let splitArray = key.split('_');
      let indexMoves = this.hasMove(+splitArray[0], this.itemsToIndex[key]);
      if (indexMoves != -1) {
        let indexRoom = this.hasRoom(indexMoves, +splitArray[1]);
        if (indexRoom != -1) {
          this.dataItem.moves[indexMoves].rooms[indexRoom].items.push(this.arrayItems[+splitArray[0]].rooms[+splitArray[1]].items[+splitArray[2]]);
        }
        else {
          this.addRoom(indexMoves, splitArray);
          this.dataItem.moves[indexMoves].rooms[this.dataItem.moves[indexMoves].rooms.length - 1].items.push(this.arrayItems[+splitArray[0]].rooms[+splitArray[1]].items[+splitArray[2]]);
        }
      } else {
        this.addMove(key, splitArray);
        this.addRoom(this.dataItem.moves.length - 1, splitArray);
        this.dataItem.moves[this.dataItem.moves.length - 1].rooms[this.dataItem.moves[this.dataItem.moves.length - 1].rooms.length - 1].items.push(this.arrayItems[+splitArray[0]].rooms[+splitArray[1]].items[+splitArray[2]])
      }
    }
    // this.finalJson = JSON.stringify(this.dataItem,null,'\t')
    this.itemService.setItemsOrder(this.dataItem);
    this.router.navigate(['/request/finish'],)
  }

  private isLift(isLift): string {
    return isLift ? 'LIFT' : 'NO_LIFT';
  }

  private hasRoom(indexMoves, idRoom): number {
    for (let i = 0; i < this.dataItem.moves[indexMoves].rooms.length; i++) {
      if (this.dataItem.moves[indexMoves].rooms[i].id === idRoom) {
        return i;
      }
    }
    return -1;
  }

  hasMove(secNumberFrom, secNumberTo): number {
    for (let i = 0; i < this.dataItem.moves.length; i++) {
      if (this.dataItem.moves[i].addressIn.seqnumber === secNumberTo && this.dataItem.moves[i].addressOut.seqnumber === secNumberFrom) {
        return i;
      }
    }
    return -1;
  }


  private addMove(key, splitArray) {
    this.dataItem.moves.push({
      addressIn: this.dataItem.addresses[this.itemsToIndex[key]],
      addressOut: this.dataItem.addresses[+splitArray[0]],
      rooms: []
    });
  }

  private addRoom(indexMoves, splitArray) {
    this.dataItem.moves[indexMoves].rooms.push({
      id: +splitArray[1],
      roomType: this.arrayItems[+splitArray[0]].rooms[+splitArray[1]].roomType,
      room: this.arrayItems[+splitArray[0]].rooms[+splitArray[1]].roomType,
      items: []
    });
  }

  isFinish() {
    let count = -1;
    for (let key in this.itemsToIndex){
      count = 1;
      break;
    }
    if(count === -1) return false;
    return true;
  }
}
