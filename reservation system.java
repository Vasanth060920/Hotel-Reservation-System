import java.util.ArrayList;
import java.util.Scanner;

class Room {
    String type;
    int number;
    double price;
    boolean isAvailable;

    public Room(String type, int number, double price) {
        this.type = type;
        this.number = number;
        this.price = price;
        this.isAvailable = true;
    }

    public void display() {
        System.out.println("Type: " + type + ", Number: " + number + ", Price: $" + price + ", Available: " + isAvailable);
    }
}

class Reservation {
    Room room;
    String guestName;
    int numberOfDays;

    public Reservation(Room room, String guestName, int numberOfDays) {
        this.room = room;
        this.guestName = guestName;
        this.numberOfDays = numberOfDays;
        room.isAvailable = false; // Mark room as booked
    }

    public double calculateTotalCost() {
        return room.price * numberOfDays;
    }

    public void display() {
        System.out.println("Guest: " + guestName + ", Room Number: " + room.number + ", Days: " + numberOfDays + ", Total Cost: $" + calculateTotalCost());
    }
}

public class HotelReservationSystem {
    ArrayList<Room> rooms;
    ArrayList<Reservation> reservations;
    Scanner scanner;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        scanner = new Scanner(System.in);

        // Initialize some rooms
        rooms.add(new Room("Single", 101, 100.0));
        rooms.add(new Room("Single", 102, 100.0));
        rooms.add(new Room("Double", 201, 150.0));
        rooms.add(new Room("Double", 202, 150.0));
        rooms.add(new Room("Suite", 301, 250.0));
    }

    public void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                room.display();
            }
        }
    }

    public void makeReservation() {
        displayAvailableRooms();

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.number == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room number or room is not available.");
            return;
        }

        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter number of days: ");
        int numberOfDays = scanner.nextInt();
        scanner.nextLine();

        Reservation reservation = new Reservation(selectedRoom, guestName, numberOfDays);
        reservations.add(reservation);

        System.out.println("Reservation successful!");
        reservation.display();
    }

    public void viewReservations() {
        System.out.println("\nCurrent Reservations:");
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet.");
        } else {
            for (Reservation reservation : reservations) {
                reservation.display();
            }
        }
    }

    public void cancelReservation() {
      System.out.print("Enter guest name to cancel reservation: ");
      String guestName = scanner.nextLine();

      Reservation reservationToRemove = null;
      for(Reservation reservation : reservations) {
        if(reservation.guestName.equals(guestName)) {
          reservationToRemove = reservation;
          break;
        }
      }

      if(reservationToRemove != null) {
        reservationToRemove.room.isAvailable = true;
        reservations.remove(reservationToRemove);
        System.out.println("Reservation for " + guestName + " cancelled.");
      } else {
        System.out.println("No reservation found for " + guestName + ".");
      }
    }



    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();

        int choice;
        do {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Display Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Cancel Reservation");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            Scanner scanner = new Scanner(System.in); // Create scanner inside loop
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.displayAvailableRooms();
                    break;
                case 2:
                    system.makeReservation();
                    break;
                case 3:
                    system.viewReservations();
                    break;
                case 4:
                    system.cancelReservation();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

    }
}
