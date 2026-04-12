import {Component, inject, signal} from '@angular/core';
import {Stat} from '../../types/Stat';
import {StatData} from '../../data/sumary-data';
import {DomSanitizer} from '@angular/platform-browser';
import {ActiveJobs} from '../../components/active-jobs/active-jobs';
import {RecentApplications} from '../../components/recent-applications/recent-applications';
import {QuickActions} from '../../components/quick-actions/quick-actions';

@Component({
  selector: 'app-recruiters-dashboard-sumary',
  imports: [
    ActiveJobs,
    RecentApplications,
    QuickActions
  ],
  templateUrl: './recruiters-dashboard-sumary.html',
  styleUrl: './recruiters-dashboard-sumary.css',
})
export class RecruitersDashboardSumary {
  protected statsArray = signal<Stat[]>(StatData);
  protected sanitizer = inject(DomSanitizer);
}
