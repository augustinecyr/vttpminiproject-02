import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Fixtures } from './models';

@Injectable()
export class FixturesService {
  constructor(private http: HttpClient) {}

  getFixtures(dayID: string) {
 //   const url = `http://localhost:8080/fixtures/matchweek?dayID=${dayID}`;
 const url = `https://vttpminiproject-02-augustine.up.railway.app/fixtures/matchweek?dayID=${dayID}`;
    console.log("url:", url)
    return this.http.get<Fixtures[]>(url);
  }
}
