//Kevin Tan 40022733, Sagar Patel 40029417
//COMP249
//Assignment #4
//April 10, 2017
package a4_40022733_40029417;

public class FullTimeFaculty extends Employee {

    private double salary;

    public FullTimeFaculty() {
        super();
        salary = 0.0;
    }

    public FullTimeFaculty(long employeeID, String firstName, String lastName, String cityResidence, int hireYear, double salary) {
        super(employeeID, firstName, lastName, cityResidence, hireYear);
        this.salary = salary;
    }

    public FullTimeFaculty(FullTimeFaculty copy) {
        super(copy);
        salary = copy.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return super.toString() + salary;
    }
    
    public FullTimeFaculty clone(){
        return new FullTimeFaculty(this);
    }
}