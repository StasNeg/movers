import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeadersComponent implements OnInit {


  constructor(private router: Router) {
  }

  ngOnInit() {

}

  hasUser() {
    return localStorage.getItem('user')===null;
  }

  onButtonClick(){
    let type = JSON.parse(localStorage.getItem('user')).type;
    if (type === 'customer') {
      this.router.navigate(['/requests']);
    } else {
      this.router.navigate(['/requestsMover']);
    }
  }
}
