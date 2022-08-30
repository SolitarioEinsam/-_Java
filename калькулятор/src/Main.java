import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;
import java.util.function.IntPredicate;

public class Main
{
    public static void main(String[] args) {
        {
            Scanner in = new Scanner(System.in);
            DataVerification datver = new DataVerification();
            ArithmeticAction ans = new ArithmeticAction();
            String expression = in.nextLine();
            String[] variables = expression.split("\\s");
            if (variables.length == 1)
                {
                    System.out.print("throws Exception //т.к. строка не является математической операцией");
                }
            else
                {
                    if (variables.length == 3)
                        {
                            String str1 = variables[0];
                            String str2 = variables[1];
                            String str3 = variables[2];
                            int b = datver.check(str1, str3);
                            if (b == 3) {
                                System.out.print("throws Exception //т.к. используются одновременно разные системы счисления");
                            } else {
                                String finish = ans.work(str1, str2, str3, b);
                                if (finish == null) {
                                    System.out.print("throws Exception //т.к. в римской системе нет отрицательных чисел");
                                } else {
                                    System.out.print(finish);
                                }
                            }
                        }
                    else
                        {
                            System.out.print("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                        }
                }
        }
    }
}
//ПРОВЕРКА ВВЕДЁНЫХ ДАННЫХ
class DataVerification
{
    int check(String str1, String str3) {
        int[] arab = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] rome = {"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        int exit = 1;
        // ПРОВЕРКА НА СООТВЕТСВИЕ АРАБСКИХ ЦИФР
        boolean arab_1 = true;
        try
            {
                int a = Integer.parseInt(str1);
            }
        catch (NumberFormatException e)
            {
                arab_1 = false;
            }
        try
            {
                int a = Integer.parseInt(str3);
            }
        catch (NumberFormatException e)
            {
                arab_1 = false;
            }
        //ПРОВЕРКА НА РИМСКИЕ ЦИФРЫ
        boolean rome_1 ;
        if (arab_1 == false){
            int checkRome1 = 0;
            int checkRome2 = 0;
            for (int i = 0; i <10; i++)
            {
                if (str1.equals(rome[i]))
                    {
                        checkRome1 = 1;
                    }
            }
            for (int i = 0; i <10; i ++)
            {
                if (str3.equals(rome[i]))
                    {
                        checkRome2 = 1;
                    }
            }
            if (checkRome1 == 1 && checkRome2 ==1)
                {
                    rome_1 = true;
                }
            else
                {
                    rome_1 = false;
                }
            if (rome_1 == true)
                {
                    exit = 2;

                }
            else
                {
                    exit = 3;
                }
        }

        //КОНЕЧНЫЙ ВЫВОД

        return exit;

        }

}
//АРИФМЕТИЧЕСКИЕ ДЕЙСТВИЯ
class ArithmeticAction
{
    String work (String str1, String str2, String str3, int arg1)
    {
        //ВВЕДЕНИЕ БИБЛИОТЕКИ РИМСКИХ ЦИФР ОТ 1 ДО 100
        String [] rome2 =                       {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                                                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                                                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                                                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                                                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                                                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                                                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                                                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                                                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                                                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        //РЕШЕНИЕ ЕСЛИ ЦИФРЫ АРАБСКИЕ
        String answer = null;
        if (arg1 == 1)
            {
                int a = Integer.parseInt(str1);
                int b = Integer.parseInt(str3);
                if (str2.equals("+"))
                    {
                        answer = "" + (a + b);
                    }
                if (str2.equals("-"))
                    {
                        answer = "" + (a - b);
                    }
                if (str2.equals("*"))
                    {
                        answer = "" + (a * b);
                    }
                if (str2.equals("/"))
                    {
                        answer = "" + (a / b);
                    }
            }
        //РЕШЕНИЕ ЕСЛИ ЦИФРЫ РИМСКИЕ
        int answerRome = 0;
        if (arg1 == 2)
            {
                int count1 = 0;
                int count2 = 0;
                for (int i = 0; i<99; i++)
                    {
                        if(str1.equals(rome2[i]))
                            {
                                count1 = i + 1;
                                break;
                            }
                    }
                for (int i = 0; i<99; i++)
                    {
                        if(str3.equals(rome2[i]))
                            {
                                count2 = i + 1;
                                break;
                            }
                    }
                if (str2.equals("+"))
                    {
                        answerRome = (count1 + count2);
                    }
                if (str2.equals("-"))
                    {
                        answerRome = (count1 - count2);
                    }
                if (str2.equals("*"))
                    {
                        answerRome = (count1 * count2);
                    }
                if (str2.equals("/"))
                    {
                        answerRome = (Math.round(count1 / count2));
                    }

            }
        //ВЫВЕДЕНИЕ ОБЩЕГО РЕЗУЛЬТАТА МОДУЛЯ
        String answerFinish = null;
        if (arg1 == 1)
            {
                answerFinish = "" + answer;
            }
        if (arg1 == 2 && answerRome >= 0)
            {
                answerFinish = rome2[answerRome - 1];
            }
        //ВЫВОД РЕЗУЛЬТАТА
        return answerFinish;

    }
}


