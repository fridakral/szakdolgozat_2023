import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {BasicProjectDTO, CreateProjectDTO} from 'src/app/DTOs/projectDTOs';
import {ProjektService} from 'src/app/services/project-service/projekt.service';

type NewProjectFormGroup = {
  name: FormControl<string>;
}

@Component({
  selector: 'app-todo-nav',
  templateUrl: './todo-nav.component.html',
  styleUrls: ['./todo-nav.component.scss']
})
export class TodoNavComponent implements OnInit {

  projects: BasicProjectDTO[] | undefined;
  currentUser!: number;

  newProjectForm: FormGroup<NewProjectFormGroup>;

  constructor(
    private projectService: ProjektService,
  ) {
    this.newProjectForm = new FormGroup<NewProjectFormGroup>({
      name: new FormControl<string>('', {nonNullable: true, validators: [Validators.required]})
    })
  }

  ngOnInit(): void {
    this.getProjects()
  }

  createNewProject() {
    this.newProjectForm.controls.name.markAsTouched();
    if (!this.newProjectForm.invalid) {
      let newProject: CreateProjectDTO = {
        name: this.newProjectForm.controls.name.value,
        dueDate: new Date(new Date().setFullYear(new Date().getFullYear() + 1))
      }
      this.projectService.createProject(newProject).subscribe(() => {
        this.newProjectForm.controls.name.patchValue('');
        this.newProjectForm.controls.name.markAsUntouched();
      });
    }
  }

  getProjects() {
    this.currentUser = JSON.parse(localStorage.getItem('userID')!);

    this.projectService.getUserProjects(this.currentUser).subscribe(res => {
      this.projects = res;
    })

  }


}
