import { Component } from '@angular/core';
import {DialogComponent, DialogService} from 'ng2-bootstrap-modal';
import {DatatrucksModel} from '../models/datatrucks.model';


export interface ConfirmModel {
  title: string;
  message: string;
  trucks: DatatrucksModel;
}

@Component({
  selector: 'app-truckeditmenu',
  templateUrl: './truckeditmenu.component.html',
  styleUrls: ['./truckeditmenu.component.css']
})
export class TruckEditmenuComponent extends DialogComponent<ConfirmModel, DatatrucksModel> implements ConfirmModel {
  title: string;
  message: string;
  trucks: DatatrucksModel;
  constructor(dialogService: DialogService) {
    super(dialogService);
  }

  confirm() {
    this.result = this.trucks;
    this.close();
  }

}
