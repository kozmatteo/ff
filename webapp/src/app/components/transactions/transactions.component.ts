import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  private transactions: Array<any> = [
    {
      id: 1,
      amount: 29.45,
      date: 1490478709,
      description: 'wpływ',
      category: 'kategoria 1'
    },
    {
      id: 2,
      amount: -23.45,
      date: 1490392309,
      description: 'wydatek',
      category: 'kategoria x'
    }
  ];

  constructor() {
  }

  ngOnInit() {
  }

  sum() {
    return this.transactions
      .map(t => t.amount)
      .reduce((prev, current) => {
        return prev + current;
      }, 0);
  }

}
