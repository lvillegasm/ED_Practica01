/**
* Práctica 1 del curso de Estructuras de Datos.
* @author Emmanuel Cruz Hernández.
* @version 1.0 Marzo 2021.
* @since Laboratorio de Estructuras de Datos 2020-2.
*/
public class Practica01{

	/**
	* Encuentra el índice del primer y último 
	* valor específico.
	* @param num un arreglo de enteros.
	* @param value el valor a encontrar índices.
	* @return un arreglo de longitud 2, con el primer y 
	* último índice donde se encuentra el elemento value.
	*/
	public static int[] findFirstAndLast(int[] num, int value){
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		
		for(int i = 0; i < num.length ; i++)
			if(num[i] == value){
				result[0] = i;
				break;
			}

		for(int j = num.length-1 ; j >= 0 ; j--)
			if(num[j] == value){
				result[1] = j;
				break;
			}

		return result;
	}

    /**
    * Verifica si un tablero de sudoku de 6x6 es válido, considerando
    * únicamente los casos de las verticales y las diagonales.
    * @param board el arreglo bidimensional de 6x6 que representa
    * el tablero.
    * @return true si el tablero es válido, false, en otro caso.
    */
    public static boolean isSudokuValid(int[][] board){
    	int length = board.length;
		for (int i = 0; i < length ; i++) {
			for (int j = 1; j <= length ; j++ ) {
				boolean verificador = false;
				// Verifica sobre las filas
				for(int k = 0 ; k < length; k++){
					if(board[i][k] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
				verificador = false;
				// Verifica sobre las columnas
				for(int k = 0 ; k < length; k++){
					if(board[k][i] == j){
						verificador = true;
						break;
					}
				}
				if(!verificador){
					return false;
				}
			}
		}
		return true;
	}

	/**
	* Rota position cantidad de veces los elementos de un arreglo
	* hacia el vecino izquierdo.
	* @param num el arreglo de enteros a rotar.
	* @param position la cantidad de espacios a rotar.
	*/
	public static void rotateArray(int[] num, int position){
		for(int i = 0; i < position ; i++){
			int aux = num[0];
			for(int j = 0; j < num.length -1 ; j++){
				num[j] = num[j+1];
			}
			num[num.length-1] = aux;
		}
	}

	public static void main(String[] args) {
		// EJEMPLOS DE ACTIVIDAD 1
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 1\n");

		int[] ejemplo1a = {1,4,2,1,6,2,9};
		int[] ejemplo1b = {4,2,7,5,4,3,7,2,5,3,4,1};
		int[] ejemplo1c = {3,2,1,4,2};

		int[] resultado1a = findFirstAndLast(ejemplo1a, 2);
		System.out.println("["+resultado1a[0]+", "+resultado1a[1]+"]");

		int[] resultado1b = findFirstAndLast(ejemplo1b, 15);
		System.out.println("["+resultado1b[0]+", "+resultado1b[1]+"]");

		int[] resultado1c = findFirstAndLast(ejemplo1c, 1);
		System.out.println("["+resultado1c[0]+", "+resultado1c[1]+"]");



		// EJEMPLOS DE ACTIVIDAD 2
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 2\n");

		int[][] ejemplo2a = {{4,5,6,2,3,1},
							{3,1,2,6,4,5},
							{1,6,4,3,5,2},
							{5,2,3,1,6,4},
							{2,3,5,4,1,6},
							{6,4,1,5,2,3}};

		int[][] ejemplo2b = {{4,5,6,2,3,1},
							{3,1,2,6,4,5},
							{2,6,4,3,5,2},
							{5,2,3,1,6,4},
							{1,3,5,4,1,6},
							{6,4,1,5,2,3}};

		boolean resultado2a = isSudokuValid(ejemplo2a);
		System.out.println("El sudoku 1 es válido: "+resultado2a);

		boolean resultado2b = isSudokuValid(ejemplo2b);
		System.out.println("El sudoku 2 es válido: "+resultado2b);



		// EJEMPLOS DE ACTIVIDAD 3
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 1\n");

		rotateArray(ejemplo1a, 5);
		rotateArray(ejemplo1b, 0);
		rotateArray(ejemplo1c, 2);

		System.out.println("Arreglo 1 rotado 5 veces");
		for(int i : ejemplo1a)
			System.out.print(i + " ");

		System.out.println("\nArreglo 2 rotado 0 veces");
		for(int i : ejemplo1b)
			System.out.print(i + " ");

		System.out.println("\nArreglo 3 rotado 2 veces");
		for(int i : ejemplo1c)
			System.out.print(i + " ");

		System.out.println("\n\nFIN DE EJEMPLOS\n");
	}
}