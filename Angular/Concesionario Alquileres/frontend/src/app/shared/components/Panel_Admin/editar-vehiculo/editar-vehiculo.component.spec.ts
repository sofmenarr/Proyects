import { ComponentFixture, TestBed } from '@angular/core/testing';

import {EditarVehiculoComponent } from './editar-vehiculo.component';

describe('EditarTipoVheiculoAdminComponent', () => {
  let component: EditarVehiculoComponent;
  let fixture: ComponentFixture<EditarVehiculoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarVehiculoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarVehiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
