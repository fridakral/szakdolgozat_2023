import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterNotificationDTO } from 'src/app/DTOs/notificationDTOs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient) { }

  getNotifications():Observable<RegisterNotificationDTO[]>{
    return this.http.get<Array<RegisterNotificationDTO>>(
      `${environment.apiUrl}/notification/unverified`
    )
  }




}
