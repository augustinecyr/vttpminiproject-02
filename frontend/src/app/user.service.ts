import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { GoogleData, UserData } from './models';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    userData: UserData | undefined;
    googleData: GoogleData | undefined;

    constructor(private router: Router, private snackBar: MatSnackBar) { }
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

}
