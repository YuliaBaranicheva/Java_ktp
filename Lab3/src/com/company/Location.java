package com.company;

import java.util.Objects;

/** Данный класс представляет собой определённое местоположение на 2D-карте.
 * Координаты - это целочисленные значения **/

public class Location {
    /** X координата этого местоположения **/
    public int xCoord;

    /** Y координата этого местоположения **/
    public int yCoord;


    /** Создание нового местоположения с указанными целочисленными координатами **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Создание нового местоположения с координатами (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    /** Тут сравниваем это местоположение с другим **/
    public boolean equals(Object obj) {
        // Представляет ли из себя obj местоположение
        if (obj instanceof Location) {
            // Тут приводится другой объект к типу местоположения, затем сравнить. Вернуть true в случае, если равно
            Location other = (Location) obj;
            if (xCoord == other.xCoord && yCoord == other.yCoord) {
                return true;
            }
        }
        // В случае если не равны, то вернуть false
        return false;
    }

    /** Предоставление хэш-кода для каждого местоположения **/
    public int hashCode() {
        int result = 17; // Некое первоначальное значение
        // Тут используется другое простое значение для объединения
        result = 37 * result + xCoord;
        result = 37 * result + yCoord;
        return result;
    }
}




