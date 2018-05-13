import { Component, OnInit } from '@angular/core'

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {
  title = 'Jubilant Eureka'
  cards
  private totalCards = 6

  constructor() {
    this.cards = Array.from(new Array(this.totalCards), (x, i) => i + 1)
  }

  ngOnInit() {
  }

}
