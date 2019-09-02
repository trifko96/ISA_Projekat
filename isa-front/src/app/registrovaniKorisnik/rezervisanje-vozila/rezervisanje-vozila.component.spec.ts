import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervisanjeVozilaComponent } from './rezervisanje-vozila.component';

describe('RezervisanjeVozilaComponent', () => {
  let component: RezervisanjeVozilaComponent;
  let fixture: ComponentFixture<RezervisanjeVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezervisanjeVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezervisanjeVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
