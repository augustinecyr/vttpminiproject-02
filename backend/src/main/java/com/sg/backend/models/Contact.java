package com.sg.backend.models;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class Contact {
    private String email;
    private String title;
    private String text;
    private MultipartFile attachment;
    private byte[] attachmentSQL;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contact() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Contact [email=" + email + ", title=" + title + ", text=" + text + ", attachment=" + attachment
                + ", attachmentSQL=" + Arrays.toString(attachmentSQL) + ", id=" + id + "]";
    }

    public byte[] getAttachmentSQL() {
        return attachmentSQL;
    }

    public void setAttachmentSQL(byte[] attachmentSQL) {
        this.attachmentSQL = attachmentSQL;
    }
}
