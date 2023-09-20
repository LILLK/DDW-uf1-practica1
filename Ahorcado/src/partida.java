//Mehdi Tahrat

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class partida {

	public static void jugar(Pj pj) {
		ArrayList<String> nombres = new ArrayList<String>();// nombre de todos los jugadores
		ArrayList<Character> usadas = new ArrayList<Character>();// letras usadas
		int nivelDif, fallo = 0, acierto = 0, iteracion = 0;

			
		
		
		recogerNombres(pj, nombres);// ntroduce en la lista todos los nombres registrados
		incioSesion(pj, nombres);// recive y detecta si el jugador es su primea vez
		pj.puntuacionActual = 0;
		while (fallo < 5) {// mientras no se falle 5 veces en una ronda
			nivelDif = nivelDif(iteracion);// nivel de dificultad 3-5 letras, 6-9 letras, +9 letras
			Palabra palabra = new Palabra(nivelDif);// se crea una palabra aleatoria dependiendo del nivel de dif
			//System.out.println("palabra: " + palabra.nombre);
			// descomentar la linea superior para mostrar la palabra generada por ronda
			usadas.clear();
			acierto = 0;
			fallo = 0;

			do {// se mantiene la ronda hasta que se pierda o se adivine la palabra y se sigue
				System.out.print("Letras usadas:");
				imprimriLetras(usadas);
				System.out.println("");
				procesarLetra(usadas);// fuerza la entrada de letras y las añade a la lista de usadas
				System.out.println("");

				if (palabra.existeLetra(usadas.get(usadas.size() - 1))) {// se acierta la letra
					System.out.println("Has acertado !");
					palabra.imprimirPxL(usadas);
					acierto += letrasDescubiertas(palabra, usadas.get(usadas.size() - 1));
					// se le suma a "acierto" el numero de letras que coinciden de la palabra
					imprimirMonigote(fallo);
					if (acierto == palabra.longitud) {// si se completa la palabra
						palabraCompletada(palabra, pj, fallo, iteracion);// resumen de puntuacion

					}
				} else {// se falla la letra
					System.out.println("Has fallado !");
					palabra.imprimirPxL(usadas);
					fallo++;
					imprimirMonigote(fallo);
				}
			} while (acierto < palabra.longitud && fallo < 5);

			iteracion++;
		}
		iteracion--;//se resta en uno para no "pasar" a la siguiente ronda
		System.out.println("");
		System.out.println("Numero de Victorias: " + iteracion);
		System.out.println("Puntuacion final: " + pj.puntuacionActual);
		pj.guardarPuntuacion(pj.puntuacionActual, iteracion);
		System.out.println("Fin de la partida!");
	}

	///////////////////// funciones ///////////////////////////////

	/////////////////////////////////////////////////////
	// - Funcion imprimriLetrasUsadas
	// imprime el array de letras
	/////////////////////////////////////////////////////

	public static void imprimriLetras(ArrayList<Character> letras) {
		for (char c : letras) {
			System.out.print(" " + c);
		}

	}

	/////////////////////////////////////////////////////
	// - Funcion letrasDescubiertas
	// devuelve el numero de letras en la palabra que conciden con la elejuda
	/////////////////////////////////////////////////////
	public static int letrasDescubiertas(Palabra palabra, char a) {
		int aux = 0;// valor a devolver
		for (char c : palabra.letras) {
			if (a == c)
				aux++;
		}
		return aux;

	}
	/////////////////////////////////////////////////////
	// - Funcion procesarLetra
	// fuerza la entrada de letras y las añade a la lista de usadas
	/////////////////////////////////////////////////////

	public static void procesarLetra(ArrayList<Character> usadas) {
		Scanner sc = new Scanner(System.in);
		char letra;
		do {// mientras no sea una letra
			System.out.print("Letra a elejir: ");
			letra = sc.next().toLowerCase().charAt(0);
			if ((((int) letra) < 90 && ((int) letra) > 97)// si no son letras incluyendo si no se encuentra la ñ "241"
					|| (((int) letra) < 65 || ((int) letra) > 122) && ((int) letra) != 241)
				System.out.println("Inserte una letra");
			if (usadas.contains(letra))
				System.out.println("Ya has usado esta letra");
		} while ((((int) letra) < 90 && ((int) letra) > 97)
				|| (((int) letra) < 65 || ((int) letra) > 122 && ((int) letra) != 241) || usadas.contains(letra));
		usadas.add(letra);
		// System.out.println("\nLetra: " + letra);
	}
	/////////////////////////////////////////////////////
	// - Funcion recogerNombres
	// introduce en la lista todos los nombres registrados
	/////////////////////////////////////////////////////

	public static void recogerNombres(Pj pj, ArrayList<String> nombres) {

		// refrescar la lista
		nombres.clear();
		try {
			DataInputStream leeFichero = new DataInputStream(new FileInputStream("Puntuacion.bin"));
			do {
				if (!nombres.contains(leeFichero.readUTF().split(" ")[0]))// si no existe
					nombres.add(leeFichero.readUTF().split(" ")[0]);// añadir nombre a la lista
			} while (true);
		} catch (IOException e) {
		}

	}

	/////////////////////////////////////////////////////
	// - Funcion incioSesion
	// Recive el jugador
	/////////////////////////////////////////////////////
	public static void incioSesion(Pj pj, ArrayList<String> nombres) {
		Scanner sc = new Scanner(System.in);
		String input;
		if (!pj.logged) {
			do {
				System.out.println("Inserte su nombre");
				pj.setNombre(input = (sc.nextLine()).trim());
				pj.setLogged(true);
				if (input.contains(";"))// si el nombre contiene ";" no se considera valido
					System.out.println("el nombre no puede contener el caracter ;");
			} while (input.contains(";"));
		}
		// establecemos el nombre y borramos los espacios adyacentes
		if (!nombres.contains(pj.nombre)) {
			System.out.println("Bienvenido a tu primera partida " + pj.nombre);
			// esto nos permite registrar el usuario por primera vez
			pj.guardarNewPJ(pj, nombres);
		}

	}

	/////////////////////////////////////////////////////
	// - Funcion palabraCompletada
	// genera ,suma al total y imprime la puntuacion del jugador
	/////////////////////////////////////////////////////
	public static void palabraCompletada(Palabra palabra, Pj pj, int fallo, int iteracion) {
		System.out.println("Palabra compeltada !");

		System.out.print("Puntuacion(" + pj.getPuntuacionActual() + ") = " + pj.getPuntuacionActual()
				+ " + Longitud de la palabra(" + palabra.longitud + ") * Ronda(" + (iteracion + 1) + ") / Fallos("
				+ fallo + ")");

		if (fallo == 0) {
			pj.setPuntuacionActual((palabra.longitud * (iteracion + 1)) + pj.getPuntuacionActual() + 50);// guardar punt
																											// en objeto
			System.out.print("+ (Bonus por 0 fallos(50))");
		} else {
			pj.setPuntuacionActual((palabra.longitud * (iteracion + 1)) / fallo + pj.getPuntuacionActual());// guardar
																											// punt en
																											// objeto
		}
		System.out.println(" = " + pj.getPuntuacionActual());

	}

	/////////////////////////////////////////////////////
	// - Funcion nivelDif
	// devuelve el nivel de la dificultad
	/////////////////////////////////////////////////////
	public static int nivelDif(int iteracion) {
		Scanner sc = new Scanner(System.in);
		int nivelDif;
		do {// selecion de dificultad
			System.out.println("-- Ronda " + (iteracion + 1) + " --\n");
			System.out.println("Elije la dificultad: ");
			System.out.println("	1. 3-5 letras");
			System.out.println("	2. 6-9 letras");
			System.out.println("	3. +9 letras");
			nivelDif = sc.nextInt();
			if (nivelDif < 1 || nivelDif > 3)
				System.out.println("Valor erroneo");
		} while (nivelDif < 1 || nivelDif > 3);
		return nivelDif;
	}

	/////////////////////////////////////////////////////
	// - Funcion imprimirMonigote
	// imprime el monigote
	/////////////////////////////////////////////////////
	public static void imprimirMonigote(int fallo) {
		System.out.println("Numero de fallos: " + fallo);
		for (int i = 0; i < fallo; i++) {
			switch (i) {
			case 0:
				System.out.println("  _______");
				System.out.println(" |/      |");
				break;
			case 1:
				System.out.println(" |      (_)");

				break;
			case 2:
				System.out.println(" |      \\|/");

				break;
			case 3:
				System.out.println(" |       |");

				break;
			case 4:
				System.out.println(" |      / \\");
				System.out.println(" |");
				System.out.println("_|___");
				break;

			default:
				break;
			}
		}

	}

}
