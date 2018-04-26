package com.erpsystem.global;

public enum Unit {
    PIECE("pc"),
    LITER("l"),
    KILOGRAM("kg"),
    METER("m");

    private String shortName;

    Unit(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString(){

        return shortName;
    }
}
