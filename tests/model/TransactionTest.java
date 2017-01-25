package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    private Money amount;
    private Date date;

    @BeforeEach
    void setUp() {
        this.amount = Mockito.mock(Money.class);
        this.date = Mockito.mock(Date.class);
    }

    @Test
    void testValidIsPayment() throws Exception {
        Transaction transaction = new Transaction("PAY000001AB", this.date, this.amount, "Debit");
        assertTrue(transaction.isPayment());
    }

    @Test
    void testInvalidIsPayment() throws Exception {
        Transaction transaction = new Transaction("ABC", this.date, this.amount, "Debit");
        assertFalse(transaction.isPayment());
    }
}