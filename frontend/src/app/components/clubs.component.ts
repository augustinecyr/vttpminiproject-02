import { Component } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { ClubService } from '../clubs.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-clubs',
  templateUrl: './clubs.component.html',
  styleUrls: ['./clubs.component.css']
})
export class ClubsComponent {
  squads$: Observable<any[]> | undefined;
 
  constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer, private clubService: ClubService) {
    this.matIconRegistry.addSvgIcon('manchester-united', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/manchester-united.svg'));
   
  }

  getSquad(id: string) {
    console.log("club id (referred on transfermarkt.com):",id)
    this.squads$ = this.clubService.getSquad(id);
  }

}
