package com.company;
import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {
        Point3d p = null, p1 = null, p2 = null;

        char cors[] = {'x', 'y', 'z'};
        int num_cors[] = new int[3];

        Scanner inp = new Scanner(System.in);
        System.out.println("Введите координаты трёх точек:");

        for(int i=1; i<4; i++){
            for (int j=0; j<3; j++) {
                System.out.println(i + ":" + cors[j]);
                num_cors[j] = inp.nextInt();
            }
            switch(i){
                case 1:
                    p = new Point3d(num_cors[0],num_cors[1],num_cors[2]);
                    break;
                case 2:
                    p1 = new Point3d(num_cors[0],num_cors[1],num_cors[2]);
                    break;
                case 3:
                    p2 = new Point3d(num_cors[0],num_cors[1],num_cors[2]);
                    break;
            }
        }
        if (Point3d.equalsPoints(p, p1) || Point3d.equalsPoints(p2, p1) || Point3d.equalsPoints(p, p2)){
            System.out.println("Вы ввели некорректные координаты");
        }
        else {
            System.out.printf("Площадь треугольника = " +  computeArea(p, p1, p2));
        }
    }

    //Для вычесления площади треугольника, образованного тремя точками
    public static double computeArea(Point3d p, Point3d p2, Point3d p3){

        //вычисляем стороны треугольника
        double a = p.distanceTo(p2);
        double b = p2.distanceTo(p3);
        double c = p3.distanceTo(p);

        //формула полупериметра
        double pper = (a + b + c) / 2.;
        //формула площаль Герона
        double square = Math.sqrt((pper * (pper - a) * (pper - b) * (pper - c)));

        return square;
    }

}