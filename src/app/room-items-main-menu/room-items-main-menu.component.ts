import {Component, OnInit} from '@angular/core';
import {AddressService} from '../services/address.service';
import {ItemService} from '../services/item.service';
import {ItemAddressData} from '../interfaces/itemAddress-data';
import {MatDialog} from '@angular/material';
import {DialogComponent} from './dialog/dialogItem/dialog.component';
import {DialogRoomComponent} from './dialog/dialogRoom/dialogRoom.component';
import {Room} from '../interfaces/room';
import {Mover} from '../interfaces/mover';


@Component({
  selector: 'app-room-items',
  templateUrl: './room-items-maim-menu.component.html',
  styleUrls: ['./room-items-main-menu.component.css']
})
export class RoomItemsMainMenuComponent implements OnInit {

  color = ['yellow', 'blue', 'pink', 'green', 'purple', 'orange', 'brown', 'dark-blue'];
  arrayItems: ItemAddressData [] = [];
  roomType;
  addresses;
  items = [];
  addressesCurrentIndex = -1;
  roomTypeCurrentIndex = -1;
  open = false;
  roomTypeTotal;
  mover: Mover;
  arrayMovers: Mover[] = [];
  tempItems = [];
  addressesLocalStorage = [];

  constructor(private addressService: AddressService, private itemService: ItemService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.addressesLocalStorage = JSON.parse(localStorage.getItem('adresses'));
    console.log(this.addressesLocalStorage);
    this.addressesLocalStorage.forEach(data => {
      console.log(data.addressTo);
    })

    // this.addresses = JSON.parse(localStorage.getItem('adresses'));
    this.itemService.getAppartmentsType().subscribe((responce) => {
      this.roomTypeTotal = [];
      this.roomTypeTotal.push(...responce.data);
      this.createArrayItems();
      console.log(this.addresses);
    });
  }

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

  getColor(i) {
    return this.color[i];
  }

  onRoomTypeChange(i) {
    this.roomTypeCurrentIndex = i;
    this.tempItems = [];
    this.arrayMovers.forEach(mover => {
      if (mover.addressTo === this.addresses[this.addressesCurrentIndex]) {
        if (mover.rooms[this.roomTypeCurrentIndex]) {
          this.tempItems.push(...mover.rooms[this.roomTypeCurrentIndex].items);
        }
      }
    });

    this.showItems();
  }

  onAddressChange(i) {
    this.tempItems = [];
    this.arrayMovers.forEach(mover => {
      if (mover.addressTo === this.addresses[i]) {
        this.tempItems.push(...mover.rooms[this.roomTypeCurrentIndex].items);
      }
    });
    this.addressesCurrentIndex = i;
    this.roomType = [];
    for (let i = 0; i < this.arrayItems[this.addressesCurrentIndex].rooms.length; i++) {
      this.roomType.push(this.arrayItems[this.addressesCurrentIndex].rooms[i].roomType);
    }
    if (this.roomTypeCurrentIndex !== -1 && this.roomType.length <= this.roomTypeCurrentIndex) {
      this.items = [];
      this.roomTypeCurrentIndex = -1;
      return;
    }
    this.showItems();
  }

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

  openDialog(): void {
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
      }
    });

  }

  private arrayFrom(property) {
    let result = [];
    for (let i in  property) {
      result.push(property[i]);
    }
    return result;
  }

  isSelectedAddres() {
    return this.addressesCurrentIndex >= 0;
  }

  canAddItem() {
    if (this.addressesCurrentIndex < 0 || this.roomTypeCurrentIndex < 0) {
      return false;
    }
    return true;
  }

  pushItem(room, item) {
    room.roomType = this.roomTypeTotal[this.roomTypeCurrentIndex];
    room.items.push(item);
  }

  isSameAddresses(mover, addressTo) {
    let sam = (mover.addressTo === addressTo && mover.addressFrom === this.addresses[this.addressesCurrentIndex]);
    return sam;
  }

  deleteItem(item) {
    this.arrayMovers.forEach(mover => {
      mover.rooms.forEach((room) => {
        room.items.forEach((it, index, arr) => {
          if (it.id === item.id) {
            arr.splice(index, 1);
          }
        });
      });
    });
  }

  createMover(addressTo) {
    return {
      addressFrom: this.addresses[this.addressesCurrentIndex],
      addressTo: addressTo,
      rooms: [],
    };
  }


  addMover(addressTo, item) {

    if (this.arrayMovers.length === 0) {
      const mover: Mover = this.createMover(addressTo);
      const room: Room = new Room();
      this.pushItem(room, item);
      mover.rooms.push(room);
      this.arrayMovers.push(mover);
    } else {
      this.deleteItem(item);
      let isMoverExist = false;
      isMoverExist = this.arrayMovers.some((mover) => {
        if (this.isSameAddresses(mover, addressTo)) {
          let isRoomExist = false;
          isRoomExist = mover.rooms.some(room => {
            if (room.roomType === this.roomTypeTotal[this.roomTypeCurrentIndex]) {
              room.items.push(item);
              return true;
            }
            return false;
          });
          if (!isRoomExist) {
            const room: Room = new Room();
            this.pushItem(room, item);
            mover.rooms.push(room);
          }
          return true;
        }
        return false;
      });
      if (!isMoverExist) {
        const room: Room = new Room();
        this.pushItem(room, item);
        const mover: Mover = this.createMover(addressTo);
        mover.rooms.push(room);
        this.arrayMovers.push(mover);
      }
    }
  }

  confirmOrder() {
    this.arrayMovers.forEach((mover, index, arr) => {
      mover.rooms.forEach((room) => {
        if (room.items.length === 0) {
          arr.splice(index, 1);
          console.log(room.items.length);
        } else {
        }
      });
      console.log(arr);
    });
    console.log(this.arrayMovers);
  }



}
