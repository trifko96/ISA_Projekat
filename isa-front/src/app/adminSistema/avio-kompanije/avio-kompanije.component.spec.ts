import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvioKompanijeComponent } from './avio-kompanije.component';

describe('AvioKompanijeComponent', () => {
  let component: AvioKompanijeComponent;
  let fixture: ComponentFixture<AvioKompanijeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvioKompanijeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvioKompanijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
