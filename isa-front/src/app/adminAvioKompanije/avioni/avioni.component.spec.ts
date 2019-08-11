import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvioniComponent } from './avioni.component';

describe('AvioniComponent', () => {
  let component: AvioniComponent;
  let fixture: ComponentFixture<AvioniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvioniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvioniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
