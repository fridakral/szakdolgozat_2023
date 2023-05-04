import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-right-nav',
  templateUrl: './right-nav.component.html',
  styleUrls: ['./right-nav.component.scss']
})
export class RightNavComponent implements OnInit {
  nav: string = "";

  constructor(private router: Router) {

  }

  ngOnInit() {
    this.nav = this.router.url
  }

  users() {
    this.router.navigate(['userData']).then()
  }

  navToProjects() {

  }

  navToTodo() {
    this.router.navigate(['todoNav']).then()
  }
}
