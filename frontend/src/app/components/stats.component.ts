import { Component, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import { GoogleData, PlayerSQL, Stats, UserData } from '../models';
import { StatsService } from '../stats.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { writeFile } from 'xlsx';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css'],
})
export class StatsComponent implements OnInit {
  stats: Observable<Stats[]> | undefined;
  playerSQL: Observable<PlayerSQL[]> | undefined;
  userData: UserData | undefined;
  googleUserData: GoogleData | undefined;
  constructor(
    private statService: StatsService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit() {
    // checks if user has been authenticated via login before allowing the use of /players
    this.userData = this.userService.userData;
    this.googleUserData = this.userService.googleData;
    if (!this.userData && !this.googleUserData) {
      console.log('No account information exists. Please login');
      this.router.navigate(['/login']);
    } else {
      console.log('User is authenticated');
      this.getListFromSQL();
    }
  }

  getListFromSQL() {
    this.playerSQL = this.statService.getListFromSQL();
    console.log('List:',this.playerSQL);
  }

  getStats(id: string) {
    console.log('Player selected:', id);
    this.stats = this.statService.getStats(id);
    this.stats.subscribe((response) => console.log('Stats:', response));
  }

  deletePlayerById(id: string) {
    console.log('Removing selected player by ID');
    this.playerSQL = this.statService.deletePlayerById(id);
    this.statService.deletePlayerById(id).subscribe(
      () => {
        console.log(
          "Removed player's record successfully from all tables in MySQL database"
        );
        this.router.navigate(['/players']);
      },
      (error) => {
        console.log('Error deleting player:', error);
      }
    );
  }

  toExcel() {
    if (!this.stats) {
      console.log('No stats available');
      return;
    }
    // create the excel format
    const columns = [
      { header: 'Goals', key: 'goals' },
      { header: 'Assists', key: 'assists' },
      { header: 'Yellow Cards', key: 'yellowCards' },
      { header: 'Red Cards', key: 'redCards' },
      { header: 'Clean Sheets', key: 'cleanSheets' },
      { header: 'Conceded Goals', key: 'concededGoals' },
    ];

    this.stats
      .pipe(
        map((stats) =>
          stats.map((s) => ({
            goals: s.goals,
            assists: s.assists,
            yellowCards: s.yellowCards,
            redCards: s.redCards,
            cleanSheets: s.cleanSheets,
            concededGoals: s.concededGoals,
          }))
        )
      )
      .subscribe((statsArr) => {
        console.log('Stats:', statsArr);
        const excelsheet = { columns, rows: statsArr };
        const excelbook = {
          Sheets: { data: excelsheet },
          SheetNames: ['STATS'],
        };

        const fileName = 'stats.xlsx';
        writeFile(excelbook, fileName);
        console.log('Successfully exported excel file');
      });
  }
}
