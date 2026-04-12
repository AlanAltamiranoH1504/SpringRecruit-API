import {Component, signal} from '@angular/core';
import {RecentApplication} from '../../types/RecentApplicartion';
import {RecentApplicationsData} from '../../data/recent-applications.data';

@Component({
  selector: 'app-recent-applications',
  imports: [],
  templateUrl: './recent-applications.html',
  styleUrl: './recent-applications.css',
})
export class RecentApplications {
  protected recenteApplicatios = signal<RecentApplication[]>(RecentApplicationsData);
}
