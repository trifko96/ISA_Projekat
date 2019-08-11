import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelNeregistrovaniComponent } from './hotel-neregistrovani.component';

describe('HotelNeregistrovaniComponent', () => {
  let component: HotelNeregistrovaniComponent;
  let fixture: ComponentFixture<HotelNeregistrovaniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelNeregistrovaniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelNeregistrovaniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
