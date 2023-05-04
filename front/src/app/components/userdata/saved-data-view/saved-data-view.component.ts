import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DetailedUserDTO, EmployeeDTO} from "../../../DTOs/userDTO";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user-service/user.service";

type SavesDataForm = {
  firstName: FormControl<string>
  lastName: FormControl<string>
  placeOfBirth: FormControl<string | null>
  address: FormControl<string | null>
  bankAccountNumber: FormControl<string | null>
  identityCardNumber: FormControl<string | null>
  dateOfBirth: FormControl<Date | null>
}

@Component({
  selector: 'app-saved-data-view',
  templateUrl: './saved-data-view.component.html',
  styleUrls: ['./saved-data-view.component.scss']
})
export class SavedDataViewComponent implements OnInit {
  @Input() detailedUser!: DetailedUserDTO;
  @Output() newDetailedUser = new EventEmitter<DetailedUserDTO>()
  savedForm: FormGroup<SavesDataForm>
  shouldChangeToNameEdit: boolean = false
  shouldChangeToBirthDateEdit: boolean = false
  shouldChangeToBirthPlaceEdit: boolean = false
  shouldChangeToAddressEdit: boolean = false
  shouldChangeToBankAccNumEdit: boolean = false
  shouldChangeToIdCardNumEdit: boolean = false

  constructor(private userService: UserService) {
    this.savedForm = new FormGroup<SavesDataForm>({
      firstName: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
      lastName: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]}),
      dateOfBirth: new FormControl<Date | null>(null),
      placeOfBirth: new FormControl<string | null>(null),
      address: new FormControl<string | null>(null),
      bankAccountNumber: new FormControl<string | null>(null),
      identityCardNumber: new FormControl<string | null>(null)
    })
  }

  ngOnInit() {
    this.savedForm.controls.dateOfBirth.setValue(this.detailedUser.dateOfBirth)
    this.savedForm.controls.placeOfBirth.setValue(this.detailedUser.placeOfBirth)
    this.savedForm.controls.lastName.setValue(this.detailedUser.lastName)
    this.savedForm.controls.firstName.setValue(this.detailedUser.firstName)
    this.savedForm.controls.address.setValue(this.detailedUser.address)
    this.savedForm.controls.identityCardNumber.setValue(this.detailedUser.identityCardNumber)
    this.savedForm.controls.bankAccountNumber.setValue(this.detailedUser.bankAccountNumber)
  }

  updateDetails() {
    if (this.savedForm.invalid) {
      this.savedForm.controls.firstName.markAsTouched()
      this.savedForm.controls.lastName.markAsTouched()
      return
    }
    const employee: EmployeeDTO = {
      address: this.savedForm.value.address!,
      bankAccountNumber: this.savedForm.value.bankAccountNumber!,
      dateOfBirth: this.savedForm.value.dateOfBirth!,
      firstName: this.savedForm.value.firstName!,
      identityCardNumber: this.savedForm.value.identityCardNumber!,
      lastName: this.savedForm.value.lastName!,
      placeOfBirth: this.savedForm.value.placeOfBirth!,
      userId: this.detailedUser.id
    }

    this.userService.saveUserDetails(employee).subscribe((res) => {
      this.newDetailedUser.emit(res)
    })

    this.shouldChangeToNameEdit = false
    this.shouldChangeToBirthDateEdit = false
    this.shouldChangeToBirthPlaceEdit = false
    this.shouldChangeToAddressEdit = false
    this.shouldChangeToBankAccNumEdit = false
    this.shouldChangeToIdCardNumEdit = false
  }
}
