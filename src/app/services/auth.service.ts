
import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {DataTo} from '../interfaces/data-to';

@Injectable()
export class AuthService {

  private url = 'http://localhost:8080';

  // TODO REMOVE ACCESS-CONTROL '*'
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  });

  constructor(private http: HttpClient) {
  }

  sendResetLink(email: string) {
    return this.http.post<DataTo>(this.url + '/forgot', {email: email}, {headers: this.headers});
  }

  checkToken(token: string) {
    return this.http.get<DataTo>(this.url + '/token/check', {headers: this.headers, params: {token: token}});
  }

  setNewPassword(password: string, token: string) {
    return this.http.post<DataTo>(this.url + '/reset', {password: password, token: token}, {headers: this.headers});
  }
  private isAuthenticated = false;
  login() {
    this.isAuthenticated = true;
  }
  logout() {
    this.isAuthenticated = false;
    window.localStorage.clear();
  }
  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }

}


