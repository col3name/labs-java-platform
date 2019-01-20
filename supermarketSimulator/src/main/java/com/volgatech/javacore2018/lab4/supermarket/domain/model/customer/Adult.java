package com.volgatech.javacore2018.lab4.supermarket.domain.model.customer;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;

import java.math.BigDecimal;

public class Adult extends BaseCustomer {
    public static final int MAX_FEMALE_AGE = 53;
    public static final int MAX_MALE_AGE = 60;
    public static final int MIN_AGE = 18;

    public Adult(Integer id, String name, Integer age, Sex sex) throws InvalidModelException {
        super(id, name, age, sex);
    }

    public Adult(Integer id,
                 String name,
                 Integer age,
                 Sex sex,
                 BigDecimal cashBalance,
                 BigDecimal cardBalance,
                 BigDecimal bonus) throws InvalidModelException {
        super(id, name, age, sex, cashBalance, cardBalance, bonus);
    }

    @Override
    public Integer getMinAge() {
        return MIN_AGE;
    }

    @Override
    public Integer getMaxAge() {
        return sex == Sex.MALE ? MAX_MALE_AGE : MAX_FEMALE_AGE;
    }

    @Override
    public String toString() {
        return "Adult{" +
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
