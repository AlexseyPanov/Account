package ru.t1.app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Account implements Cloneable {

    private String nameClient; // имя владельца
    private Map<CurrencyType, Long> acc = new HashMap<>(); // пара валюта-количество

    public Account(String nameClient) {
        this.nameClient = nameClient;
    }

    public Account(String nameClient, Map<CurrencyType, Long> acc) {
        this.nameClient = nameClient;
        this.acc = acc;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) throws CloneNotSupportedException {
        addHistory(this);
        this.nameClient = nameClient;
    }

    public Map<CurrencyType, Long> getAcc() {
        return acc;
    }

    public Map<CurrencyType, Long> addCurrency(CurrencyType curType, Long sum) {
        return acc;
    }

    public Account addAccount(String nameClient) {
        if (nameClient == null || nameClient == "") {
            return null;
        }
        return new Account(nameClient);
    }

    public void addCur(CurrencyType curType, Long sum) {
        if (sum < 0) {
            this.acc.put(curType, 0L);
        } else {
            this.acc.put(curType, sum);
        }
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // список истории
    private LinkedList<Account> history = new LinkedList<>();

    // добавить задачу в историю
    public void addHistory(Account account) throws CloneNotSupportedException {
        if (account == null) {
            return;
        }
        history.add((Account) account.clone());
    }

    // История изменений
    public List<Account> getHistory() {
        return history;
    }

    public Account undo() throws CloneNotSupportedException {
        if (history.size() == 0) {
            return this;
        }
        Account newAcc = (Account) history.getLast().clone();
        history.remove(history.getLast());
        return newAcc;
    }
    // Сохранение
    private Account safeAccount;
    public final Account safe() throws CloneNotSupportedException {
        if (this == null) {
            return null;
        }
        safeAccount = (Account) this.clone();
        return safeAccount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "nameClient='" + nameClient + '\'' +
                ", acc=" + acc +
                '}';
    }
}
