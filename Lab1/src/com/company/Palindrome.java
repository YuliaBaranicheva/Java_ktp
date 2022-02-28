package com.company;
//Класс, проверяющий является ли строка палиндромом
public class Palindrome {
    public static void main(String[] args) {
        for (int i=0; i<args.length; i++) {
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.println("Палиндром:" + s);
            }
            else {
                System.out.println("Не палиндром:" + s);
            }
        }
    }
    //Метод, позволяющий изменить символы в строке
    public static String reverseString(String s) {
        String rev = "";
        for (int i = s.length()-1; i>=0; i--) {
            rev += s.charAt(i);
        }
        return rev;
    }
    //Метод, переворачивающий слово и сравнивающий его с изначальными данными
    public static boolean isPalindrome(String s) {
        if(s.equals(reverseString(s))) {
            return true;
        }
        else {
            return false;
        }
    }
}
