import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvioNeregistrovaniComponent } from './avio-neregistrovani.component';

describe('AvioNeregistrovaniComponent', () => {
  let component: AvioNeregistrovaniComponent;
  let fixture: ComponentFixture<AvioNeregistrovaniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvioNeregistrovaniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvioNeregistrovaniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
