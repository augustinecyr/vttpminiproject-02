import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { PlayerSQL, Stats } from '../models';
import { StatsService } from '../stats.service';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent {
  stats: Observable<Stats[]> | undefined;
  playerSQL: Observable<PlayerSQL[]> | undefined;

  constructor(private statService: StatsService) { }

  ngOnInit() {
    this.getListFromSQL();
  }

  getListFromSQL() {
    console.log("fetching the list of players from MySQL")
    this.playerSQL = this.statService.getListFromSQL();
  }

  getStats(id: string){
    console.log("getting stats for this player", id)
    this.stats = this.statService.getStats(id);
    this.stats.subscribe(response => console.log("Stats:",response));
  }
  
}
