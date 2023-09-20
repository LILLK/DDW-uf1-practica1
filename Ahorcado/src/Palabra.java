//Mehdi Tahrat

import java.io.*;
import java.util.ArrayList;

public class Palabra {

	int nivelDif;// nivel de dificultad 1 facil , " ",3 dificl
	public String nombre;// es el "contenido" del objeto palabra
	public int longitud;
	public ArrayList<Character> letras = new ArrayList<Character>();
	// rellena la lista con las letras de las palabras

	///////////////////// Contructores ///////////////////////////////

	public Palabra() {

	}

	public Palabra(int nivelDif) {
		this.nivelDif = nivelDif;
		// establece con un setter el nombre
		generarNombrePalabra(nivelDif);
		this.longitud = this.nombre.toCharArray().length;
		procesarPalabra();// rellena letras

	}

	///////////////////// metodos///////////////////////////////

	/////////////////////////////////////////////////////
	// - Metodo imprimirPxL
	// imprime la palabra letra a letra
	/////////////////////////////////////////////////////

	public void imprimirPxL(ArrayList<Character> usadas ) {
		
		for (char c : this.letras) {
			if (usadas.contains(c)) {
				System.out.print(c);
			} else {
				System.out.print("*");
			}
		}
		System.out.println("");
	}
	/////////////////////////////////////////////////////
	// - Metodo procesarPalabra
	// separa sus letras en un array
	/////////////////////////////////////////////////////

	public void procesarPalabra() {
		// limpiar la lista
		this.letras.clear();
		// pasar las letras de la palabra a lista
		for (char c : this.nombre.toCharArray()) {
			this.letras.add(c);
		}
	}
	/////////////////////////////////////////////////////
	// - Metodo existeLetra
	// Devuelve verdadero si la letra existe en la palabra
	/////////////////////////////////////////////////////
	public boolean existeLetra(char a) {
		if (this.letras.contains(a))
			return true;
		else
			return false;
	}
	/////////////////////////////////////////////////////
	// - Metodo palabraRandom
	// devuelve una palabra aleatoria
	/////////////////////////////////////////////////////
	public String palabraRandom() {
		String linea = "";// aqui se almacenaran las lineas de cada archivo
		int random = (int) (Math.random() * 32194);
		try {
			BufferedReader br = new BufferedReader(new FileReader("Palabras.txt"));

			// System.out.println(linea);
			// Mientras que no se llegue al final del fichero y esta en la linea random
			for (int i = 0; (linea = br.readLine()) != null && i != random; i++) {
				System.out.print("");
			}
			// registrara en "linea" la palabra elejida
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		return linea.toLowerCase();
	}
	/////////////////////////////////////////////////////
	// - Metodo generarPalabra
	// genera una palabra adecuada al nivel
	/////////////////////////////////////////////////////

	public void generarNombrePalabra(int nivelDif) {
		System.out.println("Generando palabra...");
		switch (nivelDif) {
		case 1:// de 3 a 5 letras
			do {
				this.setNombre(this.palabraRandom());
				this.setLongitud();
			} while (this.longitud < 3 || this.longitud > 5);

			break;
		case 2:// de 6 a 9 letras
			do {
				this.setNombre(this.palabraRandom());
				this.setLongitud();

			} while (this.longitud < 6 || this.longitud > 9);

			break;
		case 3:// mas de 9 letras
			do {
				this.setNombre(this.palabraRandom());
				this.setLongitud();

			} while (this.longitud < 9);

			break;
		default:
			System.out.println("resta");
			break;
		}
		System.out.println("");
		System.out.print("Palabara: ");
		for (int i = 0; i < this.longitud; i++) {
			System.out.print("*");
		}
		System.out.println("");
	}

	/////////////////////// set/get////////////////////////////////

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public void setLongitud() {
		this.longitud = this.nombre.toCharArray().length;
	}

	

}
