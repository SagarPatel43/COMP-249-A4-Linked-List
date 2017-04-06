//Kevin Tan 40022733, Sagar Patel 40029417
//COMP249
//Assignment #4
//April 10, 2017
package a4_40022733_40029417;

public class Staff extends Employee{
    
    private int salary;
    private char performanceCode;

    public Staff() {
        super();
        salary = 0;
        performanceCode = ' ';
    }

    public Staff(long employeeID, String firstName, String lastName, String cityResidence, int hireYear, int salary, char performanceCode) {
        super(employeeID, firstName, lastName, cityResidence, hireYear);
        this.salary = salary;
        this.performanceCode = performanceCode;
    }

    public Staff(Staff copy) {
        super(copy);
        salary = copy.salary;
        performanceCode = copy.performanceCode;
    }
    
    public void setSalary(int salary){
        this.salary = salary;
    }
    
    public void setPerfCode(char performanceCode){
        this.performanceCode = performanceCode;
    }
    
    public int getSalary(){
        return salary;
    }
    
    public char getPerfCode(){
        return performanceCode;
    }
    
    public String toString() {
        return super.toString() + "\t" + salary + "\t" + performanceCode;
    }
    
    public Staff clone(){
        return new Staff(this);
    }
}