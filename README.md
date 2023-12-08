# Juego del encerrado con implementación de un árbol binario minimax.

## Autores
    1. Eduardo Alfonso Reyes López
    2. Jonathan Bautista Parra

## Ejecución

En la carpeta que contiene src.

Compilar primero -> javac -d . src/colors/Colors.java
Compilar proyecto -> javac -d . src/Juego/*.java
Ejecutar proyecto -> java clases.src.Juego.Encerrado

## Descripción

El proyecto se compone de las siguientes clases principales:
Una clase ArbolJuego en donde se crea el árbol para usar el método de minimax, así para determinar la mejor jugada para la máquina.

Una clase Ficha que simula las fichas del juego, donde su atributo es el color Azul, rojo (o blanca en caso de que sea un lugar vacío).

Una clase Tablero, la cual simula los movimientos de las fichas e imprime una representación gráfica del juego.
La clase máquina, la cual simula a la máquina como el jugador oponente.
Y finalmente la clase Encerrado la cual simula la interfaz del juego en la consola.