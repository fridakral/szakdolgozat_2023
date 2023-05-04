import {HttpErrorResponse} from '@angular/common/http';
import {UserService} from 'src/app/services/user-service/user.service';

import {Router} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Component, OnInit} from '@angular/core';
import {UserRegisterDTO} from "../../DTOs/userDTO";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  minLength = false;
  isLoading = false;
  durationInMilisec: number = 2000;
  emailErrorMsg: string = 'Kötelező!'
  passwordErrorMsg: string = 'Kötelező!'

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private snackBar: MatSnackBar
  ) {
    this.registerForm = new FormGroup({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9._%+-]+\\.[a-z]{2,4}$")]),
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.minLength(3)]),
      tenant: new FormControl('', [Validators.required])
    })
  }


  get firstName() {
    return this.registerForm.get('firstName');
  }

  get lastName() {
    return this.registerForm.get('lastName');
  }

  get email() {
    return this.registerForm.get('email');
  }

  get username() {
    return this.registerForm.get('username');
  }

  get password() {
    return this.registerForm.get('password');
  }

  get tenant() {
    return this.registerForm.get('tenant');
  }

  ngOnInit(): void {
  }

  goToLogin() {
    this.router.navigate(['login']).then();
  }


  lastNameError() {
    return this.lastName?.hasError('required') ? 'A vezetéknév megadása kötelező' : '';
  }

  onSubmit(): void {
    if (this.registerForm.invalid) {
      if (this.email?.hasError('pattern'))
        this.emailErrorMsg = "Hibás formátum!"
      else
        this.emailErrorMsg = "Kötelező!"
      if (this.password?.hasError('minLength'))
        this.emailErrorMsg = "Min. 3 karakter hosszú legyen!"
      else
        this.emailErrorMsg = "Kötelező!"
      if (this.username?.value.length === 0)
        this.username.markAsTouched();
      if (this.lastName?.value.length === 0)
        this.lastName.markAsTouched();
      if (this.firstName?.value.length === 0)
        this.firstName.markAsTouched();
      if (this.password?.value.length === 0)
        this.password.markAsTouched();
      if (this.email?.value.length === 0)
        this.email.markAsTouched();
      if (this.tenant?.value.length === 0)
        this.tenant.markAsTouched();
      return;
    }
    let userData: UserRegisterDTO = {
      username: this.username?.value,
      password: this.password?.value,
      firstName: this.firstName?.value,
      lastName: this.lastName?.value,
      email: this.email?.value,
      tenant: this.tenant?.value
    }
    this.userService.userRegister(userData).subscribe()
  }

}
