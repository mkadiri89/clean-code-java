package service;

import model.Money;
import model.Transaction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payments {
    /**
     * Returns a list of total payments by currency
     *
     * @param transactions a list of transactions
     * @return a map of currencies with their totals
     */
    public Map<String, BigDecimal> getPayments(List<Transaction> transactions)
    {
        Map<String, BigDecimal> totals = new HashMap<>();

        for (Transaction transaction : transactions) {
            if(!transaction.isPayment()) {
                continue;
            }

            Money amount = transaction.getAmount();
            String currency = amount.getCurrency();

            BigDecimal currentValue = totals.containsKey(currency) ? totals.get(currency) : new BigDecimal(0);
            totals.put(currency, currentValue.add(amount.getValue()));
        }

        return totals;
    }
}