package cinema;

public class Theater {

    private final char[][] seatingChart;
    private final static int SMALL_THEATER = 60;
    private final static int CHEAP_SEATS = 8;
    private final static int EXPENSIVE_SEATS = 10;

    public Theater(int rows, int seatsPerRow) {
        this.seatingChart = new char[rows][seatsPerRow];
        initializeSeatingChart();
    }

     public void initializeSeatingChart() {
        // set all seats to 'S'
        for (int i = 0; i < seatingChart.length; i++) {
            for (int j = 0; j < seatingChart[i].length; j++) {
                seatingChart[i][j] = 'S';
            }
        }
    }

    public void printSeatingChart() {
        // print the seating chart
        System.out.println("\nCinema:");
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
        System.out.println();
    }

    public void bookSeat(int rowNum, int seatNum) {
        seatingChart[rowNum - 1][seatNum - 1] = 'B';
    }

    public int getPrice(int rowNum) {
        int rows = seatingChart.length;
        int seatsPerRow = seatingChart[0].length;
        int totalSeats = rows * seatsPerRow;
        if (totalSeats <= SMALL_THEATER) {
            return EXPENSIVE_SEATS;
        } else {
            int expensiveRows = rows / 2;
            if (rowNum <= expensiveRows) {
                return EXPENSIVE_SEATS;
            } else {
                return CHEAP_SEATS;
            }
        }
    }
}
