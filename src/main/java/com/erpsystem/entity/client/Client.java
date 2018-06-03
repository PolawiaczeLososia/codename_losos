package com.erpsystem.entity.client;

import com.erpsystem.entity.address.Address;
import com.erpsystem.entity.contact.Contact;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "client")
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

    private Address address;
    private List<Contact> contacts;

    public Client() {
    }

    public Client(String name, String nip, String regon, String pesel, String firstName, String lastName, boolean isActive, Address address, List<Contact> contacts) {
        this.name = name;
        this.nip = nip;
        this.regon = regon;
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.address = address;
        this.contacts = contacts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact){
        this.contacts.add(contact);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nip='" + nip + '\'' +
                ", regon='" + regon + '\'' +
                ", pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", address=" + address +
                ", contacts=" + contacts +
                '}';
    }
}
