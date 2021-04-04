package fciencias.edatos.practica01;
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
	* Encuentra el índice del primer y último 
	* valor específico, con complejidad reducida.
	* @param num un arreglo de enteros.
	* @param value el valor a encontrar índices.
	* @return un arreglo de longitud 2, con el primer y 
	* último índice donde se encuentra el elemento value.
	*/
	public static int[] myFindFirstAndLast(int[] num, int value){
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		int ultimoValorMitadIzquierda=-1; //Valor auxiliar para evitar recorrer más de la mitad
		int ultimoValorMitadDerecha=-1; //Valor auxiliar para evitar recorrer más de la mita
		int mitad=(num.length/2)+(num.length%2); //obtenemos el lugar del valor o valores centrales del arreglo
		for(int i = 0; i < mitad ; i++){

			//verificamos que no encontraramos antes y el valor
			if(result[0]==-1 && num[i] == value){ 
				result[0] = i;
			}
			//verificamos que no encontraramos antes y el valor
			if(result[1]==-1 && num[num.length-1-i] == value){ 
				result[1] = num.length-1-i;
			}
			// evitamos recorrer mas de lo necesario
			if(result[0]!=-1 && result[1]!=-1){ 
				return result; //tenemos lo que necesitamos
			}
			//guardamos para que conservemos la ultima aparicion del lado izquierdo
			if(num[i] == value){ 
				ultimoValorMitadIzquierda = i;
			}
			//guardamos para que conservemos la ultima aparicion del lado derecho
			if(num[num.length-1-i] == value){ 
				ultimoValorMitadDerecha = num.length-1-i;
			}
		}
			if(result[0]==-1 && ultimoValorMitadDerecha!=-1){ //revisamos si estaba en la otra mitad y le asignamos el valor
			result[0]=ultimoValorMitadDerecha;
			}
			if(result[1]==-1 && ultimoValorMitadIzquierda!=-1){ //guardamos para que conservemos la ultima aparicion del lado izquierdo
			result[1]=ultimoValorMitadIzquierda;
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
    * Verifica si un tablero de sudoku de 6x6 es válido, considerando
    * únicamente los casos de las verticales y las diagonales, con complejidad reducida.
    * @param board el arreglo bidimensional de 6x6 que representa
    * el tablero.
    * @return true si el tablero es válido, false, en otro caso.
    */
    public static boolean myIsSudokuValid(int[][] board){
    	int length = board.length;
		/*
		las matrices verificador{x|y} es una matriz que guardara si los 
		valores de 1 a length se han encuentrado en la fila (x) o columna (y) ,
		cada posicion representa un valor igual a posicion de casilla + 1
		*/
		boolean[] verificadorx; 
		boolean[] verificadory; 

		/*
		contador{x|y} serviran para ahorrar un ciclo y verificar rapido
		que todos los valores se encontraron
		*/
		int contadorx;
		int contadory;
		for (int i = 0; i < length ; i++) {

			verificadorx = new boolean[length]; // reseteamos a false
			verificadory = new boolean[length]; // reseteamos a false
			contadorx=0; //reseteamos a 0
			contadory=0; //reseteamos a 0
			for (int j = 0 ; j < length ; j++ ) {
				int valorx = board[i][j]; //valor casilla en fila
				int valory = board[j][i]; //valor casilla en columna
				int xindex = valorx-1; //posicion en verificadorx
				int yindex = valory-1; //posicion en verificadory
				
				/*
				verificamos que sean valores de rango valido, si no lo son
				no es valido el sudoku y evitamos tambien indexOutofbounds exception
				*/		
				if(valorx<1 || valorx>length || valory<1 || valory>length){

					return false;
				}

				/*
				Se encontro un valor duplicado en fila y columna, no hace
				falta continuar ya que es invalido
				*/	
				if(verificadorx[xindex] || verificadory[yindex]){
						return false;
				}

				//Marcamos como encontrado y sumamos si no se habia encontrado
				if(!verificadorx[xindex]){
					verificadorx[xindex]=true; // validamos existencia de valor en fila
					contadorx++;
				}
				
				//Marcamos como encontrado y sumamos si no se habia encontrado
				if(!verificadory[yindex]){
					verificadory[yindex]=true; // validamos existencia de valor en columna
					contadory++;
				}
				
			}
			/*
				Si llega hasta aqui es que ya no debe haber repetidos
				solamente hace falta verificar que coincida el tamano
				con las coincidencias encontradas
			*/	
			if(contadorx!=length  || contadory!=length){
				return false;
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

		/**
	* Rota position cantidad de veces los elementos de un arreglo
	* hacia el vecino izquierdo, con complejidad reducida.
	* @param num el arreglo de enteros a rotar.
	* @param position la cantidad de espacios a rotar.
	*/
	public static void myRotateArray(int[] num, int position){
		/*
		Obtenemos la cantidad de desplazamientos reales ya que si se desplaza
		una cantidad múltiplo de num.length, volverán a estar en el mismo
		lugar los valores, lo que nos interesa son los movimientos restantes
		después de mover la mayor cantidad posible de num.length posiciones,
		para ello usamos simplemente la operación módulo.
		 */
		int size = num.length;
		int realMoves= (position%size); 
		boolean[] overwritten = new boolean[size]; // para revisar si un valor ya fue sobreescrito
		int[] originalvalue = new int[size]; // guardar los valores originales en caso de sobreescribirse
		for(int i = 0; i < size ; i++){
			if(i>=realMoves){ //fácil obtención de índice si no alcanza el límite del array, solamente restar.
				overwritten[i-realMoves]=true;
				originalvalue[i-realMoves]=num[i-realMoves];
				num[i-realMoves]=overwritten[i]?originalvalue[i]:num[i]; // si fue sobreescrita usar el valor original guardado, sino usar el valor de num

			}else{ //size-realMoves+1 nos indica la casilla  en caso de que se mueva más allá del límite del array en los valores al haber limitado a movimientos netos.
				overwritten[size-realMoves+i]=true;
				originalvalue[size-realMoves+i]=num[size-realMoves+i];
				num[size-realMoves+i] = overwritten[i]?originalvalue[i]:num[i];  // si fue sobreescrita usar el valor original guardado, sino usar el valor de num

			}

		}
	}

	public static void main(String[] args) {
		//variables para tiempos de ejecucion
		long inicio,fin;

		// EJEMPLOS DE ACTIVIDAD 1
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 1\n");

		int[] ejemplo1a = {1,4,2,1,6,2,9};
		int[] ejemplo1b = {4,2,7,5,4,3,7,2,5,3,4,1};
		int[] ejemplo1c = {3,2,1,4,2};

		int[] ejemplo1a2 = {1,4,2,1,6,2,9};
		int[] ejemplo1b2 = {4,2,7,5,4,3,7,2,5,3,4,1};
		int[] ejemplo1c2 = {3,2,1,4,2};

		inicio = System.currentTimeMillis();
		int[] resultado1a = findFirstAndLast(ejemplo1a, 2);
		fin = System.currentTimeMillis();
		System.out.println("["+resultado1a[0]+", "+resultado1a[1]+"] Tiempo (ms): "+(fin-inicio));

		inicio = System.currentTimeMillis();
		int[] resultado1b = findFirstAndLast(ejemplo1b, 15);
		fin = System.currentTimeMillis();
		System.out.println("["+resultado1b[0]+", "+resultado1b[1]+"] Tiempo (ms): "+(fin-inicio));

		inicio = System.currentTimeMillis();
		int[] resultado1c = findFirstAndLast(ejemplo1c, 1);
		fin = System.currentTimeMillis();
		System.out.println("["+resultado1c[0]+", "+resultado1c[1]+"] Tiempo (ms): "+(fin-inicio));

		// NUEVO ALGORITMO EJEMPLOS DE ACTIVIDAD 1
		System.out.println("\nNUEVO ALGORITMO CON EJEMPLOS DE ACTIVIDAD 1\n");
				inicio = System.currentTimeMillis();
		resultado1a = myFindFirstAndLast(ejemplo1a, 2);
		fin = System.currentTimeMillis();
		System.out.println("["+resultado1a[0]+", "+resultado1a[1]+"] Tiempo (ms): "+(fin-inicio));

		inicio = System.currentTimeMillis();
		resultado1b = myFindFirstAndLast(ejemplo1b, 15);
		fin = System.currentTimeMillis();
		System.out.println("["+resultado1b[0]+", "+resultado1b[1]+"] Tiempo (ms): "+(fin-inicio));

		inicio = System.currentTimeMillis();
		resultado1c = myFindFirstAndLast(ejemplo1c, 1);
		fin = System.currentTimeMillis();
		System.out.println("["+resultado1c[0]+", "+resultado1c[1]+"] Tiempo (ms): "+(fin-inicio));


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
		inicio = System.currentTimeMillis();
		boolean resultado2a = isSudokuValid(ejemplo2a);
		fin = System.currentTimeMillis();
		System.out.println("El sudoku 1 es válido: "+resultado2a + " -Tiempo (ms): "+(fin-inicio));
		inicio = System.currentTimeMillis();
		boolean resultado2b = isSudokuValid(ejemplo2b);
		fin = System.currentTimeMillis();
		System.out.println("El sudoku 2 es válido: "+resultado2b + " - Tiempo (ms): "+(fin-inicio));
		
		// NUEVO ALGORITMO CON EJEMPLOS DE ACTIVIDAD 2
		System.out.println("\nNUEVO ALGORITMO CON EJEMPLOS DE ACTIVIDAD 2\n");
		inicio = System.currentTimeMillis();
		resultado2a = myIsSudokuValid(ejemplo2a);
		fin = System.currentTimeMillis();
		System.out.println("El sudoku 1 es válido: "+resultado2a + " -Tiempo (ms): "+(fin-inicio));
		inicio = System.currentTimeMillis();
		resultado2b = myIsSudokuValid(ejemplo2b);
		fin = System.currentTimeMillis();
		System.out.println("El sudoku 2 es válido: "+resultado2b + " - Tiempo (ms): "+(fin-inicio));

		// EJEMPLOS DE ACTIVIDAD 3
		System.out.println("\nEJEMPLOS DE ACTIVIDAD 3\n");
		inicio = System.currentTimeMillis();
		rotateArray(ejemplo1a, 5);
		fin = System.currentTimeMillis();

		System.out.println("Arreglo 1 rotado 5 veces -Tiempo (ms): "+(fin-inicio));
		for(int i : ejemplo1a)
			System.out.print(i + " ");

		inicio = System.currentTimeMillis();
		rotateArray(ejemplo1b, 0);
		fin = System.currentTimeMillis();
		System.out.println("\nArreglo 2 rotado 0 veces -Tiempo (ms): "+(fin-inicio));
		for(int i : ejemplo1b)
			System.out.print(i + " ");


		inicio = System.currentTimeMillis();
		rotateArray(ejemplo1c, 2);
		fin = System.currentTimeMillis();
		System.out.println("\nArreglo 3 rotado 2 veces -Tiempo (ms): "+(fin-inicio));
		for(int i : ejemplo1c)
			System.out.print(i + " ");


			// NUEVO ALGORITMO CON EJEMPLOS DE ACTIVIDAD 3
		System.out.println("\n NUEVO ALGORITMO CON EJEMPLOS DE ACTIVIDAD 3\n");
		inicio = System.currentTimeMillis();
		myRotateArray(ejemplo1a2, 5);
		fin = System.currentTimeMillis();

		System.out.println("Arreglo 1 rotado 5 veces -Tiempo (ms): "+(fin-inicio));
		for(int i : ejemplo1a2)
			System.out.print(i + " ");

		inicio = System.currentTimeMillis();
		myRotateArray(ejemplo1b2, 0);
		fin = System.currentTimeMillis();
		System.out.println("\nArreglo 2 rotado 0 veces -Tiempo (ms): "+(fin-inicio));
		for(int i : ejemplo1b2)
			System.out.print(i + " ");


		inicio = System.currentTimeMillis();
		myRotateArray(ejemplo1c2, 2);
		fin = System.currentTimeMillis();
		System.out.println("\nArreglo 3 rotado 2 veces -Tiempo (ms): "+(fin-inicio));
		for(int i : ejemplo1c2)
			System.out.print(i + " ");


		

	
		System.out.println("\n\nFIN DE EJEMPLOS\n");
	}
}