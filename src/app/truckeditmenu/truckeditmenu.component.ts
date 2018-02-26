import {Component} from '@angular/core';
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
  areaAnswers = ['CENTER', 'SOUTH', 'NORTH', 'EAST', 'ALL'];
  roomsAnswers = [1, 2, 3, 4, 5];
  movesDayAnswers = [1, 2, 3];
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

  selectedToArea(e) {
    this.trucks.area = e.target.value;


  }

  selectedToRoom(e) {
    this.trucks.roomsCount = e.target.value;


  }

  selectedToMoves(e) {
    this.trucks.movesDay = e.target.value;
  }

}
