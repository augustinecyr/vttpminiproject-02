import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserData } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  userData: UserData | undefined;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userData = this.userService.userData;
    if (!this.userData) {
      this.router.navigate(['/login']);
    }
  }
}
