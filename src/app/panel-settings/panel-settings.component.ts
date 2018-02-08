import { Component, OnInit } from '@angular/core';
import {FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UsersService} from "../services/users.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-panel-settings',
  templateUrl: './panel-settings.component.html',
  styleUrls: ['./panel-settings.component.css']
})
export class PanelSettingsComponent implements OnInit {
  form: FormGroup;

  constructor(private usersService: UsersService,
              private authService: AuthService,
              private router: Router,
              private route: ActivatedRoute) {
  }
  private linkAccount = '';
  ngOnInit() {
    console.log(this.usersService.getUserIn().type);
    if (this.usersService.getUserIn().type=="mover") {this.linkAccount='/mover'}
    else {this.linkAccount='/customer'};
        console.log(this.linkAccount);
  }
  }
