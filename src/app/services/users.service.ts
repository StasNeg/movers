import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable()
export class UsersService {
  constructor(private http: HttpClient) {
  }

  static lurryInfo = {
    type: ['tender', 'lurry 4t', 'lurry 7.5t', 'lurry 12t', 'lurry 15t'],
    works: ['1 room', '2 rooms', '3 rooms', '4 rooms', '5+ rooms'],
    usage: [1, 2, 3]
  };
  static headers = {
    type: 'lurry type',
    works: 'space to move',
    usage: 'number of around trips'
  };

  getUserByEmail(email: string, password: string) {
    var data = {email, password};
    return this.http.post(`http://localhost:8080/login`, data);
  }

  private userIn = null;


  setUserIn(user: any) {
    this.userIn = user;
    localStorage.setItem('user', JSON.stringify(this.userIn));
    console.log(this.userIn);
  }

  getUserIn() {
    return this.userIn;
  }

}
