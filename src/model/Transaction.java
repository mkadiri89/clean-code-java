package model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transaction {
    private String reference;
    private Date date;
    private Money amount;
    private String type;

    public Transaction(String reference, Date date, Money amount, String type)
    {
        this.reference = reference;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public String getReference() {
        return this.reference;
    }

    public Date getDate() {
        return this.date;
    }

    public Money getAmount() {
        return this.amount;
    }

    public String getType() {
        return this.type;
    }

    public Boolean isPayment()
    {
        Matcher matcher = Pattern.compile("PAY([0-9]{6})([A-Z]{2})").matcher(this.reference);
        return matcher.find() ? true : false;
    }
}