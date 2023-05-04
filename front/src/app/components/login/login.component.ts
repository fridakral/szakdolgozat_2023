import {HeaderService} from './../../services/header-service/header.service';
import {JwtService} from './../../services/jwt-service/jwt.service';
import {UserLoginDTO} from './../../DTOs/userDTO';

import {Subject} from 'rxjs';
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {UserService} from 'src/app/services/user-service/user.service';
import {roleEnum} from 'src/app/enums/roleEnum';
import {HttpErrorResponse} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userForm: FormGroup;
  minLength = false;
  isLoading = false;
  isLoggedIn: boolean = false;
  post = 0;
  durationInMilisec: number = 2000;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private jwtService: JwtService,
    private snackBar: MatSnackBar
  ) {
    this.userForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      tenant: new FormControl('', [Validators.required])
    })
  }

  get username() {
    return this.userForm.get('username');
  }

  get password() {
    return this.userForm.get('password');
  }

  get tenant() {
    return this.userForm.get("tenant");
  }

  ngOnInit(): void {
  }

  goToRegister() {
    this.router.navigate(['register']).then();
  }

  login() {
    sessionStorage.clear();
    this.isLoading = true;
    if (this.userForm.invalid) {
      if (this.username?.value.length === 0) {
        this.username.markAsTouched();
      }
      if (this.password?.value.length === 0) {
        this.password.markAsTouched();
      }
      if (this.tenant?.value.length === 0) {
        this.tenant.markAsTouched();
      }
      return;
    }
    this.userService.userLogin({
      username: this.username?.value,
      password: this.password?.value,
      tenant: this.tenant?.value
    })
      .subscribe(
        (next) => {
          let token = JSON.stringify(next.body);
          token = token.substring(10);
          token = token.slice(0, -2);
          this.jwtService.saveJwtToken(token);
          this.isLoading = false;
          this.userService
            .getUserByName(this.username?.value)
            .subscribe(
              (res: UserLoginDTO) => {
                localStorage.setItem('userID', String(res.id));
                localStorage.setItem('userName', this.username?.value);
                localStorage.setItem('post', String(res.post));
                localStorage.setItem('token', token);
                this.router.navigate(['home']).then();
              }
            );
        },
        (err: HttpErrorResponse) => {
          this.snackBar.open('Sikertelen bejelentkezés', '', {
            duration: this.durationInMilisec,
          })
          this.isLoading = false;
        }
      )
  }
}
