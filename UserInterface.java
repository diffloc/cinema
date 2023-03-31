package cinema;

import java.util.Scanner;

public class UserInterface {

   private final Scanner scanner;
   private Theater theater;

   private final String MENU = ("""
           1. Show the seats
           2. Buy a ticket
           0. Exit
           """);

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        initializeTheater();
        while (true) {
            System.out.println(MENU);
            int selection = scanner.nextInt();
            switch (selection) {
                case 1 -> theater.printSeatingChart();
                case 2 -> buySeat();
                case 0 -> { return; }
                default -> System.out.println("INVALID SELECTION");
            }
        }
    }

    public void initializeTheater() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        theater = new Theater(rows, seatsPerRow);
        System.out.println();
    }

    public void buySeat() {
        System.out.println("\nEnter a row number:");
        int rowNum = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNum = scanner.nextInt();
        theater.bookSeat(rowNum, seatNum);
        System.out.println("\nTicket price: $" + theater.getPrice(rowNum));
        System.out.println();
    }
}
