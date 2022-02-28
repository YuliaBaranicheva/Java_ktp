package com.company;

//Трехмерный класс точки в евклидовом пространстве
public class Point3d {
    private double xCoord; //Координаты Х
    private double yCoord; //Координаты Y
    private double zCoord; //Координаты Z
    //Конструктор инициализации
    public Point3d (double x, double y, double z){
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    //Конструктор по умолчанию
    public Point3d(){
        //Вызов конструктора с двумя параметрами и определение источника
        this(0.0, 0.0, 0.0);
    }
    //Возвращение координаты X
    public double getX(){
        return xCoord;
    }
    //Возвращение координаты Y
    public double getY(){
        return yCoord;
    }
    //Возвращение координаты Z
    public double getZ(){
        return zCoord;
    }
    //Установка значения координаты Х
    public void setX(double value){
        xCoord = value;
    }
    //Установка значения координаты Y
    public void setY(double value){
        yCoord = value;
    }
    //Установка значения координаты Z
    public void setZ(double value){
        zCoord = value;
    }
    //Вычисляем расстояние между двумя точками
    public double distanceTo (Point3d p){
        double x2 = p.getX();
        double y2 = p.getY();
        double z2 = p.getZ();

        double x = this.xCoord;
        double y = this.yCoord;
        double z = this.zCoord;

        double dist = Math.sqrt(Math.pow((x2-x), 2) + Math.pow((y2-y), 2) + Math.pow((z2-z), 2));
        return dist;
    }
    // Метод проверки значений на равенство
    public static boolean equalsPoints (Point3d p, Point3d p2){
        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();

        double x2 = p2.getX();
        double y2 = p2.getY();
        double z2 = p2.getZ();

        if ((x == x2) && (y == y2) && (z == z2))
            return true;
        else return false;

    }
}