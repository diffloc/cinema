package cinema;

import java.util.Scanner;

public class Cinema {

    final static int SMALL_THEATER = 60;
    final static int CHEAP_SEATS = 8;
    final static int EXPENSIVE_SEATS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        char[][] seatingChart = new char[rows][seatsPerRow]; // 2D array to store the seat chart

        initializeSeatingChart(seatingChart); // set all the seats to 'S'

        printSeatingChart(seatingChart); // print the seating chart

        // calcIncome(seatingChart);
    }

    public static void initializeSeatingChart(char[][] seatingChart) {
        // set all seats to 'S'
        for (int i = 0; i < seatingChart.length; i++) {
            for (int j = 0; j < seatingChart[i].length; j++) {
                seatingChart[i][j] = 'S';
            }
        }
    }

    public static void printSeatingChart(char[][] seatingChart) {
        // print the seating chart
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int j = 1; j <= seatingChart[0].length; j++) {
            System.out.print(j + " ");
        }
        System.out.println();

        for (int i = 0; i < seatingChart.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < seatingChart[i].length; j++) {
                System.out.print(seatingChart[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void calcIncome(char[][] seatingChart){
        int rows = seatingChart.length;
        int seatsPerRow = seatingChart[0].length;
        int totalSeats = rows * seatsPerRow;
        int totalIncome = 0;

        // count the number of available seats in the seating chart
        int availableSeats = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (seatingChart[i][j] == 'S') {
                    availableSeats++;
                }
            }
        }

        // calculate the total income based on the number of available seats
        if (totalSeats <= SMALL_THEATER) {
            totalIncome = availableSeats * EXPENSIVE_SEATS;
        } else {
            int expensiveRows = rows / 2;
            int cheapRows = rows - expensiveRows;
            totalIncome = expensiveRows * seatsPerRow * EXPENSIVE_SEATS
                    + cheapRows * seatsPerRow * CHEAP_SEATS;
        }

        System.out.printf("Total income:\n$%d", totalIncome);
    }

}