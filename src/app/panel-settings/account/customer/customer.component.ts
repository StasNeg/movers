import {Component, OnInit} from '@angular/core';
import {UsersService} from '../../../services/users.service';


@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customer = this.ds2.getUserIn().id;

  constructor(private ds2: UsersService) {
  }

  ngOnInit() {
  }

}
