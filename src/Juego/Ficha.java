package clases.src.Juego;

import clases.src.colors.Colors;

/**
 * Clase para las fichas del juego.
 * Hay 4 fichas, 2 de color rojo y 2 de color azul.
 */
public class Ficha{

   /** El color de la ficha.
    * 0 azul, 1 rojo y 2 vacío
    */
  private int color;

  /**
   * Crea una ficha de un color.
   *
   * @param color el entero que representa el color de la ficha.
   */
  public Ficha(int color){
    this.color=color;
  }

  /**
   * Getter der color de la ficha.
   *
   * @returns el entero que representa el color de la ficha.
   */
  public int getColor(){
    return this.color;
  }

  /**
   * Setter der color de la ficha.
   *
   * @param color el entero que representa el color del que será la ficha.
   */
  public void setColor(int color){
    this.color=color;
  }

  /**
   * Crea una representación to String de la ficha.
   *
   * @returns string con la representación de la ficha.
   */
  @Override
  public String toString(){
    if(this.color==0){
      return Colors.CYAN + Colors.HIGH_INTENSITY+ "\u25cf" + Colors.RESTORE;
    }else if(this.color==1){
      return Colors.RED + Colors.HIGH_INTENSITY+ "\u25cf" +  Colors.RESTORE;
    }
    return Colors.WHITE + Colors.HIGH_INTENSITY+ "\u25cf" +  Colors.RESTORE;
  }

}
