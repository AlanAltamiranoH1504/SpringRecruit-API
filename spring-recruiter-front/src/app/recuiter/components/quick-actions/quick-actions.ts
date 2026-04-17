import {Component, inject, signal} from '@angular/core';
import {QuickAction} from '../../types/QuickActions';
import {QuickActionsData} from '../../data/quick-actions.data';
import {DomSanitizer} from '@angular/platform-browser';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {SaveNewVacancy} from '../../../vacancy/components/save-new-vacancy/save-new-vacancy';
import {SendMessageToCandidate} from '../../../vacancy/components/send-message-to-candidate/send-message-to-candidate';

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

  protected openModal(idButton: number) {
    this.selectModal(idButton);
  }

  protected selectModal(idModal: number) {
    switch (idModal) {
      case 1:
        this.dialog.open(SaveNewVacancy, {
          width: "900px"
        });
        break;
      case 2:
        this.dialog.open(SendMessageToCandidate, {
          width: "900px"
        });
        break;
      default:
        alert("Modal no autorizado");
        break;
    }
  }
}
