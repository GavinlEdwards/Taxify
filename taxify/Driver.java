package taxify;

import java.time.LocalDate;

import taxify.Interfaces.IDriver;
import taxify.Interfaces.ITaxiCompany;

public class Driver implements IDriver {

    private int id;
    private double rating;
    private String firstName;
    private String lastName;
    private int yearsExperience;
    private char gender;
    private LocalDate birthDate;
    private int ratingCount;
    private ITaxiCompany company;


    public Driver(int id, String firstName, String lastName, char gender, LocalDate birthDate, int yearsExperience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.yearsExperience = yearsExperience;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public char getGender() {
        return gender;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public int getYearsExperience() {
        return yearsExperience;
    }

    @Override
    public double getRating() {
        return rating/ratingCount;
    }

    @Override
    public void giveRating(double rating) {
        ratingCount++;
        this.rating = this.rating + rating;
    }


    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

}
