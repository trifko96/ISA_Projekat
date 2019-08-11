import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HoteliComponent } from './hoteli.component';

describe('HoteliComponent', () => {
  let component: HoteliComponent;
  let fixture: ComponentFixture<HoteliComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HoteliComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HoteliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
