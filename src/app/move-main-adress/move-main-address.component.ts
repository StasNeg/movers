import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AddressService} from '../services/address.service'
import {Subject} from "rxjs/Subject";
import {Router} from "@angular/router";


@Component({
  selector: 'app-move-main-address',
  templateUrl: './move-main-address.component.html',
  styleUrls: ['./move-main-address.component.css']
})

export class MoveMainAddressComponent implements OnInit {

  public addresses;
  public typeRadio;
  public parentSubject: Subject<any> = new Subject();

  constructor(private addressService: AddressService, private router: Router) {
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
    this.addressService.addTo();
    this.addresses = this.addressService.addressesTo;
    this.parentSubject.next('some value');
  }

  addForm() {
    return this.addressService.isCorrectAdd();
  }

  submitForm() {
    if (this.addressService.addressesTo.length > 0 && this.addressService.isCorrectForm() && this.addressService.typeAppartment != null)
      return true;
    return false;
  }

  onTypeRadioClick() {
    this.addressService.typeAppartment = this.typeRadio;
  }


  onSubmitForm() {
    let result = {
      typeOfAppartment: this.addressService.typeAppartment,
      addressFrom: this.addressService.addressFrom,
      addressesTo: this.addressService.addressesTo,
      date: null,
      time: null
    }
    console.log(JSON.stringify(result));
    this.router.navigate(['/room'],)
  }


}
