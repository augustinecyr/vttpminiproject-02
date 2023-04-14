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
import { StatsService } from './stats.service';
import { StatsComponent } from './components/stats.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
const appRoutes: Routes = [
  { path: '', component: MainComponent },
  // e.g. using :email makes <email> dynamic
  { path: 'login/oauth/access_token', component: GithubOauth2Component },
  { path: 'token', component: Googleoauth2Component },
  { path: 'login', component: LoginComponent },
  { path: 'account', component: AccountComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'clubs', component: ClubsComponent },
  { path: 'clubs/squad', component: ClubsComponent },
  { path: 'players/stats', component: StatsComponent },
  { path: 'players', component: StatsComponent },
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
    StatsComponent,
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
    MatFormFieldModule,
    MatSidenavModule,
    MatTableModule,
    MatDividerModule,
    MatListModule,
    MatGridListModule,
  ],
  providers: [UserService, ContactService, ClubService, StatsService],
  bootstrap: [AppComponent],
})
export class AppModule {}
