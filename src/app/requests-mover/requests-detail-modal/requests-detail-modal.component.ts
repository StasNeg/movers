import {Component} from '@angular/core';
import {DialogComponent, DialogService} from 'ng2-bootstrap-modal';
import {RequestDetail} from "../../models/request-detail.model";


export interface ConfirmModel {
}


@Component({
  selector: 'app-request-detail',
  templateUrl: './requests-detail-modal.component.html',
  styleUrls: ['./requests-detail-modal.component.css']
})
export class RequestDetailDialog extends DialogComponent<ConfirmModel, RequestDetail> implements ConfirmModel {

  data: RequestDetail;

  constructor(dialogService: DialogService) {
    super(dialogService);
  }

  confirm() {
    console.log(this.data);

  }



}
