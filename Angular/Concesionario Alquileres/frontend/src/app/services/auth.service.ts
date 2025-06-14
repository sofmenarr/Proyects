import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Usuario } from '../models/login.model';
import { Rol } from '../models/enums';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private usuarioActual = new BehaviorSubject<Usuario | null>(null);
  private estaAutenticado = new BehaviorSubject<boolean>(false);

  constructor() {
    // Recuperar el estado de inicio de sesión del localStorage al iniciar
    const usuarioGuardado = localStorage.getItem('usuario');
    
    // Verifica explícitamente si existe y no es "undefined" como string
    if (usuarioGuardado && usuarioGuardado !== 'undefined') {
      try {
        const usuario = JSON.parse(usuarioGuardado);
        // Asegurarnos de que el rol se convierte al enum correctamente
        if (usuario?.rol) {
          usuario.rol = Rol[usuario.rol as keyof typeof Rol];
        }
        this.usuarioActual.next(usuario);
        this.estaAutenticado.next(true);
      } catch (error) {
        console.error('Error al parsear usuario de localStorage:', error);
        this.limpiarDatosInvalidos(); // Limpia datos corruptos
      }
    }
  }

  private limpiarDatosInvalidos() {
    localStorage.removeItem('usuario');
    localStorage.removeItem('token');
    this.usuarioActual.next(null);
    this.estaAutenticado.next(false);
  }

  iniciarSesion(usuario: Usuario, token: string) {
    console.log('Usuario que inicia sesión:', usuario);
    this.usuarioActual.next(usuario);
    this.estaAutenticado.next(true);
    localStorage.setItem('usuario', JSON.stringify(usuario));
    localStorage.setItem('token', token);
  }

  obtenerUsuarioActual(): Observable<Usuario | null> {
    return this.usuarioActual.asObservable();
  }

  cerrarSesion() {
    this.usuarioActual.next(null);
    this.estaAutenticado.next(false);
    localStorage.removeItem('usuario');
    localStorage.removeItem('token');
  }

  obtenerEstadoAutenticacion(): Observable<boolean> {
    return this.estaAutenticado.asObservable();
  }
}
