import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervisanjeComponent } from './rezervisanje.component';

describe('RezervisanjeComponent', () => {
  let component: RezervisanjeComponent;
  let fixture: ComponentFixture<RezervisanjeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezervisanjeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezervisanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
