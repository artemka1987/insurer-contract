package ru.kupchenkov.entity;

import javax.persistence.*;

@Entity
@Table
public class BuildArea {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private double startValue;

    @Column(nullable = false)
    private double endValue;

    @Column(nullable = false)
    private double coefficient;

    public BuildArea() {
    }

    public BuildArea(double startValue, double endValue, double coefficient) {
        this.startValue = startValue;
        this.endValue = endValue;
        this.coefficient = coefficient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    public double getEndValue() {
        return endValue;
    }

    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
