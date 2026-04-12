import {Component, signal} from '@angular/core';
import {ActiveJob} from '../../types/ActiveJob';
import {ActiveJobsData} from '../../data/active-jobs.data';

@Component({
  selector: 'app-active-jobs',
  imports: [],
  templateUrl: './active-jobs.html',
  styleUrl: './active-jobs.css',
})
export class ActiveJobs {
  protected activeJobs = signal<ActiveJob[]>(ActiveJobsData);
}
