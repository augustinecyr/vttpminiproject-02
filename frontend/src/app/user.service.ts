import { Injectable } from '@angular/core';
import { GoogleData, UserData } from './models';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    userData: UserData | undefined;
    googleData: GoogleData | undefined;

    setUserData(userData: UserData) {
        this.userData = userData;
    }

    setGoogleData(googleData:GoogleData) {
        this.googleData = googleData;
    }
}
