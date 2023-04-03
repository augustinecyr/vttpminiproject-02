import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { GoogleData } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-googleoauth2',
  templateUrl: './googleoauth2.component.html',
  styleUrls: ['./googleoauth2.component.css']
})
export class Googleoauth2Component implements OnInit {

  googlecode!: string;
  googleToken!: string;
  googleData!: GoogleData;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute, private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.googlecode = params['code'];
      console.log(this.googlecode);
      if (this.googlecode) {
        this.getGoogleToken(this.googlecode).subscribe(response => {
          console.log('Response:', response);
          this.googleToken = response.access_token;
          console.log('Access token:', this.googleToken);
          // post the token using getGoogleToken
          this.getUser(this.googleToken).subscribe(user => {
            console.log('User:', user);
            this.googleData = {
              name: user.name,
              email: user.email,
              picture: user.picture,
              sub: user.sub,
            };
            this.userService.setGoogleData(this.googleData);
            // fake a delay
            setTimeout(() => {
              this.router.navigate(['/account']);
            }, 1500);
            console.log();
          });
        });
      } else {
        console.log('code parameter is invalid or missing');
      }
    });
  }
  // exchange temporary token for access token
  getGoogleToken(code: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
      Accept: 'application/json'
    });

    const params = new HttpParams()
      .set('code', code);
    console.log(params);

    return this.http.post<any>('http://localhost:8080/token', params, { headers });
  }
  // use the google token and get the email & name
  getUser(googleToken: string): Observable<GoogleData> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${googleToken}`,
      Accept: 'application/json'
    });

    return this.http.get<GoogleData>('https://openidconnect.googleapis.com/v1/userinfo', { headers });
  }
}
