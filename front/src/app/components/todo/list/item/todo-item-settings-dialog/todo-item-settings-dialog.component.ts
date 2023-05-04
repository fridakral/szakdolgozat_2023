import {Component, ElementRef, Inject, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TaskDTO, UpdateTaskBasic} from "../../../../../DTOs/projectDTOs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TodoService} from "../../../../../services/todo-service/todo.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatSelectChange} from "@angular/material/select";

export type TodoItemFormGroup = {
  taskName: FormControl<string>;
  taskDescription: FormControl<string>;
  taskDueDate: FormControl<Date>;
  usersOnTask: FormControl<string[]>
}

export type AddUserToTaskDTO = {
  taskId: number;
  usernames: string[]
}

export type UserForProjectSettings = {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  isManager: boolean;
}

@Component({
  selector: 'app-todo-item-settings-dialog',
  templateUrl: './todo-item-settings-dialog.component.html',
  styleUrls: ['./todo-item-settings-dialog.component.scss'],
  providers: []
})
export class TodoItemSettingsDialogComponent implements OnInit {
  @ViewChild('warningView', {static: true}) warningView: TemplateRef<any> | null = null;
  @ViewChild('formView', {static: true}) formView: TemplateRef<any> | null = null;
  @ViewChild('dataView', {static: true}) dataView: TemplateRef<any> | null = null;
  primaryView: TemplateRef<any> | null = null;
  show = true;
  todoItemForm: FormGroup<TodoItemFormGroup>
  allUsersInProject: UserForProjectSettings[] = []
  selectedUser: string = '';

  constructor(public dialogRef: MatDialogRef<TodoItemSettingsDialogComponent>,
              private todoService: TodoService,
              private snackBar: MatSnackBar,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.todoItemForm = new FormGroup<TodoItemFormGroup>(<TodoItemFormGroup>{
      taskName: new FormControl<string>(data.item.taskName, {nonNullable: true, validators: [Validators.required]}),
      taskDescription: new FormControl<string>(data.item.taskDescription, {nonNullable: true}),
      taskDueDate: new FormControl<Date>(data.item.taskDueDate, {nonNullable: true}),
      usersOnTask: new FormControl<string[]>(data.item.usersOnTask)
    })
  }

  ngOnInit() {
    this.primaryView = this.dataView;
    this.todoService.getUsersOnProject(this.data.projectId).subscribe(res => {
      this.allUsersInProject = res;
    })
  }

  switchViews(view: 'data' | 'form' | 'warning') {
    if (view === 'data') {
      this.primaryView = this.dataView
    } else if (view === 'form') {
      this.primaryView = this.formView
    } else {
      this.primaryView = this.warningView
    }
  }

  deleteItemFromList(item: TaskDTO) {
    this.todoService.deleteTask(item.taskId).subscribe(() => {
      this.dialogRef.close(item);
    });
  }

  saveTodoData() {
    if (this.todoItemForm.invalid) {
      return;
    }
    let newData: UpdateTaskBasic = {
      description: this.todoItemForm.controls.taskDescription.value,
      dueDate: this.todoItemForm.controls.taskDueDate.value,
      id: this.data.item.taskId,
      name: this.todoItemForm.controls.taskName.value
    }
    this.todoService.saveTaskDetails(newData).subscribe({
      next: () => {
        this.dialogRef.close(null);
      },
      error: (error: HttpErrorResponse) => {
        this.snackBar.open('Error' + error.message, '', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'bottom'
        })
      }
    })
    this.todoService.addUserToTask({
      taskId: this.data.item.taskId,
      usernames: this.todoItemForm.controls.usersOnTask.value
    }).subscribe()
  }

  switchViewIfFormChanged(): boolean {
    if (this.data.taskName !== this.todoItemForm.controls.taskName.value ||
      this.data.taskDescription !== this.todoItemForm.controls.taskDescription.value ||
      this.data.taskDueDate !== this.todoItemForm.controls.taskDueDate.value) {
      this.switchViews("warning")
      return true
    }
    return false
  }

  closeDialog() {
    const isViewChanged = this.switchViewIfFormChanged();
    if (!isViewChanged) {
      this.dialogRef.close(null)
    }
  }


  change(event: MatSelectChange) {
    this.selectedUser = event.value
    const users = this.todoItemForm.controls.usersOnTask.value;
    users.push(event.value);
    this.todoItemForm.controls.usersOnTask.patchValue(users);
  }

  remove(removeUser: string) {
    console.log(removeUser)
    const users = this.todoItemForm.controls.usersOnTask.value;
    users.forEach((user, index) => {
      if (user === removeUser) users.splice(index, 1);
    });
    this.todoItemForm.controls.usersOnTask.patchValue(users);
    this.selectedUser = ""
  }
}
