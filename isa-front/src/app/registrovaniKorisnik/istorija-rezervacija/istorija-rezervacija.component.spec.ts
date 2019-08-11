import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IstorijaRezervacijaComponent } from './istorija-rezervacija.component';

describe('IstorijaRezervacijaComponent', () => {
  let component: IstorijaRezervacijaComponent;
  let fixture: ComponentFixture<IstorijaRezervacijaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IstorijaRezervacijaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IstorijaRezervacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
