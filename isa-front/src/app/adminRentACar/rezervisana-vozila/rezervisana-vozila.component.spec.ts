import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervisanaVozilaComponent } from './rezervisana-vozila.component';

describe('RezervisanaVozilaComponent', () => {
  let component: RezervisanaVozilaComponent;
  let fixture: ComponentFixture<RezervisanaVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezervisanaVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezervisanaVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
