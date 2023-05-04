import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecurityViewComponent } from './security-view.component';

describe('SecurityViewComponent', () => {
  let component: SecurityViewComponent;
  let fixture: ComponentFixture<SecurityViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SecurityViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecurityViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
