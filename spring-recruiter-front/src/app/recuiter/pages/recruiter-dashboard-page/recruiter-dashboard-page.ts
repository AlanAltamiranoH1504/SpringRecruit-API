import {Component, signal} from '@angular/core';
import {DashboardSidebarRecruiter} from '../../components/dashboard-sidebar-recruiter/dashboard-sidebar-recruiter';
import {DashboardHeaderRecruiter} from '../../components/dashboard-header-recruiter/dashboard-header-recruiter';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-recruiter-dashboard-page',
  imports: [
    DashboardSidebarRecruiter,
    DashboardHeaderRecruiter,
    RouterOutlet
  ],
  templateUrl: './recruiter-dashboard-page.html',
  styleUrl: './recruiter-dashboard-page.css',
})
export class RecruiterDashboardPage {
  protected sideBarOpen = signal<boolean>(false);

  protected setSideBarOpen() {
    console.log("Cambiando el estado");
  }
}
