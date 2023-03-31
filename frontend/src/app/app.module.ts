import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { GithubOauth2Component } from './components/githuboauth2.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MainComponent } from './components/main.component';
import { RouterModule, Routes } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AccountComponent } from './components/account.component';
import { LoginComponent } from './components/login.component';
import { UserService } from './user.service';
import { MatCardModule } from '@angular/material/card';
import { Googleoauth2Component } from './components/googleoauth2.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatMenuModule } from '@angular/material/menu';
import { ContactComponent } from './components/contact.component';
import { ContactService } from './contact.service';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ClubsComponent } from './components/clubs.component';
import { ClubService } from './clubs.service';

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  // e.g. using :email makes <email> dynamic
  { path: 'login/oauth/access_token', component: GithubOauth2Component },
  { path: 'token', component: Googleoauth2Component },
  { path: 'login', component: LoginComponent },
  { path: 'account', component: AccountComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'club', component: ClubsComponent },
  { path: 'club/squad', component: ClubsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
  declarations: [
    AppComponent,
    GithubOauth2Component,
    MainComponent,
    AccountComponent,
    LoginComponent,
    Googleoauth2Component,
    ContactComponent,
    ClubsComponent,
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatCardModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatMenuModule,
    MatInputModule,
    MatFormFieldModule
    
  ],
  providers: [UserService,ContactService,ClubService],
  bootstrap: [AppComponent]
})
export class AppModule { }
