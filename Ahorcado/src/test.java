
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class test {// Alternativa
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < sc.nextInt(); i++)
			guardarPuntuacion(sc.nextInt(), sc.nextInt());
	}

	private static void guardarPuntuacion(Integer puntuacion, Integer numeroVictorias) {// se usa la classe envolvente
																						// porque requiere un // objeto
																						// como parametro
		Optional<Integer> op_p = Optional.ofNullable(puntuacion);
		Optional<Integer> op_nv = Optional.ofNullable(numeroVictorias);
		// uso el metodo "ofNullable"de un objeto del tipo "Opcional" que me permitira
		// darle un valor null al segundo aprametro para que no salte error si falta
		// este
		int punt = op_p.isPresent() ? op_p.get() : 0;
		// si se introdice puntuacion entonces; punt= puntuacion sino 0
		int nVic = op_nv.isPresent() ? op_nv.get() : 0;
		String nombre = "pepi";
		try {
			DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream("Puntuacion.bin", true));
			escribeFichero.writeUTF(nombre + " " + punt + " " + nVic + " ;");// "jugador punt nVic;"
			escribeFichero.close();
		} catch (IOException e) {
			System.out.println("Error E/S");

		}

	}
}