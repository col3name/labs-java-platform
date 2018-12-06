package com.java.lab.domain.model.customer;

import com.java.lab.applicationhost.utill.MathUtil;
import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.model.BaseEntity;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class BaseCustomer extends BaseEntity implements Customer {
    private static final BigDecimal DEFAULT_BALANCE = BigDecimal.valueOf(1000.0);

    protected final String name;

    protected final Integer age;

    protected final Sex sex;

    protected BigDecimal cashBalance;

    protected BigDecimal cardBalance;

    protected BigDecimal bonusBalance;

    public BaseCustomer(Integer id, String name, Integer age, Sex sex) throws InvalidModelException {
        this(id, name, age, sex, DEFAULT_BALANCE, DEFAULT_BALANCE, DEFAULT_BALANCE);
    }

    public BaseCustomer(Integer id, String name, Integer age, Sex sex, BigDecimal cashBalance, BigDecimal cardBalance, BigDecimal bonus) throws InvalidModelException {
        super(id);
        this.name = name;
        this.age = age;
        this.sex = sex;
        tryThrow(MathUtil.lessThanZero(cashBalance), "cashBalance must be more than 0");
        this.cashBalance = cashBalance;
        tryThrow(MathUtil.lessThanZero(cardBalance), "cardBalance must be more than 0");
        this.cardBalance = cardBalance;
        tryThrow(MathUtil.lessThanZero(bonus), "bonus must be more than 0");
        this.bonusBalance = bonus;
    }

    public Integer getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void payCash(BigDecimal payAmount) throws PaymentFailedException {
        checkBalance(payAmount, cashBalance);
        this.cashBalance = this.cashBalance.subtract(payAmount);
    }

    private void checkBalance(BigDecimal payAmount, BigDecimal balance) throws PaymentFailedException {
        if (!MathUtil.less(payAmount, balance)) {
            throw new PaymentFailedException("not enough cash balance: " + (payAmount.subtract(balance)));
        }
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void payCard(BigDecimal payAmount) throws PaymentFailedException {
        checkBalance(payAmount, cardBalance);
        this.cardBalance = this.cashBalance.subtract(payAmount);
    }

    public void addBonus(BigDecimal bonusBalance) {
        this.bonusBalance = this.bonusBalance.add(bonusBalance);
    }

    public BigDecimal getBonusBalance() {
        return bonusBalance;
    }

    public void payBonus(BigDecimal payAmount) throws PaymentFailedException {
        checkBalance(payAmount, bonusBalance);
        this.bonusBalance = this.cashBalance.subtract(payAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseCustomer that = (BaseCustomer) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(age, that.age) &&
                sex == that.sex &&
                Objects.equals(cashBalance, that.cashBalance) &&
                Objects.equals(cardBalance, that.cardBalance) &&
                Objects.equals(bonusBalance, that.bonusBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex, cashBalance, cardBalance, bonusBalance);
    }
}
