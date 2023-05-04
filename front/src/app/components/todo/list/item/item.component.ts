import {Component, EventEmitter, Input, Output, OnInit} from '@angular/core';
import {TaskDTO} from "../../../../DTOs/projectDTOs";
import {TodoService} from "../../../../services/todo-service/todo.service";
import {TodoItemSettingsDialogComponent} from "./todo-item-settings-dialog/todo-item-settings-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss'],
  providers: [MatDialog]
})
export class ItemComponent implements OnInit {
  @Input() item!: TaskDTO;
  @Input() projectId!: number;
  @Output() deleteEvent = new EventEmitter<TaskDTO>();

  constructor(private todoService: TodoService, private dialog: MatDialog) {
  }

  ngOnInit(): void {
  }


  openItemDialog(item: TaskDTO) {
    const dialogRef = this.dialog.open(TodoItemSettingsDialogComponent, {
      data: {
        item: item,
        projectId: this.projectId
      },
      width: "600px"
    });

    dialogRef.afterClosed().subscribe((result: TaskDTO | null) => {
      console.log(`Dialog result: ${result}`);
      if (result) {
        this.deleteEvent.emit(result)
      }
    });
  }
}
