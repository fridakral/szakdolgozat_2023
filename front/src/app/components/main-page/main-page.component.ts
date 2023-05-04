import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {NotificationComponent} from "../notification/notification.component";
import {NotificationService} from 'src/app/services/notifocation-service/notification.service';
import {RegisterNotificationDTO} from 'src/app/DTOs/notificationDTOs';
import {DialogPosition, MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {
  notificationImgElem = document.getElementsByClassName('notificationImg')
  notificationDialogWidth: number = window.innerWidth * 0.5;

  constructor(private router: Router,
              private dialog: MatDialog,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
  }


  openNotificationDialog($event: MouseEvent) {

    this.notificationService.getNotifications().subscribe(
      (res: Array<RegisterNotificationDTO>) => {

        const dialogPosition: DialogPosition = {
          top: window.innerHeight * 0.1 - 10 + 'px',
          left: window.innerWidth * 0.9 + 10 - this.notificationDialogWidth + 60 + 'px'
        };
        const dialogRef = this.dialog.open(NotificationComponent, {
          width: this.notificationDialogWidth + "px",
          position: dialogPosition,
          hasBackdrop: true,
          panelClass: 'dialog',
          maxHeight: '70%',
          data: res
        });
        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
        });
      }
    )


    /* próbakód a rendes adatok nélkül:
    let res: RegisterNotificationDTO[] = [
      {
        firstName: "Pista",
        lastName: "Kiss",
        notificationType: notificationType.DATA_CHANGE
      }
    ]
    */


  }

  navToTodo() {
    this.router.navigate(['todoNav']).then()
  }

  navUserData() {
    this.router.navigate(['userData']).then()
  }
}
