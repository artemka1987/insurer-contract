package ru.kupchenkov.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table
public class Contract {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    private Realty realty;

    @ManyToOne(optional = false)
    private Person person;

    @Column(unique = true, nullable = false)
    private int number;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private Date calculateDate;

    @Column(nullable = false)
    private double calculateSum;

    @Column(length = 500)
    private String comment;

    public Contract() {

    }

    public Contract(Realty realty, Person person, int number, Date date, Date startDate, Date endDate, Date calculateDate, double calculateSum, String comment) {
        this.realty = realty;
        this.person = person;
        this.number = number;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.calculateDate = calculateDate;
        this.calculateSum = calculateSum;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Realty getRealty() {
        return realty;
    }

    public void setRealty(Realty realty) {
        this.realty = realty;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCalculateDate() {
        return calculateDate;
    }

    public void setCalculateDate(Date calculateDate) {
        this.calculateDate = calculateDate;
    }

    public double getCalculateSum() {
        return calculateSum;
    }

    public void setCalculateSum(double calculateSum) {
        this.calculateSum = calculateSum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInsurer() {
        return getPerson().getLastName() + " " + getPerson().getFirstName() + " " + getPerson().getMiddleName();
    }

    public String getDateForGrid() {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public String getPeriodInsurance() {
        return new SimpleDateFormat("dd.MM.yyyy").format(startDate) + " - " + new SimpleDateFormat("dd.MM.yyyy").format(endDate);
    }

}
