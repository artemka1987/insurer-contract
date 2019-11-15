package ru.kupchenkov.entity.embeded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressEmbeded {

    @Column(nullable = false)
    private String country;

    @Column(length = 6)
    private String zipCode;

    @Column
    private String region;

    @Column
    private String district;

    @Column
    private String city;

    @Column
    private String Street;

    @Column(length = 20)
    private String house;

    @Column(length = 20)
    private String building;

    @Column(length = 20)
    private String building2;

    @Column(length = 20)
    private String flat;

    public AddressEmbeded() {
    }

    public AddressEmbeded(String country, String zipCode, String region, String district, String city, String street, String house, String building, String building2, String flat) {
        this.country = country;
        this.zipCode = zipCode;
        this.region = region;
        this.district = district;
        this.city = city;
        Street = street;
        this.house = house;
        this.building = building;
        this.building2 = building2;
        this.flat = flat;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBuilding2() {
        return building2;
    }

    public void setBuilding2(String building2) {
        this.building2 = building2;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
