import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable()
export class ClubService{

private baseUrl = 'http://localhost:8080';

constructor(private http: HttpClient) { }

  getSquad(id: string) {
    const url = `${this.baseUrl}/club/squad?id=${id}`;
    console.log("url:", url)
    return this.http.get<any[]>(url);
  }
}
