package com.sg.backend.controllers;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sg.backend.models.Contact;
import com.sg.backend.service.ContactService;
import com.sg.backend.service.EmailService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContactController {

    @Autowired
    private ContactService conSvc;

    @Autowired
    private EmailService emailSvc;

    @PostMapping(path = "/contact", consumes = "multipart/form-data")
    public ResponseEntity<String> postContact(@ModelAttribute Contact form) throws IOException {
        String email = form.getEmail();
        String title = form.getTitle();
        String text = form.getText();
        MultipartFile attachment = form.getAttachment();
        System.out.println("Received form from Angular - email: " + email + ", title: " + title + ", text: " + text
                + ", attachment:" + attachment);
        Contact contact = new Contact();
        // generate a random form ID
        String id = (UUID.randomUUID().toString().substring(0, 8));
        System.out.printf("id: %s\n", id);
        contact.setId(id);
        contact.setEmail(email);
        contact.setTitle(title);
        contact.setText(text);
        // multipart to blob type
        byte[] attachmentBytes = attachment.getBytes();
        contact.setAttachmentSQL(attachmentBytes);
        // print the object Contact
        System.out.println(contact);
        conSvc.createNewEntry(contact);
        System.out.println("Form successfully received from Angular, awaiting HTTP 200 Status");
        // after saving in SQL, dispatch this email
        emailSvc.dispatchEmailPostContactForm(email, "Footrix Service Request:" + id,
                "Thank you for contacting us, Your service request has been successfully submitted with ID number:" + id
                        + ".\n\nWe will reach out to you within 3 working days!");
        return ResponseEntity.ok("");
    }

}
