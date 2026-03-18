import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        List<DataPoint> data = DataLoader.loadData("C:/Users/konra/Desktop/SZKOŁA/Semestr 4/NAI/projekt1/iris.txt");

        List<DataPoint> train = new ArrayList<>();
        List<DataPoint> test = new ArrayList<>();

        DataLoader.splitData(data, train, test, 0.6);

        Scanner sc = new Scanner(System.in);

        System.out.print("Podaj k: ");
        int k = sc.nextInt();

        while (true) {
            System.out.println("\n1 - Skutecznosc modelu");
            System.out.println("2 - Sklasyfikuj przypadek");
            System.out.println("3 - Wyjście");

            int choice = sc.nextInt();

            if (choice == 1) {
                double acc = Klasyfikator.evaluate(train, test, k);
                System.out.printf("Accuracy: %.2f%%\n", acc);
            }
            else if (choice == 2) {
                double[] input = new double[4];

                System.out.println("Podaj 4 cechy (długosc kielicha , szerokosc kielicha , dlugosc płatka , szerokosc platka) : ");
                for (int i = 0; i < 4; i++) {
                    input[i] = sc.nextDouble();
                }

                String result = Klasyfikator.classify(train, input, k);
                System.out.println("Klasa: " + result);
            }
            else if (choice == 3) {
                break;
            }
        }

        sc.close();
    }
}