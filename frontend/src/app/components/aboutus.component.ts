import { Component, OnInit } from '@angular/core';
import { MapService } from '../map.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-aboutus',
  templateUrl: './aboutus.component.html',
  styleUrls: ['./aboutus.component.css'],
})
export class AboutusComponent implements OnInit {
  mapsUrl: SafeResourceUrl | undefined;

  constructor(
    private mapService: MapService,
    // referred to club.component to bypass persistent security
    private domSanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.mapService.getMapsUrl().subscribe((url) => {
      this.mapsUrl = this.domSanitizer.bypassSecurityTrustResourceUrl(url);
    });
  }
}
