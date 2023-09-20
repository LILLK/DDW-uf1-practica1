//Mehdi Tahrat

import java.util.Scanner;

public class programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcion;
		boolean salir = false;
		Pj pj = new Pj();
		while (!salir) {
			if (pj.logged)
				System.out.println("Usuario: " + pj.nombre);
			else
				System.out.println("Usuario: ");
			System.out.println("\n-- MENU --");
			System.out.println("1. Jugar");
			System.out.println("2. Mostrar Ranking");
			System.out.println("3. Salir");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				partida.jugar(pj);
				break;
			case 2:
				Ranking.ranking();
				break;
			case 3:
				salir = true;
				System.out.println("\nGracias por usar el programa!");
				break;
			default:
				System.out.println("\nOpcion no valida!");
				break;
			}
		}
		sc.close();
	}

}
