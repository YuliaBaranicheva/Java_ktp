package com.company;

public class Point2d {
    private double xCoord; //Координата Х
    private double yCoord; //Координата Y

    //Конструктор инициализации
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    //Конструктор по умолчанию
    public Point2d() {
        //Вызов конструктора с двумя параметрами и определение источника
        this(0.0, 0.0);
    }

    //Возвращение координаты X
    public double getX() {
        return xCoord;
    }

    //Возвращение координаты Y
    public double getY() {
        return yCoord;
    }

    //Отдаем значение:
    //Установка значения координаты Х
    public void setX(double val) {
        xCoord = val;
    }

    //Установка значения координаты Y
    public void setY(double val) {
        yCoord = val;
    }

    //Расстояние между двумя точками
    public double distanceTO(Point2d p) {
        double x2 = p.getX();
        double y2 = p.getY();

        double x = this.xCoord;
        double y = this.yCoord;

        double dist = Math.sqrt(Math.pow((x2-x), 2) + Math.pow((y2-y), 2));
        return dist;
    }


    //Метод проверки значений на равенство
    public static boolean Points(Point2d p, Point2d p2) {
        double x = p.getX();
        double y = p.getY();

        double x2 = p2.getX();
        double y2 = p2.getY();

        if ((x == x2) && (y == y2))
            return true;
        else
            return false;
    }
}



