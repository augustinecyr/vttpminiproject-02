import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { firstValueFrom } from "rxjs"
import {  Contact } from "./models"

@Injectable()
export class ContactService {

	constructor(private http: HttpClient) { }

	postContact(contact: Contact): Promise<Contact> {

		const form = new FormData()
		form.set("email", contact.email)
		form.set("title", contact.title)
		form.set("text", contact.text)
		form.set("attachment", contact.attachment)

		return firstValueFrom(
			this.http.post<Contact>('http://localhost:8080/contact', form)
		)

	}

}