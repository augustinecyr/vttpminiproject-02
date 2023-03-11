
import { Component, OnInit } from '@angular/core';

import { UserData } from '../models';



@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  userData!: UserData;

  constructor() { }

  ngOnInit() {

  }

}
