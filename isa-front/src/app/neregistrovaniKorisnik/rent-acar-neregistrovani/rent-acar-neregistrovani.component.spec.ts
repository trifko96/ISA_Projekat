import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentACarNeregistrovaniComponent } from './rent-acar-neregistrovani.component';

describe('RentACarNeregistrovaniComponent', () => {
  let component: RentACarNeregistrovaniComponent;
  let fixture: ComponentFixture<RentACarNeregistrovaniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentACarNeregistrovaniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentACarNeregistrovaniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
