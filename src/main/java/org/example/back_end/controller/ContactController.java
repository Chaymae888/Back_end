package org.example.back_end.controller;


import org.example.back_end.model.Contact;
import org.example.back_end.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.add(contact);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdContact.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdContact);
    }
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAll();
    }

    @DeleteMapping("/{ContactId}")
    public ResponseEntity<Contact> deleteContact(@PathVariable("ContactId") Long id) {
        if (!contactService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{ContactId}")
    public ResponseEntity<Contact> updateContact(@PathVariable("ContactId") Long id,@RequestBody Contact Contact) {
        Contact updatedContact = contactService.update(id, Contact);
        if (updatedContact == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedContact);
    }

}
