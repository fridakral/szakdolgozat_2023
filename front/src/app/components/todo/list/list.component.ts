import {Component, Input, OnInit} from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from "@angular/cdk/drag-drop";
import {CreateTaskDTO, ListDTO, TaskDTO} from "../../../DTOs/projectDTOs";
import {TodoService} from "../../../services/todo-service/todo.service";
import {FormControl, FormGroup} from "@angular/forms";

type FormGroupInterface = {
  item: FormControl<string>;

}
type TaskAndPosition = {
  taskId: number;
  position: number;
}

export type MoveTaskInListRequest = {
  listId: number;
  tasksAndPositions: TaskAndPosition[];
}

export type MoveTaskBetweenListsRequest = {
  beforeListId: number;
  afterListId: number;
  afterTasks: TaskAndPosition[];
  beforeTask: TaskAndPosition[];
  movedTaskId: number;
}

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  @Input() list!: ListDTO;
  @Input() listsIds!: string[];
  @Input() listId!: string;
  @Input() projectId!: number;
  defaultTask: TaskDTO = {
    serial: 0,
    taskCreatedOn: new Date(),
    taskDescription: "",
    taskDueDate: new Date(),
    taskId: 0,
    taskName: "",
    usersOnTask: []
  };
  todoItemForm: FormGroup<FormGroupInterface> = new FormGroup<FormGroupInterface>({
      item: new FormControl<string>(this.defaultTask.taskName, {nonNullable: true})
    }
  );

  constructor(private todoService: TodoService) {
  }

  ngOnInit(): void {

  }

  onDrop(event: CdkDragDrop<TaskDTO[]>) {
    this.todoService.drop(event, this.listId);
    let sub = this.todoService.dropEvent.subscribe((res) => {
      const modifiedPrevArray: TaskAndPosition[] = res.prev.data.map((item: any, index: number) => {
        return {
          taskId: item.taskId,
          position: index + 1
        };
      });

      const modifiedArray: TaskAndPosition[] = res.container.data.map((item: any, index: number) => {
        return {
          taskId: item.taskId,
          position: index + 1
        };
      });

      if (res.prev.id === res.container.id) {
        const moveTaskInList: MoveTaskInListRequest = {
          listId: res.prev.id,
          tasksAndPositions: modifiedPrevArray
        }
        this.todoService.moveTaskInList(moveTaskInList).subscribe();
      } else {
        const moveTask: MoveTaskBetweenListsRequest = {
          beforeListId: res.prev.id,
          beforeTask: modifiedPrevArray,

          afterListId: res.container.id,
          afterTasks: modifiedArray,
          movedTaskId: res.droppedTask.taskId
        }
        this.todoService.moveTask(moveTask).subscribe()
      }
    })
  }

  addItemToList() {
    if (this.todoItemForm.controls.item.value.length !== 0) {

      let task: CreateTaskDTO = {
        'listId': this.list.listId,
        'name': this.todoItemForm.controls.item.value,
        'dueDate': new Date("2024-06-05"),
        'createdOn': new Date(),
        'description': "ez egy próba task, egy próba leírással"
      }
      this.todoService.createTask(task).subscribe((item) => {
          let newTask: TaskDTO = {
            ...item,
            taskCreatedOn: new Date()
          }
          this.list.tasks.push(newTask);
        }
      )
    }
    this.todoItemForm.controls.item.patchValue(this.resetTask().taskName)
  }

  resetTask() {
    return {
      taskCreatedOn: new Date(),
      taskDescription: "",
      taskDueDate: new Date(),
      taskId: 0,
      taskName: "",
      usersOnTask: [],
      serial: 0
    };
  }

  deleteItemFromList(task: TaskDTO) {
    //TODO: db-ből is kiszedni
    if (this.list.tasks.includes(task))
      this.list.tasks.splice(this.list.tasks.indexOf(task), 1)
  }
}
