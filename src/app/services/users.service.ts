import {Injectable} from '@angular/core';
// import {User} from '../auth/models/user.model';
import {HttpClient} from '@angular/common/http';


@Injectable()
export class UsersService {
  constructor(private http: HttpClient) {
  }


  getUserByEmail(email: string, password: string) {
    var data = {email, password};
    return this.http.post(`http://localhost:8080/login`, data);
  }

  private userIn = null;


  setUserIn(user: any) {
    this.userIn = user;
    console.log(this.userIn);

  }

  getUserIn() {
    return this.userIn;
  }

}
