import {Component, OnInit} from "@angular/core";
import {AddressService} from "../services/address.service";
import {Subject} from "rxjs/Subject";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material";
import {AddAdditionalAddressComponent} from "./additional-address-modal-form/dialogAddAdditionalAddress.component";
import * as moment from 'moment';

@Component({
  selector: 'app-move-main-address',
  templateUrl: './move-main-address.component.html',
  styleUrls: ['./move-main-address.component.css']
})

export class MoveMainAddressComponent implements OnInit {

  public addresses;
  public typeRadio;
  public parentSubject: Subject<any> = new Subject();
  public dateOrder = new Date();
  public timeOrder = null;

  constructor(private addressService: AddressService, private router: Router, public dialog: MatDialog) {
  }

  ngOnInit() {
  }

  deleteBetween(i) {
    this.addressService.addressesTo.splice(i, 1);
    this.addresses = this.addressService.addressesTo;
  }

  upBetween(i) {
    let temp = this.addressService.addressesTo[i - 1];
    this.addressService.addressesTo[i - 1] = this.addressService.addressesTo[i];
    this.addressService.addressesTo[i] = temp;
    this.addresses = this.addressService.addressesTo;
  }

  addBetween() {
    const dialogRef = this.dialog.open(AddAdditionalAddressComponent, {
      position: {
        left: '350px'
      },
      height: '400px',
      width: '600px'
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 1) {
        this.addressService.addAddressAdd();
        this.addresses = this.addressService.addressesTo;
      }
    })

  }

  addForm() {
    return this.addressService.isCorrectAdd();
  }

  submitForm() {
    if (this.addressService.isCorrectForm()
      && this.addressService.typeAppartment != null
      && this.dateOrder != null
      && this.timeOrder != null
    )
      return true;
    return false;
  }

  onTypeRadioClick() {
    this.addressService.typeAppartment = this.typeRadio;
  }


  onSubmitForm() {
    this.addressService.addressesTo.push(this.addressService.addressTo);
    let result = {
      typeOfAppartment: this.addressService.typeAppartment,
      addressFrom: this.addressService.addressFrom,
      addressesTo: this.addressService.addressesTo,
      date: moment(this.dateOrder).format('YYYY-MM-DD').toString(),
      time: moment(this.timeOrder).format('HH:mm').toString()
    }
    this.addressService.clearAddress();
    localStorage.setItem('adresses', JSON.stringify(result));

    this.router.navigate(['/room'],)
  }

}
