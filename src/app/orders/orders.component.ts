import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Request} from '../models/request.model';
import {RequestService} from '../services/request.service';
import * as moment from 'moment';
import {LoginService} from '../login.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  requests: Request[] = [];

  constructor(private route: ActivatedRoute, private requestService: RequestService, private loginService: LoginService) {
  }

  ngOnInit() {
    let b = this.route.snapshot.paramMap.get('date');
    this.requestService.getRequests().subscribe(
      requests => {
        requests.forEach(request => {
          if (moment(request.movedatetime).format('LL') == b) {
            this.requests.push(request);
          }
        });
      }
    );
  }

}
