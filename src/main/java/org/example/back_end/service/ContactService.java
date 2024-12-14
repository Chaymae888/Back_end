package org.example.back_end.service;

import org.example.back_end.model.Contact;
import org.example.back_end.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService{
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;}
    public Contact add(Contact contact) {
        return contactRepository.save(contact);
    }


    public List<Contact> getAll() {
        return contactRepository.findAll();
    }


    public Contact update(long id, Contact updatedItem) {
        if(contactRepository.existsById(id)) {
            updatedItem.setId(id);
            return contactRepository.save(updatedItem);
        }
        else return null;

    }


    public void delete(long id) {
        if(contactRepository.existsById(id)) {
            contactRepository.deleteById(id);

        }
        else{
            System.out.println("Contact with ID " + id + " does not exist.");
        }
    }


    public boolean exists(Long id) {
        return contactRepository.existsById(id);
    }
}
