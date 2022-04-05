package com.company;
import java.awt.geom.Rectangle2D;

//Этот класс является подклассом FractalGenerator. Он используется для вычисления Фрактала Мандельброта
public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    /* метод позволяет генератору фракталов
    определить наиболее «интересную» область комплексной
     плоскости для конкретного фрактала. */
    public void getInitialRange (Rectangle2D.Double range){
        range.x=-2;
        range.y=-1.5;
        range.width=3;
        range.height=3;
    }
    /* Метод реализует итерационную функцию для фрактала Мандельброта. */
    public int numIterations(double x, double y)
    {
        //Начать итерацию с нуля
        int iteration = 0;
        //Инициализация zreal и zimaginary
        double zreal = 0;
        double zimaginary = 0;
        /*
        Вычислить Zn = Zn-1 ^ 2 + c, где значения представляют собой комплексные числа, представленные
        по zreal и zimaginary, Z0 = 0, а c - особая точка в
        фрактале, который мы отображаем (заданный x и y). Это повторяется
        до Z ^ 2> 4 (абсолютное значение Z больше 2) или максимум
        достигнуто количество итераций.
        */
        while (iteration < MAX_ITERATIONS && (zreal * zreal) + (zimaginary * zimaginary) < 4)
        {
            double zrealUp = zreal * zreal - zimaginary * zimaginary + x;
            double zimaginaryUp = 2 * zreal * zimaginary + y;
            zreal = zrealUp;
            zimaginary = zimaginaryUp;
            iteration++;
        }

        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }
        return iteration;
    }
    //Возврат названия фрактала
    public String toString() {
        return "Mandelbrot";
    }
}
