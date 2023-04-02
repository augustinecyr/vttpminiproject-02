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
  squads: Observable<any[]> | undefined;

  constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer, private clubService: ClubService) {
    this.matIconRegistry.addSvgIcon('manchester-united', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/manchester-united.svg'));
    this.matIconRegistry.addSvgIcon('manchester-city', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/manchester-city.svg'));
    this.matIconRegistry.addSvgIcon('arsenal', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/arsenal.svg'));
    this.matIconRegistry.addSvgIcon('chelsea', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/chelsea.svg'));
    this.matIconRegistry.addSvgIcon('liverpool', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/liverpool.svg'));
    this.matIconRegistry.addSvgIcon('tottenham', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/tottenham.svg'));
    this.matIconRegistry.addSvgIcon('newcastle-united', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/newcastle-united.svg'));
    this.matIconRegistry.addSvgIcon('westham', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/westham.svg'));
    this.matIconRegistry.addSvgIcon('leicester-city', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/leicester-city.svg'));
    this.matIconRegistry.addSvgIcon('astonvilla', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/astonvilla.svg'));
    this.matIconRegistry.addSvgIcon('wolves', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/wolves.svg'));
    this.matIconRegistry.addSvgIcon('southampton', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/southampton.svg'));
    this.matIconRegistry.addSvgIcon('brighton', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/brighton.svg'));
    this.matIconRegistry.addSvgIcon('everton', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/everton.svg'));
    this.matIconRegistry.addSvgIcon('nottinghamforest', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/nottinghamforest.svg'));
    this.matIconRegistry.addSvgIcon('brentford', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/brentford.svg'));
    this.matIconRegistry.addSvgIcon('leedsunited', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/leedsunited.svg'));
    this.matIconRegistry.addSvgIcon('crystalpalace', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/crystalpalace.svg'));
    this.matIconRegistry.addSvgIcon('fulham', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/fulham.svg'));
    this.matIconRegistry.addSvgIcon('bournemouth', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/bournemouth.svg'));
  }

  getSquad(id: string) {
    console.log("club id (referred on transfermarkt.com):", id)
    this.squads = this.clubService.getSquad(id);
  }

}
