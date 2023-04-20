import { Component } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private matIconRegistry: MatIconRegistry, private domSanitizer: DomSanitizer) {
    this.matIconRegistry.addSvgIcon('google-icon', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/google-icon.svg'));
    this.matIconRegistry.addSvgIcon('github', this.domSanitizer.bypassSecurityTrustResourceUrl('../../../assets/github.svg'));
  }

  google() {
   // window.location.href = 'http://localhost:8080/oauth2/authorization/google'
    window.location.href = 'https://vttpminiproject-02-augustine.up.railway.app/oauth2/authorization/google'

  }

  github() {
 //   window.location.href = 'http://localhost:8080/oauth2/authorization/github'
  window.location.href = 'https://vttpminiproject-02-augustine.up.railway.app/oauth2/authorization/github'

  }
}
