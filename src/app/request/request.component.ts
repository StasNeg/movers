import {Component, OnInit} from '@angular/core';
import {ConnectionsService} from '../services/connections.service';
import {Router, ActivatedRoute, ParamMap} from '@angular/router';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {

  // TODO GET REQUEST_ID FROM URL
  request_id = 100004;
  // TODO GET MOVER_ID FROM ENVIRONMENT
  mover_id = 100002;
  request: {};

  constructor(private router: Router,
              private connectionsService: ConnectionsService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    // this.request_id = this.route.paramMap
    //   .switchMap((params: ParamMap) => params.get('id'));

    this.connectionsService.getRequestInfo(this.request_id).subscribe((res) => {
      if (res.success) {
        this.request = res.data;
      } else {
        console.log('No request received');
      }
    });
  }

  assignRequestToMover() {
    this.connectionsService.assignRequestToMover(this.mover_id, this.request_id).subscribe((res) => {
      if (res.success) {
        alert(res.data);
      } else {
        alert(res.data);
      }
      // TODO GO BACK TO THE DASHBOARD
    });
  }
}
