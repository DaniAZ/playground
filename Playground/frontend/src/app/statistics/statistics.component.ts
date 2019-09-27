import { Component, OnInit } from '@angular/core';
import { Statistics } from '../models/statistics.model';
import { Router } from '@angular/router';
import { Service } from '../service.service';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})

export class StatisticsComponent implements OnInit {

  statistics: Statistics;

  constructor(private router: Router, private service: Service) {

  }

  ngOnInit() {
    this.service.getStats()
      .subscribe( data => {
        this.statistics = data;
      });
  };

  deleteTransaction(): void {
    this.service.deleteTransaction()
      .subscribe( data => {
        this.statistics = new Statistics();
      })
  };
}
