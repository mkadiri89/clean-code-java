
import model.Transaction;
import service.Payments;
import service.StatementParser;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Bootstrap {
    public static void main(String[] args) throws Exception {
        List<Transaction> transactions = (new StatementParser("statement.csv")).parse();
        Map<String, BigDecimal> totals = (new Payments()).getPayments(transactions);

        for(Map.Entry<String, BigDecimal> total : totals.entrySet()) {
            System.out.println(total.getKey() + " " + total.getValue());
        }
    }
}
