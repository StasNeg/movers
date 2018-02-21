import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Subject} from "rxjs/Subject";
import {AddressService} from "../../services/address.service";


@Component({
  selector: 'app-additional-dialog',
  templateUrl: './dialogAddAdditionalAddress.component.html',
  styleUrls: ['./dialogAddAdditionalAddress.component.css']
})
export class AddAdditionalAddressComponent implements OnInit {

  public parentSubject: Subject<any> = new Subject();

  constructor(public dialogRef: MatDialogRef<AddAdditionalAddressComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private addressService: AddressService) {
  }

  ngOnInit() {
  }

  isAdd() {
    return this.addressService.addressAdd == null;
  }

  closeForm(num) {
    this.dialogRef.close(num);
  }
}
