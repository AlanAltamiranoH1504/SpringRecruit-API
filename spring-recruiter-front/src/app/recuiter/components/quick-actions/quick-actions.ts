import {Component, inject, signal} from '@angular/core';
import {QuickAction} from '../../types/QuickActions';
import {QuickActionsData} from '../../data/quick-actions.data';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-quick-actions',
  imports: [],
  templateUrl: './quick-actions.html',
  styleUrl: './quick-actions.css',
})
export class QuickActions {
  protected quickActions = signal<QuickAction[]>(QuickActionsData);
  protected sanitize = inject(DomSanitizer);
}
