import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoStranicaComponent } from './info-stranica.component';

describe('InfoStranicaComponent', () => {
  let component: InfoStranicaComponent;
  let fixture: ComponentFixture<InfoStranicaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoStranicaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoStranicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
