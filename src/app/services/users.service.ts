import {Injectable} from '@angular/core';
import {User} from '../auth/models/user.model';
import {HttpClient} from '@angular/common/http';


@Injectable()
export class UsersService {
  constructor(private http: HttpClient) {
  }


  getUserByEmail(email: string, password: string) {
    var data = {email, password};
    return this.http.post(`http://localhost:8080/login`, data);
  }

  private userIn;

  putUserIn(user: User) {
    this.userIn = user;
  }

  getUserIn() {
    return this.userIn;
  }

}
