package com.example.MyFinance.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "account_record")
@Entity

public class AccountRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String category;
    private int amount;
    private String note;
    @Column(name = "record_day", columnDefinition = "DATE")
    private LocalDate day;


    public AccountRecord() {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public LocalDate getDay() { return day; }
    public void setDay(LocalDate day) { this.day = day; }

    public Long getId() {return id;}

    @Override
    public String toString() {
        return "AccountRecord{" +
                "type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", day=" + day +
                '}';
    }
}
