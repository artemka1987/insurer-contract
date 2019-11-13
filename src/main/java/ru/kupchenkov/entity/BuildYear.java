package ru.kupchenkov.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class BuildYear {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int startYear;

    @Column(nullable = false)
    private int endYear;

    @Column(nullable = false)
    private double coefficient;

    public BuildYear() {
    }

    public BuildYear(int startYear, int endYear, double coefficient) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.coefficient = coefficient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildYear buildYear = (BuildYear) o;
        return id == buildYear.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
