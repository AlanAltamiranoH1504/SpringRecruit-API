import {Component, inject, signal} from '@angular/core';
import {DashboardSidebarRecruiter} from '../../components/dashboard-sidebar-recruiter/dashboard-sidebar-recruiter';
import {DashboardHeaderRecruiter} from '../../components/dashboard-header-recruiter/dashboard-header-recruiter';
import {RouterOutlet} from '@angular/router';
import {RecruiterService} from '../../services/recruiter-service';
import {ResponseRecruiterInSession} from '../../types';

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
  protected recruiterService = inject(RecruiterService);
  protected recruiterData = signal<ResponseRecruiterInSession | null>(null);

  ngOnInit() {
    this.recruiterService.recruiterInSession({token: localStorage.getItem("tokenSpringRecruiter")!}).subscribe({
      next: (next) => {
        this.recruiterData.set(next);
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  protected sideBarOpen = signal<boolean>(false);

  protected setSideBarOpen() {
    console.log("Cambiando el estado");
  }
}
