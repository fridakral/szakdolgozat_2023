import {AfterViewInit, Component, Inject, OnInit} from '@angular/core';
import {RegisterNotificationDTO} from 'src/app/DTOs/notificationDTOs';
import {notificationType} from 'src/app/enums/notificationTypes';
import {UserService} from 'src/app/services/user-service/user.service';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit, AfterViewInit {
  panelOpenState: boolean = false;
  notifications: RegisterNotificationDTO[] = []
  isRegister: boolean = false;

  constructor(@Inject(MAT_DIALOG_DATA) data: RegisterNotificationDTO[],
              private userService: UserService) {
    this.notifications = data
  }

  ngOnInit(): void {
    console.log(this.notifications)
  }


  verifyUser(notification: RegisterNotificationDTO) {
    this.userService.verifyUser(notification?.userId).subscribe()

  }

  deleteUser(notification: RegisterNotificationDTO) {
    this.userService.deleteUser(notification.userId).subscribe(

    )
  }


  ngAfterViewInit(): void {
    const header = document.getElementById('dialogHeader')
    if (header) {
      //header.style.animation = 'start';
    }
  }

}
