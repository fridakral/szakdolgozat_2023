import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SavedDataViewComponent } from './saved-data-view.component';

describe('SavedDataViewComponent', () => {
  let component: SavedDataViewComponent;
  let fixture: ComponentFixture<SavedDataViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SavedDataViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SavedDataViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
