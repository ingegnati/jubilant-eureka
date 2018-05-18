import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  shouldShow = false
  /*
   * Toggle navbar: @see https://stackoverflow.com/a/44630868/1977778
   */

  constructor() { }

  ngOnInit() {
  }

}
