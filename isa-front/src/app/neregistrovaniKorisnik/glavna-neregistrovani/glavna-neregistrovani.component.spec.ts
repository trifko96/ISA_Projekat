import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GlavnaNeregistrovaniComponent } from './glavna-neregistrovani.component';

describe('GlavnaNeregistrovaniComponent', () => {
  let component: GlavnaNeregistrovaniComponent;
  let fixture: ComponentFixture<GlavnaNeregistrovaniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GlavnaNeregistrovaniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlavnaNeregistrovaniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
