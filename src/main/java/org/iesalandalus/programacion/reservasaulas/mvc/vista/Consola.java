package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private final static DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static Opcion[] opciones = Opcion.values();
	static Tramo[] tramos = Tramo.values();

	private Consola() {

	}

//Método que recorre el array de opciones y las va mostrando con el toString de Opcion
	public static void mostrarMenu() {
		mostrarCabecera("Bienvenido al sistema de reservas del IES Al-Ándalus. Entra libremente y deja parte de la felicidad que traes contigo.");
		for (Opcion o : opciones) {
			System.out.println(o);
		}
	}

//Muestra un mensaje de cabecera
	public static void mostrarCabecera(String cabecera) {
		LocalDate presente=LocalDate.now();
		String salida=" Hoy es "+presente.format(FORMATO_DIA).toString();
		System.out.println(cabecera+salida);
	}

//Método que pide que se elija una opción y la devuelve. Se validará después con el método esOrdinalValido del método getOpcionSegunOrdinal de Opcion
	public static int elegirOpcion() {
		System.out.println("Por favor, elija una opción del menú");
		int eleccion = Entrada.entero();
		return eleccion;
	}

//Método que ejecuta leerNombreAula(), que es un método que pide un nombre para un aula. Luego crea un aula con dicho nombre y retorna una copia
	public static Aula leerAula() {
		Aula aula = new Aula(leerNombreAula());
		return new Aula(aula);
	}

//Método que pide el nombre de un aula y lo devuelve
	public static String leerNombreAula() {
		System.out.println("Introduzca el nombre del aula");
		String nombreAula = Entrada.cadena();
		return nombreAula;
	}

//Método que ejecuta leerNombreProfesor(), que es un método que pide un nombre para un profesor. Luego pide el correo y el teléfono y crea un profesor
//con dichos datos, para después retornar una copia
	public static Profesor leerProfesor() {
		String nombreProfesor = leerNombreProfesor();
		System.out.println("Introduzca el correo del profesor");
		String correoProfesor = Entrada.cadena();
		System.out.println("Introduzca el teléfono del profesor");
		String telefonoProfesor = Entrada.cadena();
		Profesor profesor = new Profesor(nombreProfesor, correoProfesor, telefonoProfesor);
		return new Profesor(profesor);
	}

//Método que pide el nombre de un profesor y lo devuelve
	public static String leerNombreProfesor() {
		System.out.println("Introduzca el nombre del profesor");
		String nombreProfesor = Entrada.cadena();
		return nombreProfesor;
	}

//Método que pide que se elija un tramo de entre los posibles tramos, que se muestran mediante un forEach y el .toString del enum Tramo. Luego compara,
//ignorando mayúsculas, si el tramo introducido coincide con alguno de los del array de tramos. Si es así, devuelve el Tramo correspondiente, y si no
//nos seguirá pidiendo que introduzcamos un tramo válido mediante la bandera problema
	public static Tramo leerTramo() {
		Tramo tramoFinal = null;
		boolean problema = false;
		do {
			System.out.println("Elija un tramo horario:");
			for (Tramo t : tramos) {
				System.out.println(t.toString());
			}
			String tramoElegido = Entrada.cadena();
			if (tramoElegido.equalsIgnoreCase(tramos[0].toString())) {
				tramoFinal = Tramo.MANANA;
				problema=false;
			} else if (tramoElegido.equalsIgnoreCase(tramos[1].toString())) {
				tramoFinal = Tramo.TARDE;
				problema=false;
			} else {
				System.out.println("ERROR: El tramo introducido no es válido");
				problema=true;
			}
		} while (problema == true);
		return tramoFinal;
	}

//Método que nos pide introducir un día con un formato dado. Seguirá pidiendo el día mientras no se introduzca con el formato correcto y, una vez esté
//o la fecha sea anterior a la presente, nos devuelve el LocalDate del día.
	public static LocalDate leerDia() {
		LocalDate fechaFinal = null;
		boolean problema = false;
		do {
			try {
				System.out.println("Introduzca una fecha(formato dd/mm/aaaa):");
				String fechaIntroducida = Entrada.cadena();
				fechaFinal = LocalDate.parse(fechaIntroducida, FORMATO_DIA);
				problema = false;

			} catch (DateTimeParseException e) {
				System.out.println("ERROR: Formato incorrecto");
				problema = true;
			}
			if(fechaFinal.isBefore(LocalDate.now())) {
				System.out.println("ERROR: La fecha introducida no puede ser anterior al día presente");
				problema=true;
			}
		} while (problema == true);
		return fechaFinal;
	}

}
