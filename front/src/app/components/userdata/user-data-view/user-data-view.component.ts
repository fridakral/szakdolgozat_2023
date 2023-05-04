import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DetailedUserDTO} from "../../../DTOs/userDTO";
import {UserService} from "../../../services/user-service/user.service";

type UserForm = {
  postDesc: FormControl<string | null>
  username: FormControl<string>
  email: FormControl<string>
  post: FormControl<string | null>
}

@Component({
  selector: 'app-user-data-view',
  templateUrl: './user-data-view.component.html',
  styleUrls: ['./user-data-view.component.scss']
})
export class UserDataViewComponent implements OnInit {
  @Input() detailedUser!: DetailedUserDTO;
  userForm!: FormGroup<UserForm>;
  @Output() newDetailedUser = new EventEmitter<DetailedUserDTO>()

  constructor(private userService: UserService) {
    this.setFormToDefault()
  }

  ngOnInit() {
    this.userForm.controls.postDesc.setValue(this.detailedUser?.postDescription)
    this.userForm.controls.post.setValue(this.detailedUser?.post)
    this.userForm.controls.username.setValue(this.detailedUser?.username)
    this.userForm.controls.email.setValue(this.detailedUser?.email)
  }

  saveUserDetails() {
    if (this.userForm.invalid) {
      this.userForm.controls.email.markAsTouched()
      this.userForm.controls.username.markAsTouched()
      return
    }

    this.userService.updateUserData(
      {
        id: this.detailedUser.id,
        postDescription: this.userForm.controls.postDesc.value,
        post: this.userForm.controls.post.value,
        username: this.userForm.controls.username.value,
        email: this.userForm.controls.email.value
      }
    ).subscribe(res => {
      this.newDetailedUser.emit(res)
    })
  }

  setFormToDefault() {
    this.userForm = new FormGroup<UserForm>({
      postDesc: new FormControl(''),
      username: new FormControl('', {nonNullable: true, validators: [Validators.required]}),
      email: new FormControl('', {nonNullable: true, validators: [Validators.required, Validators.email]}),
      post: new FormControl('')
    })
  }
}
