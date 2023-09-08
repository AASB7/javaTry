import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class Flight {
    private String flightno, flight_code, source, destination, fdate, arrival, departure, price, favail, pid, pname, pnum, paddr, fclass, fseat;

    public static void main(String[] args) {
        Flight f = new Flight();
        f.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearConsole();
            System.out.println("\n\t******************************************");
            System.out.println("\t\tWELCOME TO YEMEN AIRWAYS");
            System.out.println("\t******************************************\n");

            System.out.println("\t\t1. Admin");
            System.out.println("\t\t2. User");
            System.out.println("\t\t3. Exit\n");
            System.out.print("\tEnter the choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    alogin();
                    break;
                case 2:
                    ulogin();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\tWrong Choice!!!");
                    break;
            }
        }
    }

    public void alogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tEnter admin password: ");
        String password = scanner.nextLine();

        if (password.equals("admin")) {
            admin();
        } else {
            System.out.println("\n\n\t\tERROR: Wrong Password\n");
            start(); // Restart the program
        }
    }

    public void admin() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearConsole();
            System.out.println("\n\n\t      WELCOME  ADMIN");
            System.out.println("\t##########################################");
            System.out.println("\t\tYEMEN AIRWAYS RESERVATION SYSTEM");
            System.out.println("\t##########################################\n");

            System.out.println("\t1. Add Flight");
            System.out.println("\t2. Delete Flight");
            System.out.println("\t3. Change Flight Details");
            System.out.println("\t4. Display Flight Details");
            System.out.println("\t5. Search Flight");
            System.out.println("\t6. Log out");
            System.out.println("\t7. Exit\n");

            System.out.print("\tEnter the choice: ");
            int achoice = scanner.nextInt();
            switch (achoice) {
                case 1:
                    do {
                        addFlight();
                        System.out.print("\n\tDo you want to add another flight details?(Y/N) : ");
                    } while (scanner.next().equalsIgnoreCase("Y"));
                    break;
                case 2:
                    delFlight();
                    break;
                case 3:
                    modFlight();
                    break;
                case 4:
                    adisplayFlight();
                    break;
                case 5:
                    searchFlight();
                    break;
                case 6:
                    start(); // Return to the main menu
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\tWrong Choice!!!\n");
            }
        }
    }
    public void ulogin() {
        Scanner scanner = new Scanner(System.in);
        String pass_reg = "";
        String name_reg = "";
        String pass = "";
        String name = "";

        System.out.println("\n\t\t>>>>>>>>>>>>>>>>>>>>||  WELCOME TO YEMEN AIRWAYS RESERVATION SYSTEM  ||<<<<<<<<<<<<<<<<<<<<\n");
        System.out.println("\n\t\t\t\t1. Register");
        System.out.println("\t\t\t\t2. Login");

        int selection;
        System.out.print("\n\tEnter your choice: ");
        selection = scanner.nextInt();

        if (selection == 1) {
            System.out.println("\n\t>>>>>>>>>>>>>>>>>>>>||  REGISTRATION ||<<<<<<<<<<<<<<<<<<<<\n");
            System.out.print("\n\n\t\tEnter Your Name      : ");
            name_reg = scanner.next();
            System.out.print("\n\t\tEnter Your Password  : ");
            pass_reg = scanner.next();
            try {
                FileWriter fw = new FileWriter("ULogin.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(" " + name_reg + " " + pass_reg + "\n");
                bw.close();
                System.out.println("\n\t\tRegistration Successful!!!!!");
                System.in.read();
                ulogin();
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else if (selection == 2) {
            System.out.println("\n\t>>>>>>>>>>>>>>>>>>>>||  LOGIN ||<<<<<<<<<<<<<<<<<<<<\n");
            System.out.print("\n\n\t\tEnter Your Name      : ");
            name = scanner.next();
            System.out.print("\n\t\tEnter Your Password  : ");
            pass = scanner.next();

            try {
                File file = new File("ULogin.txt");
                Scanner fileScanner = new Scanner(file);
                boolean found = false;

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(" ");
                    String file_name = parts[0];
                    String file_pass = parts[1];

                    if (name.equals(file_name) && pass.equals(file_pass)) {
                        found = true;
                        break;
                    }
                }
                fileScanner.close();

                if (found) {
                    System.out.print("\n\n\n\t\t\t\t| Verifying USER |\n\t\t\t\t");
                    for (int a = 1; a < 8; a++) {
                        Thread.sleep(500);
                        System.out.print("...");
                    }
                    System.out.println("\n\n\t\t\tAccess Granted..!\n");
                    System.in.read();
                    user();
                } else {
                    System.out.print("\n\n\n\t\t\t\t| Verifying USER |\n\t\t\t\t");
                    for (int a = 1; a < 8; a++) {
                        Thread.sleep(500);
                        System.out.print("...");
                    }
                    System.out.println("\n\n\t\t\tAccess Aborted...!\n");
                    System.in.read();
                    ulogin();
                }
            } catch (FileNotFoundException | InterruptedException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void user() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearConsole();
            System.out.println("\n\n\t      WELCOME  USER");
            System.out.println("\t##########################################");
            System.out.println("\t\tYEMEN AIRWAYS RESERVATION SYSTEM");
            System.out.println("\t##########################################\n");

            System.out.println("\t1. View Flight");
            System.out.println("\t2. Flight Reservation");
            System.out.println("\t3. View Ticket");
            System.out.println("\t4. Cancel Ticket");
            System.out.println("\t5. Log out");
            System.out.println("\t6. Exit\n");

            System.out.print("\tEnter the choice: ");
            int uchoice = scanner.nextInt();
            switch (uchoice) {
                case 1:
                    udisplayFlight();
                    break;
                case 2:
                    reservation();
                    break;
                case 3:
                    viewticket();
                    break;
                case 4:
                    cancle();
                    break;
                case 5:
                    start(); // Return to the main menu
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n\tWrong Choice!!!\n");
            }
        }
    }
    public void addFlight() {
        Scanner scanner = new Scanner(System.in);
        int s1, d1;
        String c, src, dest;

        System.out.println("\n*****************************ADD FLIGHT DETAILS*****************************");
        System.out.print("Enter Flight No.: ");
        flightno = scanner.next();
        System.out.print("Enter Flight Code: ");
        flight_code = scanner.next();

        System.out.println("Choose Source:");
        System.out.println("1. Bangalore\n2. Bhubaneswar\n3. Chennai\n4. Delhi\n5. Kochi\n6. Mumbai\n7. Siliguri\n8. Visakhapatnam");
        System.out.print("Enter your choice: ");
        s1 = scanner.nextInt();

        switch (s1) {
            case 1:
                source = "Bangalore";
                break;
            case 2:
                source = "Bhubaneswar";
                break;
            case 3:
                source = "Chennai";
                break;
            case 4:
                source = "Delhi";
                break;
            case 5:
                source = "Kochi";
                break;
            case 6:
                source = "Mumbai";
                break;
            case 7:
                source = "Siliguri";
                break;
            case 8:
                source = "Visakhapatnam";
                break;
            default:
                System.out.println("\nWrong choice!");
                return;
        }

        System.out.println("Choose Destination:");
        System.out.println("1. Bangalore\n2. Bhubaneswar\n3. Chennai\n4. Delhi\n5. Kochi\n6. Mumbai\n7. Siliguri\n8. Visakhapatnam");
        System.out.print("Enter your choice: ");
        d1 = scanner.nextInt();

        switch (d1) {
            case 1:
                destination = "Bangalore";
                break;
            case 2:
                destination = "Bhubaneswar";
                break;
            case 3:
                destination = "Chennai";
                break;
            case 4:
                destination = "Delhi";
                break;
            case 5:
                destination = "Kochi";
                break;
            case 6:
                destination = "Mumbai";
                break;
            case 7:
                destination = "Siliguri";
                break;
            case 8:
                destination = "Visakhapatnam";
                break;
            default:
                System.out.println("\nWrong choice!");
                return;
        }

        System.out.print("Enter Date(dd/mm/yyyy): ");
        fdate = scanner.next();
        System.out.print("Enter Arrival Time(hh:mm): ");
        arrival = scanner.next();
        System.out.print("Enter Departure Time(hh:mm): ");
        departure = scanner.next();
        System.out.print("Enter Price: ");
        price = scanner.next();
        System.out.print("Enter Availability(YES/NO): ");
        favail = scanner.next();

        System.out.print("\nConfirm? (y/n): ");
        c = scanner.next();

        if (c.equals("n")) {
            System.out.println("\nFlight details not added.");
            return;
        }

        try {
            FileWriter fileWriter = new FileWriter("FlightRecord.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(flightno + " " + flight_code + " " + source + " " + destination + " " + fdate + " " + arrival + " " + departure + " " + price + " " + favail + "\n");
            bufferedWriter.close();
            System.out.println("\nFlight details added successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adisplayFlight() {
        clearConsole();
        System.out.println("\n\t\t\tFLIGHT DETAILS\n");
        System.out.println("Flight No.\tFlight Code\tSource\tDestination\tDate\tArrival\tDeparture\tPrice\tAvailability");
        try {
            File file = new File("FlightRecord.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress any key to continue...");
        scanner.nextLine();
    }

    public void delFlight() {
        clearConsole();
        System.out.println("\n\t\t\tDELETE FLIGHT DETAILS\n");
        adisplayFlight();

        try {
            File inputFile = new File("FlightRecord.txt");
            File tempFile = new File("TempFlightRecord.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter Flight No. to delete: ");
            String flightNoToDelete = scanner.next();

            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(" ");
                String flightNo = parts[0];
                if (flightNo.equals(flightNoToDelete)) {
                    found = true;
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("\nFlight deleted successfully.");
            } else {
                System.out.println("\nFlight not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress any key to continue...");
        scanner.nextLine();
    }

    public void modFlight() {
        clearConsole();
        System.out.println("\n\t\t\tMODIFY FLIGHT DETAILS\n");
        adisplayFlight();

        try {
            File inputFile = new File("FlightRecord.txt");
            File tempFile = new File("TempFlightRecord.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter Flight No. to modify: ");
            String flightNoToModify = scanner.next();

            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(flightNoToModify)) {
                    found = true;
                    System.out.println("\nFlight details for " + flightNoToModify + ": " + currentLine);
                    System.out.println("\nEnter new details:");

                    System.out.print("Flight No.: ");
                    flightno = scanner.next();
                    System.out.print("Flight Code: ");
                    flight_code = scanner.next();

                    System.out.println("Choose Source:");
                    System.out.println("1. Bangalore\n2. Bhubaneswar\n3. Chennai\n4. Delhi\n5. Kochi\n6. Mumbai\n7. Siliguri\n8. Visakhapatnam");
                    System.out.print("Enter your choice: ");
                    int s1 = scanner.nextInt();

                    switch (s1) {
                        case 1:
                            source = "Bangalore";
                            break;
                        case 2:
                            source = "Bhubaneswar";
                            break;
                        case 3:
                            source = "Chennai";
                            break;
                        case 4:
                            source = "Delhi";
                            break;
                        case 5:
                            source = "Kochi";
                            break;
                        case 6:
                            source = "Mumbai";
                            break;
                        case 7:
                            source = "Siliguri";
                            break;
                        case 8:
                            source = "Visakhapatnam";
                            break;
                        default:
                            System.out.println("\nWrong choice!");
                            return;
                    }

                    System.out.println("Choose Destination:");
                    System.out.println("1. Bangalore\n2. Bhubaneswar\n3. Chennai\n4. Delhi\n5. Kochi\n6. Mumbai\n7. Siliguri\n8. Visakhapatnam");
                    System.out.print("Enter your choice: ");
                    int d1 = scanner.nextInt();

                    switch (d1) {
                        case 1:
                            destination = "Bangalore";
                            break;
                        case 2:
                            destination = "Bhubaneswar";
                            break;
                        case 3:
                            destination = "Chennai";
                            break;
                        case 4:
                            destination = "Delhi";
                            break;
                        case 5:
                            destination = "Kochi";
                            break;
                        case 6:
                            destination = "Mumbai";
                            break;
                        case 7:
                            destination = "Siliguri";
                            break;
                        case 8:
                            destination = "Visakhapatnam";
                            break;
                        default:
                            System.out.println("\nWrong choice!");
                            return;
                    }

                    System.out.print("Enter Date(dd/mm/yyyy): ");
                    fdate = scanner.next();
                    System.out.print("Enter Arrival Time(hh:mm): ");
                    arrival = scanner.next();
                    System.out.print("Enter Departure Time(hh:mm): ");
                    departure = scanner.next();
                    System.out.print("Enter Price: ");
                    price = scanner.next();
                    System.out.print("Enter Availability(YES/NO): ");
                    favail = scanner.next();

                    currentLine = flightno + " " + flight_code + " " + source + " " + destination + " " + fdate + " " + arrival + " " + departure + " " + price + " " + favail;
                }

                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("\nFlight details modified successfully.");
            } else {
                System.out.println("\nFlight not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress any key to continue...");
        scanner.nextLine();
    }
    public void searchFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\n\tEnter Flight No. to search: ");
        String flightNoToSearch = scanner.next();

        try {
            File file = new File("FlightRecord.txt");
            Scanner fileScanner = new Scanner(file);
            boolean found = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                String flightNo = parts[0];
                if (flightNo.equals(flightNoToSearch)) {
                    System.out.println("\nFlight found:\n" + line);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nFlight not found.");
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.print("\nPress any key to continue...");
        scanner.nextLine();
    }

    public void udisplayFlight() {
        clearConsole();
        System.out.println("\n\t\t\tFLIGHT DETAILS\n");
        System.out.println("Flight No.\tFlight Code\tSource\tDestination\tDate\tArrival\tDeparture\tPrice\tAvailability");
        try {
            File file = new File("FlightRecord.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void reservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\n\tEnter Flight No. to reserve: ");
        String flightNoToReserve = scanner.next();

        try {
            File file = new File("FlightRecord.txt");
            Scanner fileScanner = new Scanner(file);
            boolean found = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");
                String flightNo = parts[0];
                if (flightNo.equals(flightNoToReserve)) {
                    flightno = parts[0];
                    flight_code = parts[1];
                    source = parts[2];
                    destination = parts[3];
                    fdate = parts[4];
                    arrival = parts[5];
                    departure = parts[6];
                    price = parts[7];
                    favail = parts[8];
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nFlight not found.");
                System.out.print("\nPress any key to continue...");
                scanner.nextLine();
                return;
            }

            System.out.print("\nEnter Passenger ID: ");
            pid = scanner.next();
            System.out.print("Enter Passenger Name: ");
            pname = scanner.next();
            System.out.print("Enter Phone Number: ");
            pnum = scanner.next();
            System.out.print("Enter Address: ");
            paddr = scanner.next();
            System.out.print("Enter Class(Economy/Business): ");
            fclass = scanner.next();
            System.out.print("Enter Seat(1A/2B/3C): ");
            fseat = scanner.next();

            if (favail.equals("YES")) {
                System.out.print("\nConfirm Reservation? (y/n): ");
                String c = scanner.next();

                if (c.equals("n")) {
                    System.out.println("\nReservation canceled.");
                    return;
                }

                try {
                    FileWriter fileWriter = new FileWriter("ReservationRecord.txt", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(
                            " " + flightno + " " + flight_code + " " + source + " " + destination + " " + fdate + " " + arrival + " " + departure + " " + price + " " + favail
                                    + " " + pid + " " + pname + " " + pnum + " " + paddr + " " + fclass + " " + fseat + "\n");
                    bufferedWriter.close();

                    // Update flight availability to NO
                    updateFlightAvailability(flightNoToReserve, "NO");

                    System.out.println("\nReservation confirmed successfully!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("\nFlight is not available for reservation.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.print("\nPress any key to continue...");
        scanner.nextLine();
    }

    private void updateFlightAvailability(String flightNo, String availability) {
        try {
            File inputFile = new File("FlightRecord.txt");
            File tempFile = new File("TempFlightRecord.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(flightNo)) {
                    String[] parts = currentLine.split(" ");
                    parts[8] = availability;
                    currentLine = String.join(" ", parts);
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void viewticket() {
        clearConsole();
        System.out.println("\n\t\t\tVIEW TICKET\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Passenger ID: ");
        String passengerID = scanner.next();

        try {
            File file = new File("ReservationRecord.txt");
            Scanner fileScanner = new Scanner(file);
            boolean found = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains(" " + passengerID + " ")) {
                    String[] parts = line.split(" ");
                    flightno = parts[0];
                    flight_code = parts[1];
                    source = parts[2];
                    destination = parts[3];
                    fdate = parts[4];
                    arrival = parts[5];
                    departure = parts[6];
                    price = parts[7];
                    favail = parts[8];
                    pid = parts[9];
                    pname = parts[10];
                    pnum = parts[11];
                    paddr = parts[12];
                    fclass = parts[13];
                    fseat = parts[14];

                    System.out.println("\nTicket Details:");
                    System.out.println("Flight No.: " + flightno);
                    System.out.println("Flight Code: " + flight_code);
                    System.out.println("Source: " + source);
                    System.out.println("Destination: " + destination);
                    System.out.println("Date: " + fdate);
                    System.out.println("Arrival Time: " + arrival);
                    System.out.println("Departure Time: " + departure);
                    System.out.println("Price: " + price);
                    System.out.println("Availability: " + favail);
                    System.out.println("Passenger ID: " + pid);
                    System.out.println("Passenger Name: " + pname);
                    System.out.println("Phone Number: " + pnum);
                    System.out.println("Address: " + paddr);
                    System.out.println("Class: " + fclass);
                    System.out.println("Seat: " + fseat);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("\nTicket not found.");
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.print("\nPress any key to continue...");
        scanner.nextLine();
    }

    public void cancle() {
        clearConsole();
        System.out.println("\n\t\t\tCANCELLATION\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Passenger ID to cancel reservation: ");
        String passengerID = scanner.next();

        try {
            File inputFile = new File("ReservationRecord.txt");
            File tempFile = new File("TempReservationRecord.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            boolean found = false;
            String flightNo = "";

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(" " + passengerID + " ")) {
                    found = true;
                    String[] parts = currentLine.split(" ");
                    flightNo = parts[0];

                    // Update flight availability to YES
                    updateFlightAvailability(flightNo, "YES");
                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            writer.close();
            reader.close();

            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("\nReservation canceled successfully.");
            } else {
                System.out.println("\nReservation not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.print("\nPress any key to continue...");
        scanner.nextLine();
    }

    private void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
