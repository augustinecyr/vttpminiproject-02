import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { PlayerSQL, Stats } from "./models";

@Injectable()
export class StatsService {

    constructor(private http: HttpClient) { }

    getStats(id: String) {
        const url = `http://localhost:8080/players/stats?id=${id}`;
        console.log("url", url)
        return this.http.get<Stats[]>(url);
    }

    getListFromSQL() {
        const url = `http://localhost:8080/players`;
        console.log("url", url)
        return this.http.get<PlayerSQL[]>(url);
    }
}