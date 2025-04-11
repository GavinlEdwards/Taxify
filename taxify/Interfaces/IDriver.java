package taxify.Interfaces;

import java.time.LocalDate;

public interface IDriver {

    public int getId();
    public String getFirstName();
    public String getLastName();
    public char getGender();
    public LocalDate getBirthDate();
    public int getYearsExperience();
    public double getRating();
    
    public void giveRating(double rating);
    public void setCompany(ITaxiCompany company);
}
