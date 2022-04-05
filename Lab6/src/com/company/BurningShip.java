package com.company;
import java.awt.geom.Rectangle2D;

//Этот класс является подклассом FractalGenerator.
public class BurningShip extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;
    /*
     метод позволяет генератору
     фракталов определить наиболее «интересную» область комплексной плоскости
     для конкретного фрактала.
     */
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -2.5;
        range.width = 4;
        range.height = 4;
    }
    /*
    Этот метод реализует итерационную функцию для фрактала Мандельброта.
    */
    public int numIterations(double x, double y)
    {
        int iteration = 0;
        double zreal = 0;
        double zimaginary = 0;

        while (iteration < MAX_ITERATIONS &&
                zreal * zreal + zimaginary * zimaginary < 4)
        {
            double zrealUp= zreal * zreal - zimaginary * zimaginary + x;
            double zimaginaryUp = 2 * Math.abs(zreal) * Math.abs(zimaginary) + y;
            zreal = zrealUp;
            zimaginary = zimaginaryUp;
            iteration += 1;
        }

        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }
        return iteration;
    }
    //Возврат названия фрактала
    public String toString() {
        return "Burning Ship";
    }
}