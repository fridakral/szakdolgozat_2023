import {Injectable} from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from "@angular/cdk/drag-drop";
import {CreateTaskDTO, ListDTO, TaskDTO, UpdateTaskBasic} from "../../DTOs/projectDTOs";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {environment} from "src/environments/environment";
import {
  MoveTaskBetweenListsRequest,
  MoveTaskInListRequest
} from "../../components/todo/list/list.component";
import {
  AddUserToTaskDTO,
  UserForProjectSettings
} from "../../components/todo/list/item/todo-item-settings-dialog/todo-item-settings-dialog.component";

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private dropEventSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  dropEvent = this.dropEventSubject.asObservable();

  constructor(private http: HttpClient) {
  }

  public drop(event: CdkDragDrop<TaskDTO[]>, listId: string) {
    const droppedTask = event.previousContainer.data[event.previousIndex]
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
    this.dropEventSubject.next({
      event: event,
      container: event.container,
      prev: event.previousContainer,
      listId: listId,
      droppedTask: droppedTask
    })
  }

  public createTask(createTask: CreateTaskDTO): Observable<TaskDTO> {
    return this.http.post<TaskDTO>(
      `${environment.apiUrl}/list/createTask`,
      createTask
    )
  }

  public deleteTask(id: number) {
    return this.http.get(`${environment.apiUrl}/task/delete/${id}`)
  }

  public getUsersOnProject(id: number): Observable<UserForProjectSettings[]> {
    return this.http.get<UserForProjectSettings[]>(`${environment.apiUrl}/role/usersOnProject/${id}`)
  }

  public moveTaskInList(req: MoveTaskInListRequest) {
    return this.http.post(`${environment.apiUrl}/list/moveTaskInList`, req)
  }

  public moveTask(req: MoveTaskBetweenListsRequest): Observable<ListDTO[]> {
    return this.http.post<ListDTO[]>(`${environment.apiUrl}/list/moveTaskBetweenLists`, req)
  }

  public saveTaskDetails(task: UpdateTaskBasic): Observable<TaskDTO> {
    return this.http.post<TaskDTO>(`${environment.apiUrl}/task/update`, task)
  }

  public addUserToTask(users: AddUserToTaskDTO) {
    return this.http.post<TaskDTO>(`${environment.apiUrl}/task/addUsers`, users)
  }
}
