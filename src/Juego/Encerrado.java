package clases.src.Juego;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;
import java.io.*;

public class Encerrado {

  /**
   * Método que imprime la distribución de lugares del tablero.
   */
  private static void imprimeLugares() {
    System.out.print("Lugar 2");
    System.out.print(" -------- ");
    System.out.println("Lugar 4");
    System.out.println("| o               o |");
    System.out.println("|    o         o    |");
    System.out.println("|       o   o       |");
    System.out.print("|      ");
    System.out.print("Lugar 3");
    System.out.println("      |");
    System.out.println("|       o   o       |");
    System.out.println("|    o         o    |");
    System.out.println("| o               o |");
    System.out.print("Lugar 1");
    System.out.print("          ");
    System.out.println("Lugar 5");
  }

  /**
   * Método que le permite jugar al usuario, recibiendo
   * el estado del juego y regresando la jugada.
   *
   * @param color es el color de las fichas del usuario.
   * @param estado es el estado del juego.
   * @returns int[] la jugada del usuario.
   */
  public static int[] turnoJugador(int color, int[] estado) {
    Tablero tablero = new Tablero(estado);
    Scanner sc = new Scanner(System.in);
    boolean bandera = true;
    int lugarOrigen = -1;
    int lugarNuevo = -1;
    int posicionLibre = -1;
    for(int i = 0; i < 5; i++) {
      if(estado[i] == 2) {
        posicionLibre = i;
      }
    }
    while(bandera) {
      System.out.println("Ingrese el lugar de la ficha que desea mover.");
      imprimeLugares();
      if(sc.hasNextInt()){
        lugarOrigen = sc.nextInt();
        if(lugarOrigen >= 1 && lugarOrigen <= 5 && (estado[lugarOrigen - 1] == color)){
          if(tablero.movimientoValido(lugarOrigen-1,posicionLibre)){
            bandera=false;
          }
        }
        else{
          System.out.println("opcion inválida, ingresa un valor válido.");
          System.out.println();
        }
      }
      else{
        System.out.println("opcion inválida, ingresa un valor válido.");
        System.out.println();
        sc.next();
      }
    }
    bandera = true;
    while(bandera) {
      System.out.println("Ingrese el nuevo lugar al que desea mover la ficha.");
      imprimeLugares();
      if(sc.hasNextInt()){
        lugarNuevo = sc.nextInt();
        if(lugarNuevo >= 1 && lugarNuevo <= 5 && (estado[lugarNuevo - 1] == 2)){
          if(tablero.movimientoValido(lugarOrigen-1,lugarNuevo-1)){
            bandera=false;
          }
        }
        else{
          System.out.println("opcion inválida, ingresa un valor válido.");
          System.out.println();
        }
      }
      else{
        System.out.println("opcion inválida, ingresa un valor válido.");
        System.out.println();
        sc.next();
      }
    }
    tablero.moverFicha(--lugarOrigen, --lugarNuevo);
    return tablero.getTablero();
  }

  /**
   * Método que pide un entero y verifica si está
   * en el intervalo [menor, mayor].
   *
   * @param menor es la cota inferior.
   * @param mayor es la cota superior.
   * @returns int el entero ingresado validado.
   */
  public static int verificarEntero(int menor, int mayor) {
    Scanner sc = new Scanner(System.in);
    boolean bandera=true;
    int entero=-1;
    while(bandera) {
      if(sc.hasNextInt()){
        entero=sc.nextInt();
        if(entero >= menor && entero <= mayor){
          bandera=false;
        }
        else{
          System.out.println("opcion inválida, ingresa un valor válido.");
          System.out.println();
        }
      }
      else{
        System.out.println("opcion inválida, ingresa un valor válido.");
        System.out.println();
        sc.next();
      }
    }
    return entero;
  }

  /**
   * Método que cambia el estado del tablero.
   *
   * @returns int[] el nuevo estado.
   */
  public static int[] verificarTablero(){
    int[] colores= new int[5];
    int numAzules=0;
    int numRojas=0;
    int numBlancas=0;
    int color=-1;
    System.out.println("Ingrese la nueva distribución de fichas.");
    System.out.println("Ingrese 0 para azul, 1 para rojo y 2 para lugar vacío.");
    imprimeLugares();
    for(int i=0;i<5;i++){
      System.out.println("Color de la ficha en el lugar " + (i + 1));
      color = verificarEntero(0,2);
      if(color==0){
        if(numAzules<2){
          colores[i]=color;
          numAzules++;
        }else{
          System.out.println("No puedes agregar más de dos fichas azules :(");
          i--;
          continue;
        }
      }else if(color==1){
        if(numRojas<2){
          colores[i]=color;
          numRojas++;
        }else{
          System.out.println("No puedes agregar más de dos fichas rojas :(");
          i--;
          continue;
        }
      }else{
        if(numBlancas<1){
          colores[i]=color;
          numBlancas++;
        }
        else{
          System.out.println("Solo puedes tener un lugar vacío :(");
          i--;
          continue;
        }
      }
    }
    return colores;
  }

  /**
   * Método que verifica la entrada del usuario.
   *
   * @returns String la opción del usuario.
   */
  public static String verificarYN() {
    Scanner sc = new Scanner(System.in);
    boolean bandera=true;
    String caracter="";
    while(bandera) {
      if(sc.hasNext()){
        caracter=sc.next();
        if(caracter.equals("y")||caracter.equals("Y")||caracter.equals("n")||caracter.equals("N")){
          if(caracter.equals("y")||caracter.equals("Y")){
            return "y";
          }else{
            return "n";
          }
        }
        else{
          System.out.println("opcion inválida, ingresa una opción válida.");
          System.out.println();
        }
      }
      else{
        System.out.println("opcion inválida, ingresa una opción válida.");
        System.out.println();
        sc.next();
      }
    }
    return caracter;
  }

  public static void main(String args[]){
    int[] estado = {0,1,2,0,1}; // Estado inicial por default del tablero.
    boolean jugadorInicial = true;
    boolean seguirJugando = true;
    Scanner sc = new Scanner(System.in);
    int colorU = 0; // Color de las fichas del usuario.
    int colorM = 1; // Color de las fichas de la máquina.
    boolean modoJuego = false;

    System.out.println("====== E N C E R R A D O ======");

    System.out.println("¿Desea cambiar la distribución por default de las fichas? (y/n)");
    System.out.println("SI: Ingrese el carácter 'y'. NO: Ingrese 'n'.");
    char opcion;
    try {
      opcion = sc.next().charAt(0);
    } catch (InputMismatchException ime) {
      System.out.println("Opción inválida.");
      opcion = 'n';
      sc = new Scanner(System.in);
    }
    if(opcion == 'y') {
      while(seguirJugando) {
        try {
          estado = verificarTablero();
          seguirJugando = false;
        }
        catch (InputMismatchException ime) {
          Arrays.fill(estado,2);
          sc = new Scanner(System.in);
  			  continue;
        }
      }
    }

    Tablero tablero = new Tablero(estado);
    tablero.imprimeTablero();

    seguirJugando = true;
    while(seguirJugando) {
      try {
        System.out.println("Elija el color de sus fichas.");
        System.out.println("0 - azul y 1 - rojo");
         int op = sc.nextInt();
         if(op == 0) {
           break;
         }
         else if(op == 1) {
           colorU = 1;
           colorM = 0;
           break;
         } else {
           System.out.println("Color inválido.");
           continue;
         }
      }
      catch (InputMismatchException ime) {
        sc = new Scanner(System.in);
        continue;
      }
    }

    seguirJugando = true;
    while(seguirJugando) {
      try {
        System.out.println("Ingrese quien será el jugador inicial.");
        System.out.println("0 - usuario y 1 - máquina");
         int op = sc.nextInt();
         if(op == 0) {
           break;
         }
         else if(op == 1) {
           jugadorInicial = false;
           break;
         } else {
           System.out.println("Opción inválida.");
           continue;
         }
      }
      catch (InputMismatchException ime) {
        sc = new Scanner(System.in);
        continue;
      }
    }

    while(seguirJugando) {
      try {
        System.out.println("Ingrese el modo de juego");
        System.out.println("0 - minimax y 1 - azar");
         int op = sc.nextInt();
         if(op == 0) {
           modoJuego = true;
           break;
         }
         else if(op == 1) {
           modoJuego = false;
           break;
         } else {
           System.out.println("Opción inválida.");
           continue;
         }
      }
      catch (InputMismatchException ime) {
        sc = new Scanner(System.in);
        continue;
      }
    }

    Maquina ia = new Maquina(colorM, tablero);

    int ronda = 1;

    String op = "";
    if(jugadorInicial) {
      do {
        System.out.println();
        System.out.println("====== RONDA " + ronda + " ======");
        ronda++;
        tablero.imprimeTablero();
        tablero.setTablero(turnoJugador(colorU, tablero.getTablero()));
        System.out.println("Jugada usuario");
        tablero.imprimeTablero();
        if(tablero.perdedor(colorU) || tablero.perdedor(colorM)) {
          break;
        }
        tablero.setTablero(ia.juega(tablero.getTablero(), modoJuego));
        System.out.println("Jugada máquina");
        tablero.imprimeTablero();
        if(tablero.perdedor(colorU) || tablero.perdedor(colorM)) {
          break;
        }
        try {
          System.out.println("");
          System.out.println("¿Desea seguir jugando? (y/n)");
          opcion = sc.next().charAt(0);
        }
        catch (InputMismatchException ime) {
          System.out.println("Para elegir la opción escriba sólo una opción válida.");
          opcion = 'y';
          sc = new Scanner(System.in);
          continue;
        }
        if(opcion == 'n') seguirJugando = false;
        if(modoJuego){
            System.out.println("La máquina está jugando en modo minimax, ¿Desea cambiarla a modo azar? (y/n)");
            op=verificarYN();
            if(op=="y") modoJuego=false;
          }else{
            System.out.println("La máquina está jugando en modo azar, ¿Desea cambiarla a modo minimax? (y/n)");
            op=verificarYN();
            if(op=="y")modoJuego=true;
          }
      } while(seguirJugando && !tablero.perdedor(0) && !tablero.perdedor(1));
    }
    else {
      do {
        System.out.println();
        System.out.println("====== RONDA " + ronda + " ======");
        ronda++;
        tablero.imprimeTablero();
        if(tablero.perdedor(colorU) || tablero.perdedor(colorM)) {
          break;
        }
        tablero.setTablero(ia.juega(tablero.getTablero(), modoJuego));
        System.out.println("Jugada máquina");
        tablero.imprimeTablero();
        if(tablero.perdedor(colorU) || tablero.perdedor(colorM)) {
          break;
        }
        tablero.setTablero(turnoJugador(colorU, tablero.getTablero()));
        System.out.println("Jugada usuario");
        tablero.imprimeTablero();
        if(tablero.perdedor(colorU) || tablero.perdedor(colorM)) {
          break;
        }
        try {
          System.out.println("");
          System.out.println("¿Desea seguir jugando? (y/n)");
          opcion = sc.next().charAt(0);
        }
        catch (InputMismatchException ime) {
          System.out.println("Para elegir la opción escriba sólo una opción válida.");
          opcion = 'y';
          sc = new Scanner(System.in);
          continue;
        }
        if(opcion == 'n') seguirJugando = false;
        if(modoJuego){
            System.out.println("La máquina está jugando en modo minimax, ¿Desea cambiarla a modo azar? (y/n)");
            op=verificarYN();
            if(op=="y") modoJuego=false;
          }else{
            System.out.println("La máquina está jugando en modo azar, ¿Desea cambiarla a modo minimax? (y/n)");
            op=verificarYN();
            if(op=="y")modoJuego=true;
          }
      } while(seguirJugando && !tablero.perdedor(0) && !tablero.perdedor(1));
    }
    if(tablero.perdedor(0)) {
      System.out.println("Perdió el jugador de las fichas azules.");
    }
    else if(tablero.perdedor(1)) {
      System.out.println("Perdió el jugador de las fichas rojas.");
    } else {
      System.out.println("Nadie ganó");
    }

  }
}