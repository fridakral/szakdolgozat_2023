import {AuthInterceptorProvider} from './interceptors/authenticationInterceptor';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './materials/material.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {TableComponent} from './components/table/table.component';
import {DatePipe} from '@angular/common';
import {MainPageComponent} from './components/main-page/main-page.component';
import {NotificationComponent} from './components/notification/notification.component';
import {MatExpansionModule} from '@angular/material/expansion';
import {TodoComponent} from './components/todo/todo.component';
import {DragDropModule} from "@angular/cdk/drag-drop";
import {UserdataComponent} from './components/userdata/userdata.component';
import {TodoNavComponent} from './components/todo-nav/todo-nav.component';
import {ProjectComponent} from './components/todo-nav/project/project.component';
import {ListComponent} from './components/todo/list/list.component';
import {ItemComponent} from './components/todo/list/item/item.component';
import {ProjectSettingsComponent} from './components/todo-nav/project/project-settings/project-settings.component';
import {MatTabsModule} from "@angular/material/tabs";
import {NgChartsModule} from 'ng2-charts';
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatTooltipModule} from "@angular/material/tooltip";
import {
  TodoItemSettingsDialogComponent
} from './components/todo/list/item/todo-item-settings-dialog/todo-item-settings-dialog.component';
import {MatSliderModule} from "@angular/material/slider";
import {MatDialogModule} from "@angular/material/dialog";
import {MatChipsModule} from "@angular/material/chips";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {RightNavComponent} from './components/right-nav/right-nav.component';
import {MatListModule} from "@angular/material/list";
import { UserDataViewComponent } from './components/userdata/user-data-view/user-data-view.component';
import { SavedDataViewComponent } from './components/userdata/saved-data-view/saved-data-view.component';
import { SecurityViewComponent } from './components/userdata/security-view/security-view.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    TableComponent,
    MainPageComponent,
    NotificationComponent,
    TodoComponent,
    UserdataComponent,
    TodoNavComponent,
    ProjectComponent,
    ListComponent,
    ItemComponent,
    ProjectSettingsComponent,
    TodoItemSettingsDialogComponent,
    RightNavComponent,
    UserDataViewComponent,
    SavedDataViewComponent,
    SecurityViewComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatSliderModule,
    MatDialogModule,
    MatExpansionModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DragDropModule,
    MatTabsModule,
    NgChartsModule,
    MatProgressBarModule,
    MatTooltipModule,
    MatChipsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatAutocompleteModule,
    MatListModule
  ],
  providers: [AuthInterceptorProvider, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
