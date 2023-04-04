package com.sg.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sg.backend.models.Contact;

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate template;

    public void insert(Contact contact) {
        Object[] params = new Object[] {
                contact.getId(),
                contact.getEmail(),
                contact.getTitle(),
                contact.getText(),
                // this is the blob getter for specifically mySQL
                contact.getAttachmentSQL()
        };
        template.update(Queries.SQL_INSERT_CONTACT, params);
    }
}
