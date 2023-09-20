//Mehdi Tahrat

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Ranking {

	public static void ranking() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> historial = new ArrayList<String>();
		boolean a = true;// true: ordenar por puntuacion, false: ordenar por nVictorias
		boolean salir = false;

		guardarHistorial(historial);// guarda el contenido del archivo en esta lista
		while (!salir) {
			ordenarHistorial(historial, a);
			imprimirHistorial(historial);
			System.out.println("\n-- MENU Ranking --"); //MENÚ CON OPCIONES, FUNCIONA TECLEANDO EL NÚMERO DE LA OPCION.
			System.out.println("1. Ordenar por puntuacion");
			System.out.println("2. Ordenar por victorias");
			System.out.println("3. Volver al menu principal");
			int opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				a = true;
				break;
			case 2:
				a = false;
				break;
			case 3:
				salir = true;
				break;
			default:
				System.out.println("\nOpcion no valida!");
				break;
			}
		}

	}
	///////////////////// funciones///////////////////////////////

	/////////////////////////////////////////////////////
	// - funcion guardarHistorial
	// //guarda el contenido del archivo en esta lista
	/////////////////////////////////////////////////////
	public static void guardarHistorial(ArrayList<String> historial) {
		try {
			DataInputStream leeFichero = new DataInputStream(new FileInputStream("Puntuacion.bin"));
			do {
				historial.add(leeFichero.readUTF());// añadir nombre a la lista
			} while (true);
		} catch (IOException e) {
		}

	}

	/////////////////////////////////////////////////////
	// - funcion ordenarHistorial
	//// imprime el Historial
	/////////////////////////////////////////////////////
	public static void imprimirHistorial(ArrayList<String> historial) {
		String nombre;
		int puntuacion;
		int nVic;// numero de victorias
		System.out.println("╔═══════════╦═════════════╦════════════╗");
		System.out.println("║ Nombre    ║ Puntuacion  ║ NVictorias ║");
		System.out.println("╠═══════════╬═════════════╬════════════╣");

		for (String linea : historial) {
			nombre = linea.split(" ")[0];
			puntuacion = Integer.parseInt(linea.split(" ")[1]);
			nVic = Integer.parseInt(linea.split(" ")[2]);
			if (puntuacion != 0 && nVic != 0)
				System.out.println("║ " + nombre + "     ║     " + puntuacion + "     ║     " + nVic + "     ║");

		}
		System.out.println("╚═══════════╩═════════════╩════════════╝");

	}

	///////////////////////////////////////////////////////////////////
	// - funcion ordenarHistorial
	// //ordena de mayor a menor por numero de victorias o puntuacion
	//////////////////////////////////////////////////////////////////
	public static void ordenarHistorial(ArrayList<String> historial, boolean a) {
		String lineaAux;
		int n;// es el numero obtenido
		int nAux = 0;// es el anterior numero obtenido
		int id;// nuemro de registro
		int x = a ? 1 : 2;// 1 ordenar por puntuacion, 2 " " victorias

		for (int i = 0; i < historial.size(); i++) {
			id = 0;
			for (String linea : historial) {// por cada registro que hay, iteracion
				n = Integer.parseInt(linea.split(" ")[x]);
				if (n > nAux && id != 0) {// si el actual es mayor, sustitur por el anterior por este
					lineaAux = historial.get(id - 1);// guardar el menor
					historial.set(id - 1, historial.get(id));// añadir el mayor
					historial.set(id, lineaAux);// añadir el menor
				}
				nAux = Integer.parseInt(linea.split(" ")[x]);
				id++;
			}

		}

	}
}
