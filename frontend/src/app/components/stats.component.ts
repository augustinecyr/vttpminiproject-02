import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { GoogleData, PlayerSQL, Stats, UserData } from '../models';
import { StatsService } from '../stats.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

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
    console.log('fetching the list of players from MySQL');
    this.playerSQL = this.statService.getListFromSQL();
  }

  getStats(id: string) {
    console.log('getting stats for this player', id);
    this.stats = this.statService.getStats(id);
    this.stats.subscribe((response) => console.log('Stats:', response));
  }

  deletePlayerById(id: string) {
    console.log('Removing player by ID');
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
}
