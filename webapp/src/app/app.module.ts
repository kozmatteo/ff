import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {TransactionsComponent} from './components/transactions/transactions.component';
import {RouterModule, Routes} from '@angular/router';
import {TransactionsGridComponent} from './components/transactions-grid/transactions-grid.component';

const routeConfig: Routes = [
  {path: '', redirectTo: 'transactions', pathMatch: 'full'},
  {path: 'transactions', component: TransactionsComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    TransactionsComponent,
    TransactionsGridComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(routeConfig),
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
