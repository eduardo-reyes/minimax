package clases.src.Juego;

import java.util.*;

public class ArbolJuego<T extends Comparable<T>> extends ArbolBinario<T> {

  /**
   * Clase interna protegida para vértices de árbol de juego. La única
   * diferencia con los vértices de árbol binario.
   * Difieren en tener atributos para un valor, su profundidad, color
   * y un tablero del juego encerrado.
  */
  protected class VerticeMM extends Vertice {

      /** El valor del vértice.
       * 0 si hay empate.
       * 1 si gana quien se quiere maximizar.
       * -1 si gana el rival.
       */
      private int valor;
      /** La profundidad del vértice. */
      private int profundidad;
      /** El color o turno actual. */
      private int color;
      /** El estado del tablero. */
      private Tablero tablero;

      /** Constructor de vértice para el minimax.
       *
       * @param elemento el elemento del vértice.
       * @param valor el valor de acuerdo a ganar/perder.
       * @param profundidad es la profunidad del vértice en el árbol.
       * @param color el color de las fichas de ese turno.
       * @param tablero el estado del tablero en ese turno.
       */
      public VerticeMM(T elemento, int valor, int profundidad, int color, Tablero tablero){
          super(elemento);
          this.valor = valor;
          this.profundidad = profundidad;
          this.color = color;
          this.tablero = tablero;
      }

      /**
       * Regresa una representación en cadena del vérticeMM.
       *
       * @return una representación en cadena del vérticeMM.
       */
      public String toString() {
          if (this == null)
              return "Vacío. Profundidad: 0";
          return "Valor: " + String.valueOf(this.valor) + " Profunidad: " + String.valueOf(this.profundidad) + " Color: " + String.valueOf(this.color);
      }

      /**
       * Compara el vértice con otro objeto. La comparación es
       * <em>recursiva</em>.
       *
       * @param o el objeto con el cual se comparará el vértice.
       * @return <code>true</code> si el objeto es instancia de la clase
       *         {@link VerticeAVL}, su elemento es igual al elemento de
       *         éste vértice, los descendientes de ambos son recursivamente
       *         iguales, y los colores son iguales; <code>false</code> en
       *         otro caso.
       */
      @Override
      public boolean equals(Object o) {
          if (o == null || getClass() != o.getClass())
              return false;
          @SuppressWarnings("unchecked")
          VerticeMM v = (VerticeMM) o;
          return this.color == v.color && this.tablero == v.tablero;
      }

      /**
       * Método que devuelve el valor de un vértice.
       *
       * @return entero que representa la altura.
       */
      public int getValor() {
        return this.valor;
      }

      /**
       * Método que pone el valor de un vértice.
       *
       * @return entero que representa la altura.
       */
      public void setValor(int valor) {
        this.valor = valor;
      }

      /**
       * Método que devuelve el estado del tablero
       * como un arreglo de enteros según los
       * colores y posiciones de las fichas.
       *
       * @return int[] con el estado del tablero.
       */
      public int[] getTablero() {
        return this.tablero.getTablero();
      }

  }

    private class Iterador implements Iterator<T>{
        private Pila<Vertice> pila;

        public Iterador(){
            pila = new Pila<Vertice>();
            Vertice p = raiz;
            while (p!= null) {
                pila.push(p);
                p = p.izquierdo;
            }
        }

        public boolean hasNext() {
          return !pila.isEmpty();
        }

        public T next(){
            if(pila.isEmpty()){
                throw new NoSuchElementException("vacio");
            }
            Vertice v = pila.pop();
            if(v.derecho != null){
                Vertice u = v.derecho;
                while (u!=null) {
                    pila.push(u);
                    u=u.izquierdo;
                }
            }

            return v.elemento;
        }

    }

    private int colorA = 2; // Color de la máquina (Árbol)
    private int colorU = 2; // Color del usuario

    /**
      * Constructor sin parámetros. Para no perder el constructor sin parámetros
      * de {@link ArbolBinario}.
      */
    public ArbolJuego() {
      super();
    }

    /**
      * Constructor que recibe una colección.
      * Para no perder el constructor
      * de {@link ArbolBinario}.
      */
    public ArbolJuego(Collection<T> coleccion) {
      super(coleccion);
    }

    /**
     * Constructor de la clase.
     *
     * @param lista es la lista de la que se hará el árbol.
     * @param isSorted es true si la lista está ordenada y viceversa.
     */
    public ArbolJuego(Tablero tablero, int color, T elemento) {
      this.colorA = color;
      if(colorA == 0) {
        this.colorU = 1;
      } else {
        this.colorU = 0;
      }
      raiz = nuevoVertice(elemento, 0, 0, color, tablero);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeMM}.
     *
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice MM con el elemento recibido dentro del mismo.
     */
    //@Override
    protected VerticeMM nuevoVertice(T elemento, int valor, int profundidad, int color, Tablero tablero) {
        return new VerticeMM(elemento, valor, profundidad, color, tablero);
    }

    /**
     * Método que construye el árbol de posibles juagadas sobre
     * el cual trabaja minimax para evaluar numéricamente las mejores
     * ramas. Sirve como filtro para trabjar con el otro método de
     * igual nombre, pero que trabaja con vérticesMM.
     *
     * @param elemento es el elemnto d elos vértices.
     * @param profundidad es la profunidad actual del árbol
     * donde se insertará el vértice.
     * @param turno es un booleano que nos indica el turno del jugador.
     * @param tablero es el nuevo estado del tablero.
     * @param vertice es el vértice desde el cual se creará el árbol.
     * @returns int que es el minimax.
     */
    public int hazArbol(T elemento, int profundidad, boolean turno, Tablero tablero, Vertice vertice) {
      VerticeMM v = (VerticeMM) vertice;
      return hazArbol(elemento, profundidad, turno, tablero, v);
    }

    /**
     * Método que construye el árbol de posibles juagadas sobre
     * el cual trabaja minimax para evaluar numéricamente las mejores
     * ramas. Dado un estado del tablero y un color/turno, decide
     * crear más vértices si hay empate o regresar si hay un ganandor.
     *
     * @param elemento es el elemnto d elos vértices.
     * @param profundidad es la profunidad actual del árbol
     * donde se insertará el vértice.
     * @param turno es un booleano que nos indica el turno del jugador.
     * @param tablero es el nuevo estado del tablero.
     * @param verticeMM es el vértice desde el cual se creará el árbol.
     * @returns int que es el minimax.
     */
    public int hazArbol(T elemento, int profundidad, boolean turno, Tablero tablero, VerticeMM vertice) {
      int miniMax = 0;
      int color = 2;
      if(turno) {
        color = 1;
      } else {
        color = 0;
      }
      int valIzq = 0;
      int valDer = 0;
      Lista<int[]> lista = tablero.posiblesJugadas(color);
      if(profundidad <= 10) {
        if(lista.getElemento(1) != null) {
          Tablero jugada1 = new Tablero(lista.getElemento(1));
          if(jugada1.perdedor(colorU)) {
            vertice.izquierdo = nuevoVertice(elemento, 1, profundidad, color, jugada1);
            vertice.izquierdo.padre = vertice;
            valIzq = 1;
          }
          else if(jugada1.perdedor(colorA)) {
            vertice.izquierdo = nuevoVertice(elemento, -1, profundidad, color, jugada1);
            vertice.izquierdo.padre = vertice;
            valIzq = -1;
          }
          else {
            vertice.izquierdo = nuevoVertice(elemento, 0, profundidad, color, jugada1);
            vertice.izquierdo.padre = vertice;
            valIzq = hazArbol(elemento, profundidad + 1, !turno, jugada1, vertice.izquierdo);
          }
        }
        if(lista.getElemento(2) != null) {
          Tablero jugada2 = new Tablero(lista.getElemento(2));
          if(jugada2.perdedor(colorU)) {
            vertice.derecho = nuevoVertice(elemento, 1, profundidad, color, jugada2);
            vertice.derecho.padre = vertice;
            valDer = 1;
          }
          else if(jugada2.perdedor(colorA)) {
            vertice.derecho = nuevoVertice(elemento, -1, profundidad, color, jugada2);
            vertice.derecho.padre = vertice;
            valDer = -1;
          }
          else {
            vertice.derecho = nuevoVertice(elemento, 0, profundidad, color, jugada2);
            vertice.derecho.padre = vertice;
            valDer = hazArbol(elemento, profundidad + 1, !turno, jugada2, vertice.derecho);
          }
        }
      }

      if(color == this.colorA) {
        miniMax = Math.max(valIzq, valDer);
      } else {
        miniMax = Math.min(valIzq, valDer);
      }
      vertice.setValor(miniMax);

      return miniMax;
    }

    /**
     * Método que decide cual es el mejor hijo
     * de la raiz a partir del vértice que tenga el valor
     * más alto. O en caso de tener el mismo valor,
     * se decide por aquel que tenga menos altura para
     * poder ganar en menos pasos.
     *
     * @returns int[] estado del mejor tablero.
     */
    public int[] mejorJugada() {
      VerticeMM izq = (VerticeMM) raiz.izquierdo;
      VerticeMM der = (VerticeMM) raiz.derecho;
      if(!raiz.hayDerecho()) {
        return izq.getTablero();
      }
      else if(!raiz.hayIzquierdo()) {
        return der.getTablero();
      }
      if(izq.getValor() > der.getValor()) {
        return izq.getTablero();
      }
      else if(izq.getValor() > der.getValor()) {
        return der.getTablero();
      } else {
        if(izq.altura() <= der.altura()) {
          return izq.getTablero();
        }
        else {
          return der.getTablero();
        }
      }
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     *
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    //@Override
    public T pop() {
      return null;
    }

    @Override
    public void add(T elemento) {
      return;
    }

    @Override
    public boolean delete(T elemento) {
      return false;
    }
}
