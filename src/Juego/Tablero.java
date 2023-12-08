package clases.src.Juego;

import clases.src.colors.Colors;

public class Tablero {

  /** Arreglo con los valores de las fichas. */
  private Ficha[] fichas= new Ficha[5];

  /**
   * Constructor vacío del tablero que crea una
   * disposición alternada de las fichas por default.
   *
   */
  public Tablero(){
    this.fichas[0]= new Ficha(0);
    this.fichas[1]= new Ficha(1);
    this.fichas[2]= new Ficha(2);
    this.fichas[3]= new Ficha(0);
    this.fichas[4]= new Ficha(1);
  }

  /**
   * Constructor del tablero que recibe un arreglo de
   * enteros y se los pasa al arreglo atributo de la clase.
   *
   * @param colores los valores de las fichas en el tablero.
   */
  public Tablero(int[] colores){
    this.fichas[0]= new Ficha(colores[0]);
    this.fichas[1]= new Ficha(colores[1]);
    this.fichas[2]= new Ficha(colores[2]);
    this.fichas[3]= new Ficha(colores[3]);
    this.fichas[4]= new Ficha(colores[4]);

  }

  /**
   * Getter del tablero que regresa el estado actual
   * a través de un arreglo de enteros con los valores
   * de las fichas por su poisición en el arreglo.
   *
   * @returns arreglo con la ubicación de las fichas.
   */
  public int[] getTablero() {
    int[] colores = new int[5];
    for(int i = 0; i < 5; i++) {
      colores[i] = fichas[i].getColor();
    }
    return colores;
  }

  /**
   * Setter del tablero que según un arreglo de enteros
   * actualiza el valor de todos lo lugares del
   * tablero sin restricciones.
   *
   * @param estadoNuevo el nuevo estado de los lugares del tablero.
   */
  public void setTablero(int[] estadoNuevo) {
    for(int i = 0; i < 5; i++) {
      fichas[i].setColor(estadoNuevo[i]);
    }
  }

  /**
   * Mueve una ficha del lugar en el que se encuentra a un lugar
   * nuevo si cumple con las condiciones para poder moverse. En
   * caso contrario deja el tablero intacto.
   *
   * @param lugarOrigen el lugar de la ficha que se desea mover.
   * @param lugarNuevo el lugar al que se desea mover la ficha.
   * @returns boolean que nos indica si se pudo hacer el movimiento.
   */
  public boolean moverFicha(int lugarOrigen, int lugarNuevo){
    if(fichas[lugarNuevo].getColor() == 2 && Math.abs(lugarOrigen - lugarNuevo) <= 2) {
      int color = fichas[lugarOrigen].getColor();
      fichas[lugarOrigen].setColor(2);
      fichas[lugarNuevo].setColor(color);
      return true;
    }
    System.out.println("No se puede mover la ficha al lugar indicado.");
    return false;
  }

  /**
   * Método que nos indica por medio de un booleano si algún
   * movimiento de una ficha es válido entre dos lugares.
   *
   * @param lugarOrigen el lugar de la ficha que se desea mover.
   * @param lugarNuevo el lugar al que se desea ver si se puede mover.
   * @returns boolean que nos indica si se puede hacer el movimiento.
   */
  public boolean movimientoValido(int lugarOrigen, int lugarNuevo) {
    if(fichas[lugarNuevo].getColor() == 2 && Math.abs(lugarOrigen - lugarNuevo) <= 2) {
      return true;
    }
    return false;
  }

  /**
   * Método que según el estado actual del tablero,
   * regresa una lista con las psoibles juagadas
   * de un cierto color de fichas.
   *
   * @param color es el color de las fichas a mover.
   * @return lista con máximo dos posibles jugadas.
   */
  public Lista<int[]> posiblesJugadas(int color){
      int[] posiciones = new int[2];
      int contador = 0;
      int posicionBlanca = -1;
      int[] auxIzq = new int[5];
      int[] auxDer = new int[5];
      Lista<int[]> posiblesJug = new Lista<int[]>();
      //ArrayList<int[]> posiblesJug = new ArrayList<int[]>();

      for(int i = 0; i < 5; i++) {
        auxIzq[i]=fichas[i].getColor();
        auxDer[i]=fichas[i].getColor();
        if(fichas[i].getColor() == color) {
          posiciones[contador] = i;
          contador++;
        }
        if(fichas[i].getColor()==2){
          posicionBlanca=i;
        }
      }
      if(movimientoValido(posiciones[0],posicionBlanca)){
        auxIzq[posiciones[0]]=2;
        auxIzq[posicionBlanca]=color;
      }if(!movimientoValido(posiciones[0],posicionBlanca)){
        auxIzq=null;
      }if(movimientoValido(posiciones[1],posicionBlanca)){
        auxDer[posiciones[1]]=2;
        auxDer[posicionBlanca]=color;
      }if(!movimientoValido(posiciones[1],posicionBlanca)){
        auxDer=null;
      }
      posiblesJug.add(auxIzq);
      posiblesJug.add(auxDer);

      return posiblesJug;
  }

  /**
   * Método que revisa las condiciones en las cuales termina
   * el juego y nos indica si el jugador del color ingresado
   * perdió la partida.
   *
   * @param color el color del jugador a revisar.
   * @returns boolean que nos indica si perdió.
   */
  public boolean perdedor(int color) {
    int[] posicion = new int[2];
    int contador = 0;
    for(int i = 0; i < 5; i++) {
      if(fichas[i].getColor() == color) {
        posicion[contador] = i;
        contador++;
      }
    }
    if((posicion[0] == 0 && posicion[1] == 1) && (fichas[2].getColor() != 2) && (fichas[3].getColor() != 2)) {
      return true;
    }
    if((posicion[0] == 3 && posicion[1] == 4) && (fichas[2].getColor() != 2) && (fichas[1].getColor() != 2)) {
      return true;
    }
    return false;
  }

  /**
   * Método imprime en consola una representación
   * visual del tablero para el usuario.
   *
   */
  public void imprimeTablero(){
    System.out.print(this.fichas[1]);
    Colors.print(" ----------------- ",Colors.WHITE + Colors.HIGH_INTENSITY);
    System.out.println(this.fichas[3]);
    Colors.println("| o               o |",Colors.WHITE + Colors.HIGH_INTENSITY);
    Colors.println("|    o         o    |",Colors.WHITE + Colors.HIGH_INTENSITY);
    Colors.println("|       o   o       |",Colors.WHITE + Colors.HIGH_INTENSITY);
    Colors.print("|         ",Colors.WHITE + Colors.HIGH_INTENSITY);
    System.out.print(this.fichas[2]);
    Colors.println("         |",Colors.WHITE + Colors.HIGH_INTENSITY);
    Colors.println("|       o   o       |",Colors.WHITE + Colors.HIGH_INTENSITY);
    Colors.println("|    o         o    |",Colors.WHITE + Colors.HIGH_INTENSITY);
    Colors.println("| o               o |",Colors.WHITE + Colors.HIGH_INTENSITY);
    System.out.print(this.fichas[0]);
    System.out.print("                   ");
    System.out.println(this.fichas[4]);
  }
 }