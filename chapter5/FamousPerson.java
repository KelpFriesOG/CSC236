package chapter5;

import java.util.Comparator;

public class FamousPerson implements Comparable<FamousPerson> {

    protected String firstName, lastName, fact;
    protected int yearOfBirth;

    public FamousPerson(String firstName, String lastName, int yearOfBirth, String fact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fact = fact;
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFact() {
        return fact;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        else {
            FamousPerson fp = (FamousPerson) obj;
            return (this.firstName.equals(fp.firstName)
                    && this.lastName.equals(fp.lastName));
        }

    }

    public int compareTo(FamousPerson other) {
        // other is assumed not to be a null pointer

        if (!other.lastName.equals(this.lastName)) {
            return this.lastName.compareTo(other.lastName);
        } else {
            return this.firstName.compareTo(other.firstName);
        }
    }

    @Override
    public String toString() {

        return (firstName + " " + lastName + " (Born " + yearOfBirth
                + "): " + fact);

    }

    public static Comparator<FamousPerson> yearOfBirthComparator() {

        return new Comparator<FamousPerson>() {
            public int compare(FamousPerson element1, FamousPerson element2) {
                return (element1.yearOfBirth - element2.yearOfBirth);
            }
        };

    }

}
