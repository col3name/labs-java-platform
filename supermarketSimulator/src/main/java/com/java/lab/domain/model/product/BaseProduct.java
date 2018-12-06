package com.java.lab.domain.model.product;

import com.java.lab.applicationhost.utill.MathUtil;
import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.model.BaseEntity;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class BaseProduct extends BaseEntity implements Product {

    public static final String UNSIGNED_ERROR_MSG = "price must be more than 0";
    protected final String name;

    protected final Integer ageLimit;

    protected final BigDecimal price;

    protected BigDecimal bonus;

    public BaseProduct(Integer id, String name, Integer ageLimit, BigDecimal price, BigDecimal bonus) throws InvalidModelException {
        super(id);
        tryThrow(name.length() < 3, "name min length 3");
        tryThrow(ageLimit < 0, "age must be more than 0");
        tryThrow(MathUtil.lessThanZero(price), UNSIGNED_ERROR_MSG);
        tryThrow(MathUtil.lessThanZero(bonus), "bonus must be more than 0");
        this.name = name;
        this.bonus = bonus;
        this.ageLimit = ageLimit;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(ageLimit, that.ageLimit) &&
                Objects.equals(price, that.price) &&
                Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ageLimit, price, bonus);
    }
}
