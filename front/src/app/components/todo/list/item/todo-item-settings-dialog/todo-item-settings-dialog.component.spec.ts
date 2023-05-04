import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoItemSettingsDialogComponent } from './todo-item-settings-dialog.component';

describe('TodoItemSettingsDialogComponent', () => {
  let component: TodoItemSettingsDialogComponent;
  let fixture: ComponentFixture<TodoItemSettingsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TodoItemSettingsDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TodoItemSettingsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
