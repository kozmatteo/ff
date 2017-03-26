import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-transactions-grid',
  templateUrl: './transactions-grid.component.html',
  styleUrls: ['./transactions-grid.component.css']
})
export class TransactionsGridComponent implements OnInit {

  @Input() transactions: Array<any>;

  constructor() {
  }

  ngOnInit() {
  }

}
