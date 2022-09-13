package cinema;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cinema {
    private static int income = 0;

    public static void show(int seats, String[][] cinema) {
        System.out.println("\nCinema: ");
        System.out.print("  ");
        for (int i = 0; i < seats; i++) {
            System.out.print(i + 1 + " ");
        }

        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static String[][] createCinema(int rows, int seats) {
        String[][] cinema = new String[rows][seats];
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = "S";
            }
        }
        return cinema;

    }

    public static int buyTheTicket(Scanner scanner, int rows, int seats, String[][] cinema) {

        boolean flag = false;
        System.out.println("\nEnter a row number:");
        int rowNum = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int seatNum = scanner.nextInt();


        while ((rowNum > rows && seatNum < seats) || (rowNum < rows && seatNum > seats)) {
            System.out.println("Wrong input!");

            System.out.println("\nEnter a row number:");
            rowNum = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();

        }


        while (cinema[rowNum - 1][seatNum - 1].equals("B")) {
            System.out.println("\nThat ticket has already been purchased");
            System.out.println("\nEnter a row number:");
            rowNum = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();

            while ((rowNum > rows && seatNum < seats) || (rowNum < rows && seatNum > seats)) {
                System.out.println("Wrong input!");

                System.out.println("\nEnter a row number:");
                rowNum = scanner.nextInt();

                System.out.println("Enter a seat number in that row:");
                seatNum = scanner.nextInt();

            }

        }

            if (rows * seats < 60) {
                income += 10;
                System.out.println("\nTicket price: $10");
            } else if (rowNum <= rows / 2) {
                System.out.println("\nTicket price: $10");
                income += 10;
            } else {
                income += 8;
                System.out.println("\nTicket price: $8");
            }
            cinema[rowNum - 1][seatNum - 1] = "B";


        return income;
    }

    public static void statistic(String[][] cinema, int rows, int seats, int income) {
        int count = 0;
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (cinema[i][j].equals("B")) {
                    count++;
                }
            }
        }

        double percentage = (double) (count) / (seats * rows);
        System.out.println("Number of purchased tickets: " + count);
        System.out.println("Percentage: " + new DecimalFormat("0.00%").format(percentage));
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + totalInCome(rows, seats));


    }


    public static int totalInCome(int rows, int seats) {
        int total = rows * seats;

        if (total < 60) {
            return total * 10;
        } else {
            int half = rows / 2;
            int result = ((half * seats) * 10) + ((rows - half) * seats * 8);

            return result;
        }

    }

    public static void display() {
        System.out.println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0.Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int income = 0;

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        String[][] cinema = createCinema(rows, seats);

        boolean flag = false;
        while (!flag) {

            display();
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    show(seats, cinema);
                    break;
                case 2:
                    income = buyTheTicket(scanner, rows, seats, cinema);
                    break;
                case 3:
                    statistic(cinema, rows, seats, income);
                    break;
                case 0:
                    flag = true;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
}