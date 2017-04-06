//Kevin Tan 40022733, Sagar Patel 40029417
//COMP249
//Assignment #4
//April 10, 2017

/*
This program utilizes File IO, ArrayLists, and LinkedLists(built from scratch) to create a payment system that handles Employee records stored in txt files
and performs operations on this data with the use of the above to make calculations and organize the data.
*/
package a4_40022733_40029417;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    static Scanner console = new Scanner(System.in);

    //All three add methods call this method, it is used to prompt the user for all the input necessary for a new record, additionally a String parameter is passed into the method to check what
    //type of employee record is being added (to ask for the appropriate data), in the interest of preventing crashing these statements are in a try-catch statement
    //If the user enters data that would cause an input mismatch exception it will be caught and they will be prompted again to enter the correct data.
    static Employee check(String type) {
        Employee temp = null;
            try {
                System.out.print("Enter employee ID: ");
                long id = console.nextLong();
                System.out.print("Enter employee first name: ");
                String firstName = console.next();
                System.out.print("Enter employee last name: ");
                String lastName = console.next();
                System.out.print("Enter employee city of residence: ");
                String cityResidence = console.next();
                System.out.print("Enter employee hire year: ");
                int hireYear = console.nextInt();
                if (type.equals("TA")) {
                    System.out.print("Enter employee classification: ");
                    String classification = console.next();
                    System.out.print("Enter employee number of classes: ");
                    int numClass = console.nextInt();
                    System.out.print("Enter employee number of hours: ");
                    int numHours = console.nextInt();
                    temp = new TeachingAssistant(id, firstName, lastName, cityResidence, hireYear, classification, numClass, numHours);
                }
                if (type.equals("PT")) {
                    System.out.print("Enter employee hourly salary: ");
                    double hourly = console.nextDouble();
                    System.out.print("Enter employee number of hours: ");
                    int numHours = console.nextInt();
                    System.out.print("Enter employee number of students: ");
                    int numStudents = console.nextInt();
                    temp = new PartTimeFaculty(id, firstName, lastName, cityResidence, hireYear, hourly, numHours, numStudents);
                }
                if (type.equals("FT")) {
                    System.out.print("Enter employee salary: ");
                    double salary = console.nextDouble();
                    temp = new FullTimeFaculty(id, firstName, lastName, cityResidence, hireYear, salary);
                }
            } catch (InputMismatchException e) {
                System.out.println("\nIncorrect record!");
                System.out.println("Please re-enter the record again");
                console.next();
                temp = check(type);
            }
        return temp;
    }

    //addRecords
    //Firstly these methods open a FileInputStream and read all the data into Object contructors, allowing it to initialize an object and add it into the ArrayList of objects passed into the method
    //Then these methods will call the check method to return a valid record Object which will be stored with a pointer, the methods then check to see if the new record being returned by check
    //contains a duplicate ID with the IDs already in the ArrayList, if there is a conflict the user must reenter the ID
    //The methods add this record into the ArrayList and the user can continue or stop adding records
    //Once user is done, the Objects in the ArrayList are overwritten into the input file.
    static void addFTRecords(ArrayList<FullTimeFaculty> ft, String fileName) throws FileNotFoundException {
        int ans = 0;
        Scanner read = new Scanner(new FileInputStream(fileName));
        while (read.hasNext()) {
            FullTimeFaculty temp = new FullTimeFaculty(read.nextLong(), read.next(), read.next(), read.next(), read.nextInt(), read.nextDouble());
            ft.add(temp);
        }
        read.close();
        System.out.println("Please enter information for new Full Time Faculty record: ");
        while (ans != -1) {
            FullTimeFaculty temp = (FullTimeFaculty) check("FT");
            while (ft.contains(temp)) {
                System.out.println("There is already an employee with that ID, please enter a new employee ID : ");
                temp.setEmployeeID(console.nextLong());
            }
            ft.add(temp);
            System.out.println("Record added. Do you wish to enter a new record? (Enter -1 to stop, 1 to continue)");
            ans = console.nextInt();
        }
        System.out.println("Now updating records in txt file...");
        PrintWriter pw = new PrintWriter(fileName);

        for (FullTimeFaculty i : ft) {
            pw.println(i);
        }
        pw.close();
    }

    static void addPTRecords(ArrayList<PartTimeFaculty> pt, String fileName) throws FileNotFoundException {
        int ans = 0;
        Scanner read = new Scanner(new FileInputStream(fileName));
        while (read.hasNext()) {
            PartTimeFaculty temp = new PartTimeFaculty(read.nextLong(), read.next(), read.next(), read.next(), read.nextInt(), read.nextDouble(), read.nextInt(), read.nextInt());
            pt.add(temp);
        }
        read.close();
        System.out.println("Please enter information for new Part Time Faculty record: ");
        while (ans != -1) {
            PartTimeFaculty temp = (PartTimeFaculty) check("PT");
            while (pt.contains(temp)) {
                System.out.println("There is already an employee with that ID, please enter a new employee ID : ");
                temp.setEmployeeID(console.nextLong());
            }
            pt.add(temp);
            System.out.println("Record added. Do you wish to enter a new record? (Enter -1 to stop, 1 to continue)");
            ans = console.nextInt();
        }
        System.out.println("Now updating records in txt file...");
        PrintWriter pw = new PrintWriter(fileName);
        for (PartTimeFaculty i : pt) {
            pw.println(i);
        }
        pw.close();
    }

    static void addTARecords(ArrayList<TeachingAssistant> ta, String fileName) throws FileNotFoundException {
        int ans = 0;
        Scanner read = new Scanner(new FileInputStream(fileName));
        while (read.hasNext()) {
            TeachingAssistant temp = new TeachingAssistant(read.nextLong(), read.next(), read.next(), read.next(), read.nextInt(), read.next(), read.nextInt(), read.nextInt());
            if (!temp.getClassification().equals("Alum")) {
                ta.add(temp);
            }
        }
        read.close();
        System.out.println("Please enter information for new Teaching Assistant record: ");
        while (ans != -1) {
            TeachingAssistant temp = (TeachingAssistant) check("TA");
            while (ta.contains(temp)) {
                System.out.println("There is already an employee with that ID, please enter a new employee ID : ");
                temp.setEmployeeID(console.nextLong());
            }
            if (!temp.getClassification().equals("Alum")) {
                ta.add(temp);
            } else {
                System.out.println("Alumnus cannot be TAs, record will be excluded.");
            }
            System.out.println("Record added. Do you wish to enter a new record? (Enter -1 to stop, 1 to continue)");
            ans = console.nextInt();
        }
        System.out.println("Now updating records in txt file...");
        PrintWriter pw = new PrintWriter(fileName);
        for (TeachingAssistant i : ta) {
            pw.println(i);
        }
        pw.close();
    }

    //This methods opens input streams for the two files passed in, then just as the add method, it initializes objects using the file data and adds these objects to the EmployeeList(LinkedList)
    //the adding of the objects to an empty list is handled by the add method
    //The a for loop is used to iterate through every node of the LinkedList using the getSize() method 
    //Within each iteration an object is initialized using the getValue method which takes an index as a parameter, with this object for the TAs, the classification can be retrieved using
    //the accessor methods and their salary can be calculate with the appropriate bonus based on their classification
    //For Part Time Faculty the object is initialized and a bonus is calculated based on their number of students
    //Once both loops are done the total Salaries of each type of Employee is printed
    static void findTermSalary(String TA, String PT) throws FileNotFoundException {
        Scanner readTA = new Scanner(new FileInputStream(TA)), readPT = new Scanner(new FileInputStream(PT));
        EmployeeList listTA = new EmployeeList(), listPT = new EmployeeList();
        double TAsum = 0, PTsum = 0;
        while (readTA.hasNext()) {
            TeachingAssistant a = new TeachingAssistant(readTA.nextLong(), readTA.next(), readTA.next(), readTA.next(), readTA.nextInt(), readTA.next(), readTA.nextInt(), readTA.nextInt());
            listTA.add(a);
        }
        while (readPT.hasNext()) {
            listPT.add(new PartTimeFaculty(readPT.nextLong(), readPT.next(), readPT.next(), readPT.next(), readPT.nextInt(), readPT.nextDouble(), readPT.nextInt(), readPT.nextInt()));
        }
        for (int i = 1; i <= listTA.getSize(); i++) {
            TeachingAssistant temp = (TeachingAssistant) listTA.getValue(i);
            if (temp.getClassification().equals("UGrad")) {
                TAsum += temp.getTotalWorkingHours() * (18.25);
            } else if (temp.getClassification().equals("Grad")) {
                TAsum += temp.getTotalWorkingHours() * (18.25 * 1.2);
            }
        }
        for (int i = 1; i <= listPT.getSize(); i++) {
            PartTimeFaculty temp = (PartTimeFaculty) listPT.getValue(i);
            if (temp.getNumStudents() >= 40 && temp.getNumStudents() < 60) {
                PTsum += temp.getHourlyRate() * temp.getNumHours() + 500;
            } else if (temp.getNumStudents() >= 60) {
                PTsum += temp.getHourlyRate() * temp.getNumHours() + 1000;
            } else {
                PTsum += temp.getHourlyRate() * temp.getNumHours();
            }
        }
        readTA.close();
        readPT.close();
        System.out.println("The total term salary of teaching assistants and part time faculty is $" + new DecimalFormat("#.00").format(TAsum + PTsum) + "\n");
    }

    //This method utilizes the EmployeeList LinkedList
    //It iterates through all the Nodes of the LinkedList and finds the Object with the lowest and highest salary, this uses the getValue method of the linked list that returns a deep copy
    //of the object in the Node at the index, the method then loops through a second time to find any records with the same salary as the highest/lowest, it then prints the results.
    static void findHighestLowestFTSalary(String FTfile) throws FileNotFoundException {
        Scanner readFT = new Scanner(new FileInputStream(FTfile));
        EmployeeList listFT = new EmployeeList();
        while (readFT.hasNext()) {
            listFT.add(new FullTimeFaculty(readFT.nextLong(), readFT.next(), readFT.next(), readFT.next(), readFT.nextInt(), readFT.nextDouble()));
        }
        readFT.close();
        FullTimeFaculty min = (FullTimeFaculty) listFT.getFirstValue(), max = (FullTimeFaculty) listFT.getFirstValue();
        for (int i = 2; i <= listFT.getSize(); i++) {
            if (min.getSalary() > ((FullTimeFaculty) listFT.getValue(i)).getSalary()) {
                min = (FullTimeFaculty) listFT.getValue(i);
            }

            if (max.getSalary() < ((FullTimeFaculty) listFT.getValue(i)).getSalary()) {
                max = (FullTimeFaculty) listFT.getValue(i);
            }
        }
        String highest ="\n";
        String lowest ="\n";
        for (int i = 1; i <= listFT.getSize(); i++) {
            
            if (max.getSalary() == ((FullTimeFaculty) listFT.getValue(i)).getSalary()) {
                highest += listFT.getValue(i).toString() + "\n";
            }
            if (min.getSalary() == ((FullTimeFaculty) listFT.getValue(i)).getSalary()) {
                lowest += listFT.getValue(i).toString() + "\n";
            }
        }
        System.out.println("\nEmployee(s) with lowest salary: " + lowest + "\nEmployee(s) with highest salary: " + highest);
    }

    //This methods uses the EmployeeList, once filled with all the Staff Objects from the records file, the list is iterated through with a for loop
    //The method temporarily creates an object of the Node data at that index in the list, and checks the PerfCode of that Staff object, depending on the value a bonus is awarded on 
    //top of their normal salary and this new salary is set into the Object using the mutator method available, once completee the PerfCode is defaulted to E and the now changed Object
    //is written into the input file.
    static void increaseStaffSalary(String StaffFile) throws FileNotFoundException {
        Scanner readStaff = new Scanner(new FileInputStream(StaffFile));
        EmployeeList listStaff = new EmployeeList();
        while (readStaff.hasNext()) {
            listStaff.add(new Staff(readStaff.nextLong(), readStaff.next(), readStaff.next(), readStaff.next(), readStaff.nextInt(), readStaff.nextInt(), readStaff.next().charAt(0)));
        }
        readStaff.close();
        PrintWriter writeStaff = new PrintWriter(new FileOutputStream(StaffFile));
        for (int i = 1; i <= listStaff.getSize(); i++) {
            Staff temp = (Staff) listStaff.getValue(i);
            if (temp.getPerfCode() == 'A') {
                temp.setSalary((int) (temp.getSalary() * 1.08));
            } else if (temp.getPerfCode() == 'B') {
                temp.setSalary((int) (temp.getSalary() * 1.06));
            } else if (temp.getPerfCode() == 'C') {
                temp.setSalary((int) (temp.getSalary() * 1.03));
            } else if (temp.getPerfCode() == 'D') {
                temp.setSalary((int) (temp.getSalary() * 1.01));
            }
            temp.setPerfCode('E');
            writeStaff.println(temp);
        }
        writeStaff.close();
    }

    //Initializes lists and catches FileNotFoundExceptions thrown by methods opening file streams.
    public static void main(String[] args) {
        ArrayList<TeachingAssistant> ta = new ArrayList<>();
        ArrayList<FullTimeFaculty> ft = new ArrayList<>();
        ArrayList<PartTimeFaculty> pt = new ArrayList<>();
        try {
            System.out.println("Adding records to Full-Time-Faculty file");
            //addFTRecords(ft, "Full-Time-Faculty.txt");
            System.out.println("Adding records to Part-Time-Faculty file");
            //addPTRecords(pt, "Part-Time-Faculty.txt");
            System.out.println("Adding records to Teaching Assistant file");
            //addTARecords(ta, "TAs.txt");
            System.out.println("Finding the total term salary for TAs and Part time faculties");
            findTermSalary("TAs.txt", "Part-Time-Faculty.txt");
            System.out.println("Finding the highest and lowest salaries");
            findHighestLowestFTSalary("Full-Time-Faculty.txt");
            System.out.println("Increasing the Staff salary and updating to file");
            increaseStaffSalary("Staff.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}