import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GlavnaRentACarComponent } from './glavna-rent-acar.component';

describe('GlavnaRentACarComponent', () => {
  let component: GlavnaRentACarComponent;
  let fixture: ComponentFixture<GlavnaRentACarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GlavnaRentACarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlavnaRentACarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
