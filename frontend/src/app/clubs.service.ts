import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Club } from "./models";

@Injectable()
export class ClubService {

  constructor(private http: HttpClient) { }

  getSquad(id: string) {
    // {id} value get from the button in html
    //const url = `http://localhost:8080/clubs/squad?id=${id}`;
    const url = `https://footrix.up.railway.app/clubs/squad?id=${id}`;
    console.log("url:", url)
    return this.http.get<Club[]>(url);
  }
}
