package com.erpsystem.entity.contact;

import com.erpsystem.entity.contact.Contact;
import com.erpsystem.entity.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact save(Contact contact){
        contactRepository.save(contact);
        return contact;
    }

    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Contact findById(String id){
        return contactRepository.findById(id).orElse(null);
    }

    public Contact overwrite(Contact contact){
        Contact overwrittenContact = contactRepository.findById(contact.getId()).orElse(null);
        if(overwrittenContact != null){
            contactRepository.save(contact);
        }
        return overwrittenContact;
    }

    public void deleteAll(){
        contactRepository.deleteAll();
    }

    public void delete(Contact contact){
        contactRepository.delete(contact);
    }

    public Contact deleteById(String id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        contactRepository.deleteById(id);
        return contact;
    }
}
