import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { GoogleData, UserData } from './models';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  userData: UserData | undefined;
  googleData: GoogleData | undefined;

  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private http: HttpClient
  ) {}
  setUserData(userData: UserData) {
    this.userData = userData;
  }

  setGoogleData(googleData: GoogleData) {
    this.googleData = googleData;
  }

  logout(): void {
    this.userData = undefined;
    this.googleData = undefined;
    console.log('Clearing user data');
    localStorage.removeItem('userData');
    localStorage.removeItem('googleData');
    console.log('Successfully cleared user data');
    this.snackBar.open('You have successfully logged out!', 'X', {
      duration: 3000,
    });
    this.router.navigate(['/login']);
  }
  // Google OAuth2
  // exchange temporary token for access token
  getGoogleToken(code: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
      Accept: 'application/json',
    });

    const params = new HttpParams().set('code', code);
    console.log(params);

   // return this.http.post<any>('http://localhost:8080/token', params, { headers,});
    return this.http.post<any>('https://footrix.up.railway.app/token', params, { headers,});

  }
  // use the google token and get the email & name
  getGoogleUser(googleToken: string): Observable<GoogleData> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${googleToken}`,
      Accept: 'application/json',
    });

    return this.http.get<GoogleData>(
      'https://openidconnect.googleapis.com/v1/userinfo',
      { headers }
    );
  }

  // Github OAuth2
  getGithubToken(code: string): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
      Accept: 'application/json',
    });

    const params = new HttpParams().set('code', code);
    console.log(params);

   // return this.http.post<any>('http://localhost:8080/login/oauth/access_token',params,{ headers });
    return this.http.post<any>('https://footrix.up.railway.app/login/oauth/access_token',params,{ headers });

  }

  getGithubUser(accessToken: string): Observable<UserData> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${accessToken}`,
      Accept: 'application/json',
    });

    return this.http.get<UserData>('https://api.github.com/user', { headers });
  }
}
