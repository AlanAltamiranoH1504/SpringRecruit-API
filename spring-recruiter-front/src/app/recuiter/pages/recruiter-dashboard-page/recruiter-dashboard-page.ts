import { Component } from '@angular/core';
import {DashboardSidebarRecruiter} from '../../components/dashboard-sidebar-recruiter/dashboard-sidebar-recruiter';

@Component({
  selector: 'app-recruiter-dashboard-page',
  imports: [
    DashboardSidebarRecruiter
  ],
  templateUrl: './recruiter-dashboard-page.html',
  styleUrl: './recruiter-dashboard-page.css',
})
export class RecruiterDashboardPage {

}
