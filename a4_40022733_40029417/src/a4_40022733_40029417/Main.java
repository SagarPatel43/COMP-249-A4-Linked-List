package a4_40022733_40029417;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    static Scanner console = new Scanner(System.in);

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

    static void addFTRecords(ArrayList<FullTimeFaculty> ft, String fileName) throws FileNotFoundException {
        int ans = 0;
        Scanner read = new Scanner(new FileInputStream(fileName));
        while (read.hasNext()) {
            FullTimeFaculty temp = new FullTimeFaculty(read.nextLong(), read.next(), read.next(), read.next(), read.nextInt(), read.nextDouble());
            ft.add(temp);
        }
        read.close();
        System.out.println("Please add a record for full time faculty member");
        while (ans != -1) {
            FullTimeFaculty temp = (FullTimeFaculty) check("FT");
            while (ft.contains(temp)) {
                System.out.println("Already have a person with that ID, please enter a new employee ID");
                temp.setEmployeeID(console.nextLong());
            }
            ft.add(temp);
            System.out.println("Record added, do you wish to enter a new record? (Enter -1 to stop)");
            ans = console.nextInt();
        }
        System.out.println("Done adding full time faculty members, adding to text...");
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
        System.out.println("Please add a record for part time faculty member");
        while (ans != -1) {
            PartTimeFaculty temp = (PartTimeFaculty) check("PT");
            while (pt.contains(temp)) {
                System.out.println("Already have a person with that ID, please enter a new employee ID");
                temp.setEmployeeID(console.nextLong());
            }
            pt.add(temp);
            System.out.println("Record added, do you wish to enter a new record? (Enter -1 to stop)");
            ans = console.nextInt();
        }
        System.out.println("Done adding part time faculty members, adding to text...");
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
        System.out.println("Please add a record for teaching assistant");
        while (ans != -1) {
            TeachingAssistant temp = (TeachingAssistant) check("TA");
            while (ta.contains(temp)) {
                System.out.println("Already have a person with that ID, please enter a new employee ID");
                temp.setEmployeeID(console.nextLong());
            }
            if (!temp.getClassification().equals("Alum")) {
                ta.add(temp);
            } else {
                System.out.println("Will not be adding this record, classification is a Alum");
            }
            System.out.println("Record added, do you wish to enter a new record? (Enter -1 to stop)");
            ans = console.nextInt();
        }
        System.out.println("Done adding teaching assistant, adding to text...");
        PrintWriter pw = new PrintWriter(fileName);
        for (TeachingAssistant i : ta) {
            pw.println(i);
        }
        pw.close();
    }

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

    static void findHighestLowestFTSalary(String FTfile) throws FileNotFoundException {
        Scanner readFT = new Scanner(new FileInputStream(FTfile));
        EmployeeList listFT = new EmployeeList();
        while (readFT.hasNext()) {
            listFT.add(new FullTimeFaculty(readFT.nextLong(), readFT.next(), readFT.next(), readFT.next(), readFT.nextInt(), readFT.nextDouble()));
        }
        readFT.close();
        FullTimeFaculty min = (FullTimeFaculty) listFT.getFirstValue(), max = (FullTimeFaculty) listFT.getFirstValue();
        for (int i = 0; i < listFT.getSize(); i++) {
            if (min.getSalary() > ((FullTimeFaculty) listFT.getValue(i)).getSalary()) {
                min = (FullTimeFaculty) listFT.getValue(i);
            }

            if (max.getSalary() < ((FullTimeFaculty) listFT.getValue(i)).getSalary()) {
                max = (FullTimeFaculty) listFT.getValue(i);
            }
        }
        System.out.println("The max salary is: " + max.getSalary()+"\nThe min salary is: " + min.getSalary());
        System.out.println("\nThese are the following records with max and min salary (with duplicates)");
        for (int i = 1; i <= listFT.getSize(); i++) {
            if (max.getSalary() == ((FullTimeFaculty) listFT.getValue(i)).getSalary()) {
                System.out.println("Max: " + (FullTimeFaculty) listFT.getValue(i));
            }
            if (min.getSalary() == ((FullTimeFaculty) listFT.getValue(i)).getSalary()) {
                System.out.println("Min: " + (FullTimeFaculty) listFT.getValue(i));
            }
        }
        
    }

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