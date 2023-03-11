import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';



@Component({
  selector: 'app-oauth2',
  templateUrl: './oauth2.component.html',
  styleUrls: ['./oauth2.component.css']
})
export class Oauth2Component implements OnInit {

  code!: string;
  state!: string;
  accessToken!: string;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) {

  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.code = params['code'];

      console.log(this.code);
      if (this.code) {
        this.getToken(this.code).subscribe(response => {
          console.log('Response:', response);
          this.accessToken = response.access_token;
          console.log('Access token:', this.accessToken);
        });
      } else {
        console.log('Code or state parameter is missing');
      }
    });
  }

  getToken(code: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
      Accept: 'application/json'
    });

    const params = new HttpParams()
      .set('code', code);
    console.log(params);

    return this.http.post<any>('http://localhost:8080/login/oauth/access_token', params, { headers });
  }


}
