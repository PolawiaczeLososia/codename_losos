package com.erpsystem.entity.client;

import org.springframework.data.annotation.Id;

public class Client {

    @Id
    private String id;
    private String name;
    private String nip;
    private String regon;
    private String pesel;
    private String firstName;
    private String lastName;
    private boolean isActive;


}
