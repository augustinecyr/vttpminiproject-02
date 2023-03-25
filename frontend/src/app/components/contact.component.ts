import { Component, ElementRef, ViewChild } from '@angular/core';
import { Form, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ContactService } from '../contact.service';
import { Contact } from '../models';


@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})

export class ContactComponent {

  @ViewChild('attachment')
	attachment!: ElementRef

	form!: FormGroup

	constructor(private fb: FormBuilder, private postSvc: ContactService) { }

	ngOnInit(): void {
		this.form = this.createForm()
	}

	processForm() {
		const contact = this.form.value as Contact
		contact.attachment = this.attachment.nativeElement.files[0]

		console.info('>>> form: ', contact)

		this.postSvc.postContact(contact)
			.then(response => {
			//	console.info(`form id: ${response.formId}`)
				this.form = this.createForm()
			})
			.catch(error => {
				console.error('>>> error: ', error)
			})
	}

	createForm(): FormGroup {
		return this.fb.group({
			email: this.fb.control('', [ Validators.required, Validators.email ]),
			title: this.fb.control('', [ Validators.required ]),
			text: this.fb.control('', [ Validators.required, Validators.minLength(2) ]),
			attachment: this.fb.control('')
		})
	}
}


