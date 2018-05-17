import { Component, OnInit, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-warehouse-view',
  templateUrl: './warehouse-view.component.html',
  styleUrls: ['./warehouse-view.component.css']
})
export class WarehouseViewComponent implements OnInit, AfterViewInit {

  isWarehouse: boolean;
  constructor(private location: ActivatedRoute) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    if (this.location.routeConfig.path === 'warehouse') {
      this.isWarehouse = true;
    }

  }

}
