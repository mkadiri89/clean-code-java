package service;

import model.Money;
import model.Transaction;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatementParser {
    private Scanner scannerFile;

    public StatementParser(String fileName) throws FileNotFoundException {
        this.scannerFile = new Scanner(new File("input/" + fileName));
    }

    public List<Transaction> parse() throws Exception {
        Map header = this.getHeader();
        List<Transaction> transactions = new ArrayList<>();

        while (scannerFile.hasNextLine()) {
            String[] columns = scannerFile.nextLine().split(",");

            Boolean isCredit = !columns[(Integer) header.get("credit")].isEmpty();
            String type = isCredit ? "Credit" : "Debit";
            BigDecimal value = isCredit
                ? new BigDecimal(columns[(Integer) header.get("credit")])
                : new BigDecimal(columns[(Integer) header.get("debit")]);

            Date transactionDate = (new SimpleDateFormat("d/M/yyyy")).parse(columns[(Integer) header.get("date")]);

            transactions.add(new Transaction(
                columns[(Integer) header.get("narrative_1")],
                transactionDate,
                new Money(value, columns[(Integer) header.get("currency")]),
                type
            ));
        }

        return transactions;
    }

    private Map<String, Integer> getHeader() {
        this.scannerFile.useDelimiter(",");
        String headerLine = this.scannerFile.nextLine();
        String[] headerUnformatted = headerLine.split(",");
        Map<String, Integer> header = new HashMap<>();

        for(int i = 0; i < headerUnformatted.length; i++) {
            header.put(headerUnformatted[i].replace(" ", "_").toLowerCase(), i);
        }

        return header;
    }
}