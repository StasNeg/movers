import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  tokenIsOk: boolean;
  token: string;
  form: FormGroup;

  constructor(private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)])
    });
    this.activatedRoute.queryParams.subscribe(params => {
      this.token = params['token'];
      this.authService.checkToken(this.token).subscribe((data) => {
        if (data.success === true) {
          this.tokenIsOk = true;
        }
      });
    });
  }

  setNewPassword() {
    const password = this.form.value.password;
    console.log(password);
    this.authService.setNewPassword(password, this.token).subscribe((data) => {
      // TODO MAKE IT WORK WITH JUST ==
      if (data.success === true) {
        alert('Password updated');
        this.router.navigate(['/login']);
      }
    });
  }
}
