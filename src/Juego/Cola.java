package clases.src.Juego;

public class Cola<T> extends PushPop<T> {
    /**
     * Regresa una representación en cadena de la cola.
     *
     * @return una representación en cadena de la cola.
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "";
        String a = "";
        Nodo b = cabeza;
        while (b != null) {
            a += b.elemento + ",";
            b = b.siguiente;
        }
        return a;
    }

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override public void push(T elemento){
        if (elemento == null) {
            throw new IllegalArgumentException("");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = ultimo = nuevo;
            longi++;
            return;
        }
        ultimo.siguiente = nuevo;
        ultimo = nuevo;
        longi++;
    }

    /**
   * Regresa un clon de la estructura.
   *
   * @return un clon de la estructura.
   */
  public Cola<T> clone(){
      Cola<T> nueva = new Cola<T>();
      Cola<T> aux = new Cola<T>();
      if (this.isEmpty()) {
          return nueva;
      }
      Nodo aux2 = this.cabeza;
      while(aux2 != null){
          aux.push(aux2.elemento);
          aux2 = aux2.siguiente;
      }
      while(!aux.isEmpty()) {
          nueva.push(aux.pop());
      }
      return nueva;
  }
}
