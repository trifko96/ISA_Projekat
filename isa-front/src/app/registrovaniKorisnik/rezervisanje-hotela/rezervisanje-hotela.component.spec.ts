import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervisanjeHotelaComponent } from './rezervisanje-hotela.component';

describe('RezervisanjeHotelaComponent', () => {
  let component: RezervisanjeHotelaComponent;
  let fixture: ComponentFixture<RezervisanjeHotelaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezervisanjeHotelaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezervisanjeHotelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
