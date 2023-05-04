import {Component, Inject, TemplateRef, ViewChild} from '@angular/core';
import {ProjectSettings, TaskDTO, UpdateTaskBasic} from "../../../../DTOs/projectDTOs";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ProjektService} from "../../../../services/project-service/projekt.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {
  TodoItemFormGroup,
  UserForProjectSettings
} from "../../../todo/list/item/todo-item-settings-dialog/todo-item-settings-dialog.component";
import {HttpErrorResponse} from "@angular/common/http";
import {MatSelectChange} from "@angular/material/select";
import {UpdateProject} from "@angular/cdk/schematics";
import {MatSnackBar} from "@angular/material/snack-bar";
import {roleEnum} from "../../../../enums/roleEnum";
import {LogarithmicScale} from "chart.js";

type ProjectSettingForm = {
  projectName: FormControl<string>;
  projectDescription: FormControl<string>;
  projectDueDate: FormControl<Date>;
  usersOnProject: FormControl<string[]>;

}
export type UpdateProjectBasic = {
  description: string,
  id: number,
  dueDate: Date,
  name: string
}

@Component({
  selector: 'app-project-settings',
  templateUrl: './project-settings.component.html',
  styleUrls: ['./project-settings.component.scss']
})
export class ProjectSettingsComponent {
  @ViewChild('warningView', {static: true}) warningView: TemplateRef<any> | null = null;
  @ViewChild('formView', {static: true}) formView: TemplateRef<any> | null = null;
  @ViewChild('dataView', {static: true}) dataView: TemplateRef<any> | null = null;
  primaryView: TemplateRef<any> | null = null;
  show = true;
  projectForm: FormGroup<ProjectSettingForm>
  allUsers: UserForProjectSettings[] = []
  allUsersOnProject: UserForProjectSettings[] = []
  selectedUser: string = '';

  constructor(
    private projectService: ProjektService,
    private dialogRef: MatDialogRef<ProjectSettingsComponent>,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: ProjectSettings) {
    this.projectForm = new FormGroup<ProjectSettingForm>(<ProjectSettingForm>{
      projectName: new FormControl<string>(data.name, {nonNullable: true, validators: [Validators.required]}),
      projectDescription: new FormControl<string>(data.description, {nonNullable: true}),
      projectDueDate: new FormControl<Date>(data.dueDate, {nonNullable: true}),
      usersOnProject: new FormControl<string[]>([])
    })
  }

  ngOnInit() {
    this.primaryView = this.dataView;
    this.projectService.getUsersOnProject(this.data.id).subscribe(res => {
      this.allUsersOnProject = res
      const users = [...res].map(u => {
        return u.username
      })
      this.projectForm.controls.usersOnProject.patchValue(users);
    })
  }

  switchViews(view: 'data' | 'form' | 'warning') {
    if (view === 'data') {
      this.primaryView = this.dataView
    } else if (view === 'form') {
      this.projectService.getAllUser().subscribe(res => {
        this.allUsers = res
      })
      this.primaryView = this.formView
    } else {
      this.primaryView = this.warningView
    }
  }

  saveProjectData() {
    if (this.projectForm.invalid) {
      return;
    }
    let newProject: UpdateProjectBasic = {
      description: this.projectForm.controls.projectDescription.value,
      dueDate: this.projectForm.controls.projectDueDate.value,
      id: this.data.id,
      name: this.projectForm.controls.projectName.value
    }
    this.projectService.saveProjectDetails(newProject).subscribe({
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

  }

  switchViewIfFormChanged(): boolean {
    if (this.data.name !== this.projectForm.controls.projectName.value ||
      this.data.description !== this.projectForm.controls.projectDescription.value ||
      this.data.dueDate !== this.projectForm.controls.projectDueDate.value) {
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
    const employee = this.allUsers.find(obj => obj.username === event.value);
    this.projectService.addUserToProject({
      projectId: this.data.id,
      employeeId: employee?.id ? employee.id : 0,
      employeeRoleEnum: roleEnum.BEOSZTOTT
    }).subscribe(() => {
      const users = this.projectForm.controls.usersOnProject.value;
      users.push(event.value);
      this.projectForm.controls.usersOnProject.patchValue(users);
    })
  }

  remove(removeUser: string) {
    console.log(removeUser)
    const employee = this.allUsersOnProject.find(obj => obj.username === removeUser);
    this.projectService.removeEmployeeFromProject({
      projectId: this.data.id,
      employeeId: employee?.id ? employee.id : 0,
      employeeRoleEnum: roleEnum.BEOSZTOTT
    }).subscribe(res => {
      const users = this.projectForm.controls.usersOnProject.value;
      users.forEach((user, index) => {
        if (user === removeUser) users.splice(index, 1);
      });
      this.projectForm.controls.usersOnProject.patchValue(users);
      this.selectedUser = ""
    })
  }

  deleteProject() {
    this.projectService.deleteProject(this.data.id).subscribe(() => {
      this.dialogRef.close()
    });
  }

}
