import { Component } from '@angular/core'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Jubilant Eureka'
  cards
  private totalCards = 6

  constructor() {
    this.cards = Array.from(new Array(this.totalCards), (x, i) => i + 1)
  }
}
