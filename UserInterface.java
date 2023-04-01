package cinema;

import java.sql.SQLOutput;
import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;
    private Theater theater;
    private int currentIncome;

    private final String MENU = ("""
            1. Show the seats
            2. Buy a ticket
            3. Statistics
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
                case 3 -> showStatistics();
                case 0 -> {
                    return;
                }
                default -> System.out.println("\nINVALID SELECTION");
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
        while (true) {
            System.out.println("\nEnter a row number:");
            int rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNum = scanner.nextInt();

            if (rowNum > theater.numRows() || seatNum > theater.numSeatsPerRow()) {
                System.out.println("\nWrong input!");
                continue;
            }
            if (!theater.isSeatAvailable(rowNum, seatNum)) {
                System.out.println("\nThat ticket has already been purchased!");
                continue;
            }

            theater.bookSeat(rowNum, seatNum);
            System.out.println("\nTicket price: $" + theater.getPrice(rowNum));
            currentIncome += theater.getPrice(rowNum);
            System.out.println();
            break;
        }
    }

    public void showStatistics() {
        char[][] theaterSeats = theater.getSeatingChart();
        int totalSeats = theaterSeats.length * theaterSeats[0].length;
        int purchasedTickets = 0;
        for (char[] theaterSeat : theaterSeats) {
            for (char c : theaterSeat) {
                if (c == 'B') {
                    purchasedTickets++;
                }
            }
        }
        System.out.printf("\nNumber of purchased tickets: %d", purchasedTickets);
        System.out.printf("\nPercentage: %.2f%%", 100.00 * purchasedTickets / totalSeats);
        System.out.printf("\nCurrent income: $%d", currentIncome);
        System.out.printf("\nTotal income: $%d\n\n", theater.totalIncome());
    }
}
