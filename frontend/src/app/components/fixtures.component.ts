import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Fixtures } from '../models';
import { FixturesService } from '../fixtures.service';

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css'],
})
export class FixturesComponent implements OnInit {
  fixtures: Observable<Fixtures[]> | undefined;
  defaultDayID: string | undefined;
  constructor(
    private fixturesService: FixturesService
  ) {}

  ngOnInit() {
    // load the 1st fixture of 2022/23 EPL
    this.defaultDayID = '1';
    this.fixtures = this.fixturesService.getFixtures(this.defaultDayID);
  }

  getFixtures(dayID: string) {
    console.log('Matchweek:', dayID);
    this.fixtures = this.fixturesService.getFixtures(dayID);
  }
}
