package ru.kupchenkov.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Cacheable(false)
public class Person {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(length = 100)
    private String middleName;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false, length = 4)
    private String documentSeries;

    @Column(nullable = false)
    private int documentNumber;

    public Person() {
    }

    public Person(String lastName, String firstName, String middleName, Date birthDate, String documentSeries, int documentNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.documentSeries = documentSeries;
        this.documentNumber = documentNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDocumentSeries() {
        return documentSeries;
    }

    public void setDocumentSeries(String documentSeries) {
        this.documentSeries = documentSeries;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFio() {
        return lastName + " " + firstName + " " + middleName;
    }

    public String getPassport() {
        return documentSeries + " " + documentNumber;
    }

    public String getBirthDateForGrid() {
        return new SimpleDateFormat("dd.MM.yyyy").format(birthDate);
    }
}
