import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LetoviComponent } from './letovi.component';

describe('LetoviComponent', () => {
  let component: LetoviComponent;
  let fixture: ComponentFixture<LetoviComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LetoviComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LetoviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
