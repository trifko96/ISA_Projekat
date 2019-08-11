import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GlavnaAdminSistemaComponent } from './glavna-admin-sistema.component';

describe('GlavnaAdminSistemaComponent', () => {
  let component: GlavnaAdminSistemaComponent;
  let fixture: ComponentFixture<GlavnaAdminSistemaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GlavnaAdminSistemaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlavnaAdminSistemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
