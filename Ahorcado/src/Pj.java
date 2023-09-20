//Mehdi Tahrat

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Pj {

	public String nombre;
	public int puntuacionActual;
	public boolean logged;

	///////////////////// Contructores ///////////////////////////////
	public Pj() {
		this.logged = false;
	}

	public Pj(String nombre, int puntuacionActual, boolean logged) {
		this.nombre = nombre;
		this.puntuacionActual = puntuacionActual;
		this.logged = logged;

	}
	///////////////////// metodos///////////////////////////////
	
	/////////////////////////////////////////////////////
	// - Metodo guardarNewPJ
	// Guarda un nuevo jugador
	/////////////////////////////////////////////////////

	public void guardarNewPJ(Pj pj, ArrayList<String> nombres) {
		guardarPuntuacion(null,null);//gaurdamos sin puntuacion y sin victorias
		nombres.add(pj.nombre);
	}
	/////////////////////////////////////////////////////
	// - Metodo guardarPuntuacion
	// guarda la puntuacion del jugador
	/////////////////////////////////////////////////////

	public void guardarPuntuacion(Integer puntuacion, Integer numeroVictorias) {// se usa la classe envolvente porque requiere un																		// objeto como parametro
		Optional<Integer> op_p = Optional.ofNullable(puntuacion);
		Optional<Integer> op_nv = Optional.ofNullable(numeroVictorias);
		// uso el metodo "ofNullable"de un objeto del tipo "Opcional" que me permitira
		// darle un valor null al segundo aprametro para que no salte error si falta
		// este
		int punt = op_p.isPresent() ? op_p.get() : 0;
		//si se introdice puntuacion entonces; punt= puntuacion sino 0
		int nVic = op_nv.isPresent() ? op_nv.get() : 0;
		String nombre = this.nombre;
		try {
			DataOutputStream escribeFichero = new DataOutputStream(new FileOutputStream("Puntuacion.bin", true));
			escribeFichero.writeUTF(nombre + " " + punt +" "+ nVic + " ;");// "jugador punt nVic;"
			escribeFichero.close();
		} catch (IOException e) {
			System.out.println("Error E/S");

		}

	}

	/////////////////////// set/get////////////////////////////////

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacionActual() {
		return puntuacionActual;
	}

	public void setPuntuacionActual(int puntuacionActual) {
		this.puntuacionActual = puntuacionActual;
	}

	public boolean getLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	

}
