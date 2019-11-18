package ru.kupchenkov.service;

import ru.kupchenkov.dao.BuildAreaDao;
import ru.kupchenkov.dao.BuildYearDao;
import ru.kupchenkov.entity.RealtyType;

import javax.persistence.EntityManager;

public class CalculateService {

    private EntityManager manager;
    private RealtyType realtyType;
    private int buildYear;
    private double buildArea;
    private double insurrerSum;
    private long countDays;

    public CalculateService(EntityManager manager, RealtyType realtyType, int buildYear, double buildArea, double insurrerSum, long countDays) {
        this.manager = manager;
        this.realtyType = realtyType;
        this.buildYear = buildYear;
        this.buildArea = buildArea;
        this.insurrerSum = insurrerSum;
        this.countDays = countDays;
    }

    public double calculate() {
        BuildYearDao buildYearDao = new BuildYearDao(manager);
        double buildCoefficient = buildYearDao.findByYear(buildYear).getCoefficient();
        BuildAreaDao buildAreaDao = new BuildAreaDao(manager);
        double areaCoefficient = buildAreaDao.findByArea(buildArea).getCoefficient();
        return Math.round(((insurrerSum / countDays) * realtyType.getCoefficient() * buildCoefficient * areaCoefficient) * 100) / 100;
    }
}
