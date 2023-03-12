import { Injectable } from '@angular/core';
import { UserData } from './models';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    userData: UserData | undefined;

    setUserData(userData: UserData) {
        this.userData = userData;
    }
}
