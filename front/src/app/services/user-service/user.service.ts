import {
  DetailedUserDTO,
  EmployeeDTO,
  EmployeeTable,
  UserDTO,
  UserLoginDTO,
  UserRegisterDTO
} from './../../DTOs/userDTO';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {HttpHeaders, HttpParams} from '@angular/common/http';
import {HttpClient, HttpEvent} from '@angular/common/http';
import {Observable} from "rxjs";
import {UserForProjectSettings} from "../../DTOs/projectDTOs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }


  getDetailedEmployee(userId: number) {
    return this.http.get<DetailedUserDTO>(
      `${environment.apiUrl}/employee/getDetailedUser/${userId}`
    )
  }

  getUserByName(username: string) {
    return this.http.get<UserLoginDTO>(
      `${environment.apiUrl}/employee/getEmployee/${username}`
    )
  }

  getAllUser(): Observable<UserForProjectSettings[]> {
    return this.http.get<UserForProjectSettings[]>(
      `${environment.apiUrl}/employee/getAll`
    )
  }

  userRegister(user: UserRegisterDTO, tenant?: UserRegisterDTO) {
    return this.http.post<any>(
      `${environment.apiUrl}/employee/register?tenant=${user.tenant}`,
      user
    );
  }

  verifyUser(id: number) {
    return this.http.get(
      `${environment.apiUrl}/employee/verifyUser/${id}`
    );
  }


  deleteUser(id: number) {
    return this.http.get(
      `${environment.apiUrl}/employee/deleteUser/${id}`
    );
  }


  userLogin(user: UserLoginDTO) {
    let params = new URLSearchParams();
    params.set('username', user.username);
    params.set('password', user.password);
    params.set('tenant', user.tenant);
    let options = {
      headers: new HttpHeaders().set(
        'Content-Type',
        'application/x-www-form-urlencoded'
      ),
      observe: 'response' as 'response',
    };
    return this.http.post(
      `${environment.apiUrl}/employee/login`,
      params,
      options
    );
  }

  getDetailedUser(id: number) {
    return this.http.get<DetailedUserDTO>(
      `${environment.apiUrl}/employee/getDetailedUser/${id}`
    );
  }

  updateUserData(user: UserDTO) {
    return this.http.post<any>(
      `${environment.apiUrl}/employee/updateUser`,
      user
    );
  }

  saveUserDetails(user: EmployeeDTO) {
    return this.http.post<any>(
      `${environment.apiUrl}/employee/updateEmployee`,
      user
    );
  }

  changePassword(p: Object) {
    return this.http.post<any>(
      `${environment.apiUrl}/employee/changePassword`,
      p
    );
  }
}
