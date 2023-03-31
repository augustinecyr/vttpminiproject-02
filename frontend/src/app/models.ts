export interface UserData {
    login: string;
    id: string;
    html_url: string;
    avatar_url: string;
}

export interface GoogleData {
    email: string;
    picture: string;
    name: string;
    sub: string;
}

export interface Contact {
	email: string;
	title: string;
	text: string;
    attachment: File;
}

export interface FormResponse {
	formId: string;
}

export interface Club {
    name: string;
    id: string;
    heroImage: string;
}
