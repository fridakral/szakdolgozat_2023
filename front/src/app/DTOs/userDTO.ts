import {roleEnum} from '../enums/roleEnum';


export interface UserLoginDTO {
  id?: number;
  verified?: Boolean;
  post?: roleEnum;
  username: string;
  password: string;
  tenant: string;
}

export interface UserRegisterDTO {
  username: string;
  password: string;
  lastName: string;
  firstName: string;
  email: string;
  tenant: string;
}

export interface DetailedUserDTO {
  id: number;
  username: string;
  password?: string;
  firstName: string;
  lastName: string;
  placeOfBirth: string;
  dateOfBirth: Date;
  address: string;
  identityCardNumber: string;
  bankAccountNumber: string;
  postDescription: string;
  post: string;
  email: string;
}


export interface EmployeeTable {
  Adat: string;
  Érték: string;
}

export interface EmployeeDTO {
  address: string | null;
  bankAccountNumber: string | null;
  dateOfBirth: Date | null;
  firstName: string;
  lastName: string;
  identityCardNumber: string | null;
  placeOfBirth: string | null;
  userId: number;
}

export interface UserDTO {
  id: number;
  email: string;
  post: string | null;
  postDescription: string | null;
  username: string;
}
