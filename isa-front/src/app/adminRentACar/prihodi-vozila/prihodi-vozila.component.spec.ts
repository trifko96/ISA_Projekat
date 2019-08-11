import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrihodiVozilaComponent } from './prihodi-vozila.component';

describe('PrihodiVozilaComponent', () => {
  let component: PrihodiVozilaComponent;
  let fixture: ComponentFixture<PrihodiVozilaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrihodiVozilaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrihodiVozilaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
