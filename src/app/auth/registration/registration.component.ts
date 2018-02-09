import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AccountsService} from "../../services/accounts.service";
import {Account} from "../../models/account.model";


@Component({
  selector: 'tmv-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  form: FormGroup;

  constructor(
    private accountsService: AccountsService,
    private router: Router) {

  }

  ngOnInit() {
    this.form = new FormGroup({
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'name': new FormControl(null, [Validators.required]),
      'type': new FormControl(null, Validators.required)
    //  ,'repeatPassword': new FormControl(null, [Validators.required, Validators.minLength(6)])

    });
  }

  onSubmit() {
    const {email, password, name, type } = this.form.value;
    const account = new Account(email, password, name, type);

    this.accountsService.createNewAccount(account)
      .subscribe((/*account: Account*/) => {
        this.router.navigate(['/login'], {
          queryParams: {
            nowCanLogin: true
          }
        });
      });
  }

  // forbiddenEmails(control: FormControl): Promise<any> {
  //   return new Promise((resolve, reject) => {
  //     this.accountsService.getAccountByEmail(control.value)
  //       .subscribe((account:any) => {
  //         if (account) {
  //           resolve({forbiddenEmail: true});
  //         } else {
  //           resolve(null);
  //         }
  //       });
  //   });
  // }

}
