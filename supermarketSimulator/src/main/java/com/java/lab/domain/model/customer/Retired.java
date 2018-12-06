package com.java.lab.domain.model.customer;


import com.java.lab.domain.exception.InvalidModelException;

import java.math.BigDecimal;

public class Retired extends BaseCustomer {
    public static final int MIN_FEMALE_AGE = 54;
    public static final int MIN_MALE_AGE = 61;
    public static final int MAX_AGE = 150;

    public Retired(Integer id, String name, Integer age, Sex sex) throws InvalidModelException {
        super(id, name, age, sex);
    }

    public Retired(Integer id, String name, Integer age, Sex sex, BigDecimal cashMoney, BigDecimal cardBalance, BigDecimal bonus) throws InvalidModelException {
        super(id, name, age, sex, cashMoney, cardBalance, bonus);
    }

    @Override
    public Integer getMinAge() {
        return sex == Sex.MALE ? MIN_MALE_AGE : MIN_FEMALE_AGE;
    }

    @Override
    public Integer getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public String toString() {
        return "Retired{" +
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
