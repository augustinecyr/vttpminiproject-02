import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class MapService {
  constructor(private http: HttpClient) {}

  // retrieve the url built with api key from backend
  getMapsUrl() {
    return this.http.get('http://localhost:8080/googlemap', {
      responseType: 'text',
    });
  }
}
