import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GlavnaRegistrovaniComponent } from './glavna-registrovani.component';

describe('GlavnaRegistrovaniComponent', () => {
  let component: GlavnaRegistrovaniComponent;
  let fixture: ComponentFixture<GlavnaRegistrovaniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GlavnaRegistrovaniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlavnaRegistrovaniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
