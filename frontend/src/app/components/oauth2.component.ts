import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { switchMap } from 'rxjs';



@Component({
  selector: 'app-oauth2',
  templateUrl: './oauth2.component.html',
  styleUrls: ['./oauth2.component.css']
})
export class Oauth2Component implements OnInit {
  constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer, private http: HttpClient) {
    this.matIconRegistry.addSvgIcon('google-icon', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/google-icon.svg'));
    this.matIconRegistry.addSvgIcon('github', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/github.svg'));
  }

  google() {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google'
  }

  github() {
    window.location.href = 'http://localhost:8080/oauth2/authorization/github'
  }
  
  ngOnInit() {
   
  }
  
 
  
  

}
