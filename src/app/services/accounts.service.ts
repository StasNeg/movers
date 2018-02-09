import {Http, Response} from '@angular/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';

import {Account} from '../models/account.model';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AccountsService {
  constructor(private http: HttpClient) {}

  getAccountByEmail(email: string) {
    // return this.http.get(`http://localhost:3000/accounts?email=${email}`)
    //   .map((response: Response) => response.json())
    //   .map((account: Account) => account[0] ? account[0] : undefined);
  }

  createNewAccount(account: Account) {
    return this.http.post('http://localhost:8080/signup', account);
  }


}
