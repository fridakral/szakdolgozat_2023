import {Component, EventEmitter, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user-service/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {DetailedUserDTO} from "../../../DTOs/userDTO";

type PasswordForm = {
  newPass: FormControl<string>;
  newPassAgain: FormControl<string>;
  currentPass: FormControl<string>;
}

@Component({
  selector: 'app-security-view',
  templateUrl: './security-view.component.html',
  styleUrls: ['./security-view.component.scss']
})
export class SecurityViewComponent {
  passwordForm!: FormGroup<PasswordForm>;
  noPwMatch: boolean = false
  @Output() newDetailedUser = new EventEmitter<DetailedUserDTO>()

  constructor(private userService: UserService,
              private snack: MatSnackBar) {
    this.setFormToDefault()
  }

  saveNewPassword() {
    if (this.passwordForm.invalid) {
      this.passwordForm.controls.newPass.markAsTouched()
      this.passwordForm.controls.currentPass.markAsTouched()
      this.passwordForm.controls.newPassAgain.markAsTouched()
      return
    }
    if (this.passwordForm.controls.newPass.value !== this.passwordForm.controls.newPassAgain.value) {
      this.noPwMatch = true
      this.snack.open('A két jelszó nem egyezik!', '', {
        duration: 2000,
        horizontalPosition: 'center',
        verticalPosition: "bottom"
      })
      return;
    }
    this.userService.changePassword({
      oldPassword: this.passwordForm.controls.newPass.value,
      newPassword: this.passwordForm.controls.newPass.value,
      employeeId: JSON.parse(localStorage.getItem('userID')!)
    }).subscribe((res) => {
      this.newDetailedUser.emit(res)
      this.setFormToDefault()
      this.noPwMatch = false
    })
  }

  setFormToDefault() {
    this.passwordForm = new FormGroup<PasswordForm>({
      newPass: new FormControl('', {nonNullable: true, validators: [Validators.required]}),
      newPassAgain: new FormControl('', {nonNullable: true, validators: [Validators.required]}),
      currentPass: new FormControl('', {nonNullable: true, validators: [Validators.required]})
    })
  }
}
