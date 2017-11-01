package com.example.jochen.myexpense.model;

import java.util.Calendar;

/**
 * Created by Jochen on 28.10.2017.
 */

// Eine Modelklasse / Datenhalter hat Konstruktoren, Variablen/Fields, Getter und Setter

public class Expense {

    private long id;

    private String amount;
    private String category;
    private Calendar date;

    // Zwei Konstruktoren, Date ist optional
    // Im reduzierten Konstruktor wird der andere mit NULL aufgerufen um nichts zu vergessen

    public Expense(final String amount, final String category) {
        this(amount, category, null);
    }

    public Expense(final String amount, final String category, final Calendar date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
