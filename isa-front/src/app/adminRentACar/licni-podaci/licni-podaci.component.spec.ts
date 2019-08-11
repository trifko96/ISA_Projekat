import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LicniPodaciComponent } from './licni-podaci.component';

describe('LicniPodaciComponent', () => {
  let component: LicniPodaciComponent;
  let fixture: ComponentFixture<LicniPodaciComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LicniPodaciComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LicniPodaciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
