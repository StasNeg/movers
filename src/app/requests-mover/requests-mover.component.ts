import {Component, OnInit} from '@angular/core';
import {Request} from '../models/request.model';
import {RequestService} from '../services/request.service';
import {DialogService} from "ng2-bootstrap-modal";
import {RequestDetailDialog} from "./requests-detail-modal/requests-detail-modal.component";
import {RequestDetail} from "../models/request-detail.model";


@Component({
  selector: 'app-requests-mover',
  templateUrl: './requests-mover.component.html',
  styleUrls: ['./requests-mover.component.css'],


})
export class RequestsMoverComponent implements OnInit {
  requests: Request [];
  requestDetail: RequestDetail;

  constructor(private requestService: RequestService, private dialogService: DialogService) {
  }

  ngOnInit() {
    this.requestService.getRequests().subscribe(
      (request: Request []) => {
        this.requests = request;
      }
    );
  }

  onClick(index) {
    this.requestService.getRequestsDetail(this.requests[index].id).subscribe(
      (request: RequestDetail) => {
        this.requestDetail = request;
        let disposable = this.dialogService.addDialog(RequestDetailDialog, {data: this.requestDetail})
          .subscribe();
      }
    );


  }
}
