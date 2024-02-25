package ru.t1.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.t1.app.CurrencyType.*;

public class AccountTest {
    static Account account;
    static Map<CurrencyType, Long> summaZeroRUB = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        account = new Account("test");
        summaZeroRUB.put(RUB, 0L);
    }

    @Test
    void getNameTest() {
        assertEquals("test", account.getNameClient());
    }

    @Test
    void setNameTest() throws CloneNotSupportedException {
        assertEquals("test", account.getNameClient());
        account.setNameClient("new name");
        assertEquals("new name", account.getNameClient());
    }

    @Test
    void addCurTest() {
        account.addCur(RUB, 0L);
        Map<CurrencyType, Long> accAndCur = account.getAcc();
        assertEquals(accAndCur.size(), 1);
    }

    @Test
    void addCurrencyRUBandSummaZeroTest() {
        account.addCur(RUB, 0L);
        assertEquals(account.getAcc(), summaZeroRUB);
    }

    @Test
    void addCurrencyRUBandSumma100_Test() {
        account.addCur(RUB, 100L);
        Map<CurrencyType, Long> accAndCur = account.getAcc();
        summaZeroRUB.put(RUB, 100L);
        assertEquals(accAndCur, summaZeroRUB);
    }

    @Test
    void addCurrencyRUBandSummaMinus100_Test() {
        account.addCur(RUB, -100L);
        assertEquals(account.getAcc(), summaZeroRUB);
    }

    @Test
    void undoTest() throws CloneNotSupportedException {
        account.setNameClient("test new");
        assertEquals(account.getNameClient(), "test new");
        account.setNameClient("test new2");
        assertEquals(account.getNameClient(), "test new2");
        assertEquals(account.undo().getNameClient(), "test new");
    }

    @Test
    void safeTest() throws CloneNotSupportedException {
        account.setNameClient("Mike");
        Account accSafe = account.safe();
        account.setNameClient("Max");
        assertEquals(accSafe.getNameClient(), "Mike");
    }
}
