package com.sg.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.backend.models.Contact;
import com.sg.backend.repositories.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository conRepo;

    public void createNewEntry(Contact contact) {
        conRepo.insert(contact);
    }
}
