import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit, AfterViewInit {

  @Input() columns?: string[];
  @Input() rows?: any[];
  @ViewChild(MatPaginator) paginator?: MatPaginator;
  public dataSource = new MatTableDataSource<any>();

  constructor() {
  }

  ngAfterViewInit(): void {
    if (this.paginator != null) {
      this.dataSource.paginator = this.paginator;
    }


  }

  ngOnInit(): void {
    this.dataSource.data = this.rows as any[];
  }
}
