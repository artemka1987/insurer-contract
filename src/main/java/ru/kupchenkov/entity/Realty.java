package ru.kupchenkov.entity;

import javax.persistence.*;

@Entity
@Table
public class Realty {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(optional = false)
    private RealtyType realtyType;

    @ManyToOne(optional = false)
    private BuildYear buildYear;

}
