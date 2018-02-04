import {Component, OnInit} from '@angular/core';
import {Request} from '../models/request.model';
import {RequestService} from '../request.service';


@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css'],


})
export class RequestsComponent implements OnInit {
  requests: Request [];

  constructor(private requestService: RequestService) {
  }

  ngOnInit() {
    this.requestService.getRequests().subscribe(
      (request: Request []) => {
        this.requests = request;
        console.log(request);
      }
    );

  }

}
