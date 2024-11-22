// BUS MANAGEMENT SYSTEM JAVA



import java.util.*;

class Bus {
    String busNumber;
    String routeName;
    String inTime;
    String outTime;

    public Bus(String busNumber, String routeName, String inTime, String outTime) {
        this.busNumber = busNumber;
        this.routeName = routeName;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    @Override
    public String toString() {
        return "Bus Number: " + busNumber + ", Route Name: " + routeName + ", In Time: " + inTime + ", Out Time: " + outTime;
    }
}

class BusManagementSystem {
    List<Bus> busList = new ArrayList<>();

    // Add a new bus
    public void addBus(String number, String route, String inTime, String outTime) {
        Bus bus = new Bus(number, route, inTime, outTime);
        busList.add(bus);
    }

    // Update route for a specific bus by bus number
    public void updateBus(String number, String newRoute, String newInTime, String newOutTime) {
        for (Bus bus : busList) {
            if (bus.busNumber.equals(number)) {
                bus.routeName = newRoute != null ? newRoute : bus.routeName;
                bus.inTime = newInTime != null ? newInTime : bus.inTime;
                bus.outTime = newOutTime != null ? newOutTime : bus.outTime;
                System.out.println("Bus details updated successfully.");
                return;
            }
        }
        System.out.println("Bus with number " + number + " not found.");
    }

    // Delete a bus by number
    public void deleteBus(String number) {
        busList.removeIf(bus -> bus.busNumber.equals(number));
        System.out.println("Bus with number " + number + " has been deleted.");
    }

    // Display all buses
    public void displayAllBuses() {
        if (busList.isEmpty()) {
            System.out.println("No buses available.");
        } else {
            for (Bus bus : busList) {
                System.out.println(bus);
            }
        }
    }

    // Display sorted by inTime or outTime (ascending or descending)
    public void displayBusesByTime(String timeType, boolean ascending) {
        if (timeType.equalsIgnoreCase("in")) {
            busList.sort(ascending ? Comparator.comparing(bus -> ((Bus) bus).inTime)
                    : Comparator.comparing(bus -> ((Bus) bus).inTime).reversed());
        } else if (timeType.equalsIgnoreCase("out")) {
            busList.sort(ascending ? Comparator.comparing(bus -> ((Bus) bus).outTime)
                    : Comparator.comparing(bus -> ((Bus) bus).outTime).reversed());
        }
        displayAllBuses();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BusManagementSystem busSystem = new BusManagementSystem();

        // Loop to add 5 bus records from user input
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details for bus " + (i + 1) + ": ");
            System.out.print("Bus Number: ");
            String busNumber = scanner.nextLine();
            System.out.print("Route Name: ");
            String routeName = scanner.nextLine();
            System.out.print("In Time (HH:mm): ");
            String inTime = scanner.nextLine();
            System.out.print("Out Time (HH:mm): ");
            String outTime = scanner.nextLine();

            busSystem.addBus(busNumber, routeName, inTime, outTime);
        }

        // Display the buses initially sorted by out time (ascending)
        System.out.println("\nBuses sorted by out time (ascending): ");
        busSystem.displayBusesByTime("out", true);

        // Loop to allow user to perform actions like update, delete, etc.
        while (true) {
            System.out.println("\nChoose an action: ");
            System.out.println("1. Update a bus");
            System.out.println("2. Delete a bus");
            System.out.println("3. Display buses sorted by time");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // Update bus
                    System.out.print("Enter bus number to update: ");
                    String number = scanner.nextLine();
                    System.out.print("Enter new route name (or press Enter to skip): ");
                    String newRoute = scanner.nextLine();
                    System.out.print("Enter new in time (or press Enter to skip): ");
                    String newInTime = scanner.nextLine();
                    System.out.print("Enter new out time (or press Enter to skip): ");
                    String newOutTime = scanner.nextLine();
                    busSystem.updateBus(number, newRoute.isEmpty() ? null : newRoute, 
                                        newInTime.isEmpty() ? null : newInTime, 
                                        newOutTime.isEmpty() ? null : newOutTime);
                    break;
                case 2:
                    // Delete bus
                    System.out.print("Enter bus number to delete: ");
                    String deleteNumber = scanner.nextLine();
                    busSystem.deleteBus(deleteNumber);
                    break;
                case 3:
                    // Display buses sorted by in/out time
                    System.out.print("Sort by 'in' time or 'out' time? ");
                    String timeType = scanner.nextLine();
                    System.out.print("Sort ascending (true) or descending (false)? ");
                    boolean ascending = Boolean.parseBoolean(scanner.nextLine());
                    busSystem.displayBusesByTime(timeType, ascending);
                    break;
                case 4:
                    // Exit
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
