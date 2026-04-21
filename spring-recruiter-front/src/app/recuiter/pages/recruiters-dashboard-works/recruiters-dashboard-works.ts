import {Component} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {SaveNewVacancy} from '../../../vacancy/components/save-new-vacancy/save-new-vacancy';
import {ReactiveFormsModule} from '@angular/forms';
import {RouterLink, RouterOutlet} from '@angular/router';
import {CommonModule} from '@angular/common';
import {MatPaginatorModule} from '@angular/material/paginator';

@Component({
  selector: 'app-recruiters-dashboard-works',
  imports: [
    ReactiveFormsModule,
    CommonModule,
    MatPaginatorModule,
    RouterOutlet
  ],
  templateUrl: './recruiters-dashboard-works.html',
  styleUrl: './recruiters-dashboard-works.css',
})
export class RecruitersDashboardWorks {
  constructor(private dialog: MatDialog) {
  }

  protected openModal() {
    this.dialog.open(SaveNewVacancy, {
      width: "900px"
    });
  }
}
