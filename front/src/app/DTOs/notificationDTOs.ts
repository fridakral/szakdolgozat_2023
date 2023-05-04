import { notificationType } from "../enums/notificationTypes";


export interface RegisterNotificationDTO{
    firstName: string,
    lastName: string,
    notificationType: notificationType,
    userId: number
}
