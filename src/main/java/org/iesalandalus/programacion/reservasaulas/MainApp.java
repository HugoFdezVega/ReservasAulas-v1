package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class MainApp {
	//Método main, que crea el Modelo, la Vista, corre el constructor del Controlador pasando como parámetros el Modelo y Vista y luego corre
	//el método comenzar del controlador
		public static void main(String[] args) {
		Modelo modelo=new Modelo();
		Vista vista=new Vista();
		Controlador controlador=new Controlador(modelo,vista);
			
		controlador.comenzar();	
			
		}
	}


