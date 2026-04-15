import {Component, inject, signal} from '@angular/core';
import {QuickAction} from '../../types/QuickActions';
import {QuickActionsData} from '../../data/quick-actions.data';
import {DomSanitizer} from '@angular/platform-browser';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {SaveNewVacancy} from '../../../vacancy/components/save-new-vacancy/save-new-vacancy';

@Component({
  selector: 'app-quick-actions',
  imports: [MatDialogModule],
  templateUrl: './quick-actions.html',
  styleUrl: './quick-actions.css',
})
export class QuickActions {
  protected quickActions = signal<QuickAction[]>(QuickActionsData);
  protected sanitize = inject(DomSanitizer);

  constructor(private dialog: MatDialog) {
  }

  openModal() {
    this.dialog.open(SaveNewVacancy, {
      width: "700px"
    });
  }
}
