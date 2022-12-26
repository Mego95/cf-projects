package gr.aueb.cf.projects;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TheatreBookingApp {
    final static int COLUMNS = 12;
    final static int ROWS = 30;
    final static boolean[][] seats = new boolean[COLUMNS][ROWS];
    final static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        boolean quit = false;
        String input;

        do {
            printMenu();
            input = in.nextLine().trim();
            try {
                choice = parseChoice(input);
                if (choice == 3) quit = true;
                handleChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Please give a choice between 1-3");
            }
        } while (!quit);
    }

    public static void handleChoice(int choice) {
        String input = "";
        String[] seat;
        int[] seatPosition;

        try {
            switch (choice) {
                case 1:
                    System.out.println("Please give seat to book:");
                    input = getInput();
                    seat = inputToSeat(input);
                    seatPosition = getSeatPosition(seat);
                    if (isBooked(seatPosition)) {
                        System.out.println("Seat already booked");
                        break;
                    } else {
                        book(seatPosition);
                        System.out.println("Seat booked");
                        break;
                    }
                case 2:
                    System.out.println("Please give seat to cancel:");
                    input = getInput();
                    seat = inputToSeat(input);
                    seatPosition = getSeatPosition(seat);
                    if (!isBooked(seatPosition)) {
                        System.out.println("Seat is  not booked");
                        break;
                    } else {
                        cancel(seatPosition);
                        System.out.println("Booking cancelled");
                        break;
                    }
                case 3:
                    System.out.println("Thank you for using theatre booking app");
                    break;
                default:
                    System.out.println("Please give a choice between 1-3");
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Please give a valid seat");
        }
    }

    public static void printMenu() {
        System.out.println("Please select one of the following:");
        System.out.println("1. Book seat");
        System.out.println("2. Cancel booking");
        System.out.println("3. Quit");
        System.out.println();
    }

    public static int parseChoice(String input) {
        int choice = 0;

        try {
            choice = Integer.parseInt(input);
            return choice;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String getInput() {
        return in.nextLine().trim().toUpperCase();
    }

    public static String[] inputToSeat(String input) {
        String[] seat = new String[2];

        String column = input.substring(0, 1);
        String row = input.substring(1, input.length());

        seat[0] = column;
        seat[1] = row;

        return seat;
    }

    public static int[] getSeatPosition(String[] seat) {
        int[] seatPosition = {-1, -1};

        try {
            char column = seat[0].charAt(0);
            seatPosition[0] = (int) column - 65;
            if ((seatPosition[0] > COLUMNS - 1) || (seatPosition[0] < 0)) throw new ArrayIndexOutOfBoundsException();

            seatPosition[1] = Integer.parseInt(seat[1]);
            if ((seatPosition[1] > ROWS - 1) || (seatPosition[1] < 0)) throw new ArrayIndexOutOfBoundsException();

            return seatPosition;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static boolean isBooked(int[] seatPosition) {
        return seats[seatPosition[0]][seatPosition[1]];
    }

    /*
     * book and cancel services
     */

    public static void book(int[] seatPosition) {
        if (!isBooked(seatPosition)) {
            seats[seatPosition[0]][seatPosition[1]] = true;
        }
    }

    public static void cancel(int[] seatPosition) {
        if (isBooked(seatPosition)) {
            seats[seatPosition[0]][seatPosition[1]] = false;
        }
    }
}
