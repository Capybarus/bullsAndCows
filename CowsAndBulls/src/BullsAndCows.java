import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args) {
        int bulls = 0, cows = 0, tried = 1, difficult = 0;
        System.out.println("Введите сложность игры от 3 - 5");
        Scanner in = new Scanner(System.in);
        try {
            difficult = Integer.parseInt(in.nextLine());
            if (difficult < 3 || difficult > 5)
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("Игра завершена");
            System.exit(0);
        }
        System.out.println("Выбранная сложность - " + difficult);

        int [] array = new int [difficult];
        int [] animals = new int [difficult];
        Random random = new Random();

        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt(10);
            for (int j = 0; j < i; j++)
            {
                if(array[i] == array[j])
                {
                    i--;
                    break;
                }
            }
        }

        while (true)
        {
            System.out.println("Введите " + difficult + "-значную неповторяющуюся комбинацию цифр");
            String attempt = in.nextLine();

            if (attempt.equals("сдаюсь")) {
                System.out.println("Попыток - " + tried  + " игра завершена");
                break;
            }

            String [] numbers = attempt.split("");

            try {
                for (int i = 0; i < numbers.length; i++) {
                    int number = Integer.parseInt(numbers[i]);
                    animals[i] = number;
                    if (i > difficult)
                    {
                        throw new Exception();
                    }
                }
                for (int i = 0; i < animals.length; i++) {
                    for (int j = animals.length - 1; j > i; j--) {
                        if (animals[i] == animals[j]) {
                            throw new Exception();
                        }
                    }
                }
            }
            catch (Exception myException)
            {
                System.out.println("Ошибка ввода");
                continue;
            }

            for (int i = 0; i < animals.length; i++) {
                if (array[i] == animals[i])
                {
                    bulls++;
                    continue;
                }
                for (int j = 0; j < array.length; j++) {
                    if (array[j] == animals[i])
                    {
                        cows++;
                        break;
                    }
                }
            }

            if (bulls == difficult)
            {
                System.out.println("Игра закончена, вы отгадали число, попыток - " + tried);
                break;
            }
            tried++;
            System.out.println("Быков - " + bulls + " Коров - " + cows);
            bulls = 0; cows = 0;
        }
    }
}
