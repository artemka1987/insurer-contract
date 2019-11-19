package ru.kupchenkov.entity;

import ru.kupchenkov.entity.embeded.AddressEmbeded;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table
@Cacheable(false)
public class Realty {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    private RealtyType realtyType;

    @ManyToOne(optional = false)
    private BuildYear buildYear;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private double area;

    @Embedded
    private AddressEmbeded address;

    @Column(nullable = false)
    private int insurerSum;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ManyToOne(optional = false)
    private User userModify;

    @Column(nullable = false)
    private Calendar dateModify;

    public Realty() {
    }

    public Realty(RealtyType realtyType, BuildYear buildYear, double area, AddressEmbeded address, int insurerSum, Date startDate, Date endDate, User userModify, Calendar dateModify, int year) {
        this.realtyType = realtyType;
        this.buildYear = buildYear;
        this.area = area;
        this.address = address;
        this.insurerSum = insurerSum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userModify = userModify;
        this.dateModify = dateModify;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealtyType getRealtyType() {
        return realtyType;
    }

    public void setRealtyType(RealtyType realtyType) {
        this.realtyType = realtyType;
    }

    public BuildYear getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(BuildYear buildYear) {
        this.buildYear = buildYear;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public AddressEmbeded getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeded address) {
        this.address = address;
    }

    public int getInsurerSum() {
        return insurerSum;
    }

    public void setInsurerSum(int insurerSum) {
        this.insurerSum = insurerSum;
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

    public User getUserModify() {
        return userModify;
    }

    public void setUserModify(User userModify) {
        this.userModify = userModify;
    }

    public Calendar getDateModify() {
        return dateModify;
    }

    public void setDateModify(Calendar dateModify) {
        this.dateModify = dateModify;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
