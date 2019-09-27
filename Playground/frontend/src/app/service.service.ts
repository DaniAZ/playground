import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Statistics } from './models/statistics.model';
import { Transaction } from './models/transaction.model';

@Injectable({
  providedIn: 'root'
})
@Injectable()
export class Service {

  constructor(private http:HttpClient) {}

  private url = 'http://localhost:8080/api';

  public getStats() {
    return this.http.get<Statistics>(this.url + "/getstats");
  }

  public deleteTransaction() {
    return this.http.delete(this.url + "/delete");
  }

  public createTransaction(transaction) {
    return this.http.post<Transaction>(this.url+ "/save", transaction);
  }

}