import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';
import {Request} from './models/request.model';
import {Injectable} from '@angular/core';
import {LoginService} from './login.service';
import {JsonModel} from './models/json.model';
import {UsersService} from './services/users.service';


@Injectable()
export class RequestService {
  requests = [new Request()];
  url: string = 'http://localhost:8080/dashboard/requests/customer/getrecent?token=';

  constructor(private http: HttpClient, private loginService: LoginService, private userService: UsersService) {
  }

  getRequests() {
    const taleUrl = this.userService.getUserIn().id;

    return this.http.get<JsonModel>(this.url + this.loginService.token + taleUrl)
      .map((request: JsonModel) => {
        this.requests = request.data;
        console.log(this.url);
        console.log(this.userService.getUserIn().id);
        return this.requests;
      });
  }
}


