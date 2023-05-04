import { TestBed } from '@angular/core/testing';

import { ProjektService } from './projekt.service';

describe('ProjektService', () => {
  let service: ProjektService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjektService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
