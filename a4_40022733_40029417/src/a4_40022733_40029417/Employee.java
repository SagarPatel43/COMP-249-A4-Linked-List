package a4_40022733_40029417;

public class Employee implements Ordered{
    
    protected long employeeID;
    protected String firstName;
    protected String lastName;
    protected String cityResidence;
    protected int hireYear;
    
    public Employee(){
        employeeID = 0;
        firstName = "";
        lastName = "";
        cityResidence = "";
        hireYear = 0;
    }
    
    public Employee(long employeeID, String firstName, String lastName, String cityResidence, int hireYear){
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityResidence = cityResidence;
        this.hireYear = hireYear;
    }
    
    public Employee(Employee copy){
        this.employeeID = copy.employeeID;
        this.firstName = copy.firstName;
        this.lastName = copy.lastName;
        this.cityResidence = copy.cityResidence;
        this.hireYear = copy.hireYear;
    }
    
    public void setEmployeeID(long employeeID){
        this.employeeID = employeeID;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setCityResidence(String cityResidence){
        this.cityResidence = cityResidence;
    }
    
    public void setHireYear(int hireYear){
        this.hireYear = hireYear;
    }
    
    public long getEmployeeID(){
        return employeeID;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getCityResidence(){
        return cityResidence;
    }
    
    public int getHireYear(){
        return hireYear;
    }
    
    public String toString(){
        return String.format("%-7d %-7s %-15s %-15s %-8d", employeeID, firstName, lastName, cityResidence, hireYear);
    }
    
    public boolean equals(Object ob){
        if(ob.getClass() != this.getClass() || ob == null){
            return false;
        }
        Employee e = (Employee) ob;
        if(e.employeeID == this.employeeID){
            return true;
        }
        return false;
    }

    public boolean precedes(Object ob) {
        if(ob.getClass() != this.getClass() || ob == null){
            return false;
        }
        Employee e = (Employee) ob;
        if(hireYear < e.hireYear){
            return true;
        }
        return false;
    }

    public boolean follows(Object ob) {
        if(ob.getClass() != this.getClass() || ob == null){
            return false;
        }
        Employee e = (Employee) ob;
        if(hireYear > e.hireYear){
            return true;
        }
        return false;
    }
    
    public Employee clone(){
        return new Employee(this);
    }
}