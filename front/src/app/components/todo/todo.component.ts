import {Component, OnInit} from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from "@angular/cdk/drag-drop";
import {ProjektService} from 'src/app/services/project-service/projekt.service';
import {ListDTO, TaskDTO} from "../../DTOs/projectDTOs";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.scss']
})
export class TodoComponent implements OnInit {
  lists?: ListDTO[];
  projectId: number = 0;
  listsIds: string[] = [];

  constructor(private projectService: ProjektService) {
    projectService.currentProjectSubject.subscribe(res => {
      this.projectId = res
    })
  }

  ngOnInit(): void {
    this.projectService.getDetailedProject(this.projectId).subscribe(res => {
      this.lists = res.lists;
      this.lists.forEach(list => {
        this.listsIds.push(String(list.listId))
      })
    })
  }
}
