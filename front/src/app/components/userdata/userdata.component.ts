import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../../services/user-service/user.service";
import {DetailedUserDTO} from "../../DTOs/userDTO";

@Component({
  selector: 'app-userdata',
  templateUrl: './userdata.component.html',
  styleUrls: ['./userdata.component.scss']
})
export class UserdataComponent implements OnInit {

  @ViewChild('userData', {static: true}) userData: TemplateRef<any> | null = null;
  @ViewChild('savedData', {static: true}) savedData: TemplateRef<any> | null = null;
  @ViewChild('security', {static: true}) security: TemplateRef<any> | null = null;
  @ViewChild('init', {static: true}) init: TemplateRef<any> | null = null;
  primaryTemplate: TemplateRef<any> | null = null;
  whichIsActive: 'userData' | 'savedData' | 'security' | 'init' = "init"
  detailedUser!: DetailedUserDTO

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.primaryTemplate = this.init

    this.userService.getDetailedEmployee(JSON.parse(localStorage.getItem('userID')!)).subscribe(res => {
      this.detailedUser = res
    })
  }

  setUserDataToActive() {
    this.whichIsActive = 'userData'
    this.primaryTemplate = this.userData
  }

  setSavedDateToActive() {
    this.whichIsActive = 'savedData'
    this.primaryTemplate = this.savedData
  }

  setSecurityToActive() {
    this.whichIsActive = 'security'
    this.primaryTemplate = this.security
  }

  setNewDetailedUser(event: DetailedUserDTO) {
    this.detailedUser = event
  }
}
