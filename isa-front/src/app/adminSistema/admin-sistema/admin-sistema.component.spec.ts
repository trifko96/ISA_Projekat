import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSistemaComponent } from './admin-sistema.component';

describe('AdminSistemaComponent', () => {
  let component: AdminSistemaComponent;
  let fixture: ComponentFixture<AdminSistemaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSistemaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSistemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
