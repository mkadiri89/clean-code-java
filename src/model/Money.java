package model;

import java.math.BigDecimal;

public class Money {
    private BigDecimal value;
    private String currency;

    public Money(BigDecimal value, String currency)
    {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue()
    {
        return value;
    }

    public String getCurrency()
    {
        return currency;
    }
}