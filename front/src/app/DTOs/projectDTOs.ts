import {roleEnum} from '../enums/roleEnum';

export interface BasicProjectDTO {

  'id': number,
  'name': string,
  'lists': BasicListDTO[],
  'isManager': boolean

}

export interface ProjectDTO {
  'projectId': number,
  'projectName': string,
  'projectDescription': string,
  'projectCreatedOn': Date,
  'projectDueDate': Date,
  'lists': ListDTO[]
}

export interface BasicListDTO {
  'id': number,
  'name': string,
  'taskCount': number,
  'serial': number
}

export interface ListDTO {
  'listId': number,
  'listName': string,
  'serial': number,
  'tasks': TaskDTO[]
}


export interface TaskDTO {
  'serial': number,
  'taskName': string,
  'taskId': number,
  'taskDueDate': Date,
  'taskCreatedOn': Date,
  'taskDescription': string,
  'usersOnTask': string[]
}

export interface UpdateTaskBasic {
  'name': string,
  'id': number,
  'dueDate': Date,
  'description': string

}

export interface CreateProjectDTO {
  'id'?: number,
  'name': string,
  'description'?: string,
  'createdOn'?: Date,
  'dueDate': Date
}

export interface CreateTaskDTO {
  'listId': number,
  'name': string,
  'dueDate': Date,
  'createdOn': Date,
  'description': string
}

export interface AddUserToProject {
  'employeeId': number,
  'projectId': number,
  'employeeRoleEnum': roleEnum
}

export interface ProjectSettings {

  'id': number,
  'description': string,
  'dueDate': Date,
  'createdOn': Date,
  'name': string,
  'users': UserForProjectSettings[]

}

export interface UserForProjectSettings {
  'id': number,
  'username': string,
  'firstName': string,
  'lastName': string,
  'isManager': boolean
}

