//Kevin Tan 40022733, Sagar Patel 40029417
//COMP249
//Assignment #4
//April 10, 2017
package a4_40022733_40029417;

public class TeachingAssistant extends Employee{
    
    private String classification;
    private int numClass;
    private int totalWorkingHours;
    
    public TeachingAssistant(){
        super();
        classification = "";
        numClass = 0;
        totalWorkingHours = 0;
    }
    
    public TeachingAssistant(long employeeID, String firstName, String lastName, String cityResidence, int hireYear, String classification, int numClass, int totalWorkingHours){
        super(employeeID, firstName, lastName, cityResidence, hireYear);
        this.classification = classification;
        this.numClass = numClass;
        this.totalWorkingHours = totalWorkingHours;
    }
    
    public TeachingAssistant(TeachingAssistant copy){
        super(copy);
        classification = copy.classification;
        numClass = copy.numClass;
        totalWorkingHours = copy.totalWorkingHours;
    }
    
    public void setClassification(String classification){
        this.classification = classification;
    }
    
    public void setNumClass(int numClass){
        this.numClass = numClass;
    }
    
    public void setTotalWorkingHours(int totalWorkingHours){
        this.totalWorkingHours = totalWorkingHours;
    }
    
    public String getClassification(){
        return classification;
    }
    
    public int getNumClass(){
        return numClass;
    }
    
    public int getTotalWorkingHours(){
        return totalWorkingHours;
    }
    
    public String toString() {
        return super.toString() + String.format("%-7s %-7d %-7d", classification, numClass, totalWorkingHours);
    }
    
    public TeachingAssistant clone(){
        return new TeachingAssistant(this);
    }

}