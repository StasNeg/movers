import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {FormControl, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
  }

  sendResetLink(form: NgForm) {
    const email = form.value.email;
    if (form.valid) {
      this.authService.sendResetLink(email).subscribe(
        (data) => {
          // TODO <TYPE> IT AND MAKE IT WORK WITH JUST ==
          if (data.success === true) {
            alert('Email was sent to ' + email);
          }
        }
      );
    }
  }

}
