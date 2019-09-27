import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StatisticsComponent } from './statistics/statistics.component';
import { AddTransactionComponent } from './add-transaction/add-transaction.component';

const routes: Routes = [
  { path: 'stats', component: StatisticsComponent },
  { path: 'add', component: AddTransactionComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
