import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  tokenIsOk: boolean;
  token: string;

  constructor(private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params['token'];
      this.authService.checkToken(this.token).subscribe((data) => {
        if (data.success === true) {
          this.tokenIsOk = true;
        }
      });
    });
  }

  setNewPassword(form: NgForm) {
    const password = form.value.password;
    this.authService.setNewPassword(password, this.token).subscribe((data) => {
      // TODO MAKE IT WORK WITH JUST ==
      if (data.success === true) {
        alert('Password updated');
        this.router.navigate(['/login']);
      }
    });
  }
}
