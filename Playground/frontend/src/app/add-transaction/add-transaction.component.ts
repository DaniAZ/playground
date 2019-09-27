import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Transaction } from '../models/transaction.model';
import { Service } from '../service.service';
@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-transaction.component.html',
  styleUrls: ['./add-transaction.component.css']
})
export class AddTransactionComponent  {

    transaction: Transaction = new Transaction();
  
    constructor(private router: Router, private transactionService: Service) {
  
    }
  
    createTransaction(): void {
      this.transactionService.createTransaction(this.transaction)
          .subscribe( data => {
            alert("Transaction created successfully.");
          });
  
    };
  
  }