package com.volgatech.javacore2018.lab4.supermarket.domain.model.customer;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;

import java.math.BigDecimal;

public class Child extends BaseCustomer {

    public static final int MAX_AGE = 17;
    public static final int MIN_AGE = 9;

    public Child(Integer id, String name, Integer age, Sex sex) throws InvalidModelException {
        super(id, name, age, sex);
    }

    public Child(Integer id, String name, Integer age, Sex sex, BigDecimal cashMoney, BigDecimal cardBalance, BigDecimal bonus) throws InvalidModelException {
        super(id, name, age, sex, cashMoney, cardBalance, bonus);
    }

    @Override
    public Integer getMinAge() {
        return MIN_AGE;
    }

    @Override
    public Integer getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ",  name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", cashBalance=" + cashBalance +
                ", cardBalance=" + cardBalance +
                ", bonusBalance=" + bonusBalance +
                '}';
    }
}
