import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AerodromiComponent } from './aerodromi.component';

describe('AerodromiComponent', () => {
  let component: AerodromiComponent;
  let fixture: ComponentFixture<AerodromiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AerodromiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AerodromiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
