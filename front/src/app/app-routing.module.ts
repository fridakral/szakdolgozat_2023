import { TodoNavComponent } from './components/todo-nav/todo-nav.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainPageComponent} from "./components/main-page/main-page.component";
import {TodoComponent} from "./components/todo/todo.component";
import { UserdataComponent } from './components/userdata/userdata.component';

const routes: Routes = [
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: MainPageComponent
  },
  {
    path:'todo',
    component: TodoComponent
  },
  {
    path:'userData',
    component:UserdataComponent
  },
  {
    path:'todoNav',
    component:TodoNavComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
