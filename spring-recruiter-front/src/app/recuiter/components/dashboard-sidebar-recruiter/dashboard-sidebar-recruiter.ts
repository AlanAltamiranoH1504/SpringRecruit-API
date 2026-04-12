import {Component, inject} from '@angular/core';
import {navigateData} from '../../data/navigate-data';
import {DomSanitizer} from '@angular/platform-browser';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'app-dashboard-sidebar-recruiter',
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './dashboard-sidebar-recruiter.html',
  styleUrl: './dashboard-sidebar-recruiter.css',
})
export class DashboardSidebarRecruiter {
  protected  navigateData = navigateData;
  protected sanitize = inject(DomSanitizer);

}
