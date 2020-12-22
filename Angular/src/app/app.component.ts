import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'CouponSystemWebsite';

  condition: String;

  constructor() {
  }

  ngOnInit() : void {
    this.condition = "admin";
  }
}
