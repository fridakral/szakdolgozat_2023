import {
  AddUserToProject,
  BasicProjectDTO,
  CreateProjectDTO,
  ProjectDTO,
  ProjectSettings,
  UserForProjectSettings
} from './../../DTOs/projectDTOs';
import {environment} from 'src/environments/environment';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {UpdateProjectBasic} from "../../components/todo-nav/project/project-settings/project-settings.component";


@Injectable({
  providedIn: 'root'
})
export class ProjektService {

  currentProjectSubject = new BehaviorSubject<number>(0);


  constructor(private http: HttpClient) {

  }

  setCurrentProjectSubject(id: number) {
    this.currentProjectSubject.next(id);
  }

  getUserProjects(id: number) {
    return this.http.get<BasicProjectDTO[]>(
      `${environment.apiUrl}/project/findAll/${id}`
    )
  }

  createProject(createProjectDTO: CreateProjectDTO) {
    return this.http.post(
      `${environment.apiUrl}/project/create/${localStorage.getItem("userID")}`,
      createProjectDTO
    )
  }

  getDetailedProject(id: number) {
    return this.http.get<ProjectDTO>(
      `${environment.apiUrl}/project/getProject/${id}`
    )
  }

  deleteProject(id: number) {
    return this.http.get(
      `${environment.apiUrl}/project/delete/${id}`
    )

  }

  getSettingsData(projectId: number): Observable<ProjectSettings> {
    return this.http.get<ProjectSettings>(`${environment.apiUrl}/project/settings/${projectId}`)
  }


  getAllUser() {
    return this.http.get<UserForProjectSettings[]>(`${environment.apiUrl}/employee/getAll`)
  }

  getUsersOnProject(projectId: number) {
    return this.http.get<UserForProjectSettings[]>(`${environment.apiUrl}/role/usersOnProject/${projectId}`)
  }


  saveProjectDetails(newProject: UpdateProjectBasic) {
    return this.http.post(
      `${environment.apiUrl}/project/update`,
      newProject
    )
  }

  addUserToProject(user: AddUserToProject) {
    return this.http.post(
      `${environment.apiUrl}/project/addEmployeeToProject`,
      user
    )
  }

  removeEmployeeFromProject(user: AddUserToProject) {
    return this.http.post(
      `${environment.apiUrl}/project/removeEmployee`,
      user
    )
  }
}
