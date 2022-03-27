package com.shf.app15_sqlite;

/**
 * black_number 表对应的实体类
 */
public class BlackNumber {
    private int id;
    private String number;

    public BlackNumber() {
    }

    public BlackNumber(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BlackNumber{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
