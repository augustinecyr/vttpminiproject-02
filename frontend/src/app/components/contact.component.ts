import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContactService } from '../contact.service';
import { Contact } from '../models';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css'],
})
export class ContactComponent implements OnInit {
  @ViewChild('attachment')
  attachment!: ElementRef;

  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private postSvc: ContactService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.form = this.createForm();
  }

  processForm() {
    const contact = this.form.value as Contact;
    contact.attachment = this.attachment.nativeElement.files[0];
    console.log('assembling the form.....');
    console.info('form: ', contact);

    this.postSvc
      .postContact(contact)
      .then((response) => {
        this.form = this.createForm();
      })
      .catch((error) => {
        console.error('error: ', error);
      });
    console.info('form sent to backend');
    this.snackBar.open(
      'Thank you for contacting us, please check your email!',
      'X',
      {
        duration: 5000,
      }
    );
  }

  createForm(): FormGroup {
    return this.fb.group({
      email: this.fb.control('', [Validators.required, Validators.email]),
      title: this.fb.control('', [Validators.required]),
      text: this.fb.control('', [Validators.required, Validators.minLength(2)]),
      attachment: this.fb.control(''),
    });
  }
}
