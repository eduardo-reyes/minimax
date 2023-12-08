package clases.src.Juego;

import java.util.Random;

public class Maquina {

  Tablero tablaM; // tablero de la máquina con el estado actual del juego.
  int color; // Color  de fichas de la máquina.
  int otroColor; // Color de fichas del usuario.

  /**
   * Constructor vacío de la clase.
   *
   */
  public Maquina() {
  }

  /**
   * Constructor de la clase.
   *
   * @param color es el color de las fichas de la máquina.
   * @param t es el tablero con el estado del juego.
   */
  public Maquina(int color, Tablero t) {
    tablaM = new Tablero(t.getTablero());
    this.color = color;
    if(color == 0) {
      otroColor = 1;
    } else {
      otroColor = 0;
    }
  }

  /**
   * Setter del color de la ia.
   *
   * @param color el nuevo color de las fichas.
   */
  public void setColorM(int color) {
    this.color = color;
    //arbol.raiz.color = this.color;
    if(color == 0) {
      otroColor = 1;
    } else {
      otroColor = 0;
    }
  }

  /**
   * Método que simula una jugada de la máquina devolviendo
   * un arreglo de enteros que representa el nuevo estado
   * del juego. Tiene dos modos de juego, jugar con
   * minimax y moviendo al azar.
   *
   * @param tablero es el estado del juego.
   * @param modalidad es el booleano según el cual
   * vamos a decidir como jugar.
   * @return int[] con la jugada de la máquina.
   */
  public int[] juega(int[] tablero, boolean modalidad) {
    tablaM.setTablero(tablero);
    if(modalidad) {
      return minimax();
    }
    return azar();
  }

  /**
   * Método que crea un árbol de decisiones instanciando
   * un árbol del juego y decide cual es la mejor
   * jugada disponible para devolverla.
   *
   * @return int[] con la jugada del minimax.
   */
  public int[] minimax() {
    ArbolJuego<Integer> arbolm = new ArbolJuego<Integer>(tablaM, color, 0);
    boolean turno = true;
    if(color == 0) {
        turno = false;
    }
    arbolm.hazArbol(0,1,turno,tablaM,arbolm.raiz);
    //System.out.println(arbolm.toString()); Descomentar línea para ver el árbol.
    return arbolm.mejorJugada();
  }


  /**
   * Método que regresa una jugada al azar según el
   * estado del juego.
   *
   * @return int[] con la jugada.
   */
  public int[] azar(){
    Random rdm = new Random();
    int[] posiciones = new int[2];
    int contador = 0;
    int mov = -1;
    int[] colores = tablaM.getTablero();
    int libre = -1;

    for(int i = 0; i < 5; i++) {
      if(colores[i] == this.color) {
        posiciones[contador] = i;
        contador++;
      }if(colores[i] == 2) {
        libre = i;
      }

    }
    mov = rdm.nextInt(1)+1;
    if(tablaM.movimientoValido(posiciones[mov],libre)) {
      tablaM.moverFicha(posiciones[mov],libre);
    }
    else{
      if(mov==0){
        tablaM.moverFicha(posiciones[1],libre);
      }
      else{
        tablaM.moverFicha(posiciones[0],libre);
      }
    }
    return tablaM.getTablero();
  }

}
