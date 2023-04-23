import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Fixtures, GoogleData, UserData } from '../models';
import { FixturesService } from '../fixtures.service';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css'],
})
export class FixturesComponent implements OnInit {
  fixtures: Observable<Fixtures[]> | undefined;
  defaultDayID: string | undefined;
  userData: UserData | undefined;
  googleUserData: GoogleData | undefined;
  constructor(
    private fixturesService: FixturesService,
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.userData = this.userService.userData;
    this.googleUserData = this.userService.googleData;
    if (!this.userData && !this.googleUserData) {
      console.log('No account information exists. Please login');
      this.router.navigate(['/login']);
    } else {
      console.log('User is authenticated');
      // load the 1st fixture of 2022/23 EPL
      this.defaultDayID = '1';
      this.fixtures = this.fixturesService.getFixtures(this.defaultDayID);
    }
  }

  getFixtures(dayID: string) {
    console.log('Matchweek:', dayID);
    this.fixtures = this.fixturesService.getFixtures(dayID);
  }
}
