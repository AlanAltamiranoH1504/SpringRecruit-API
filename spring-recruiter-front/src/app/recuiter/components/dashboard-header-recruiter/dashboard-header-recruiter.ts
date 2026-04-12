import {Component, inject} from '@angular/core';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-dashboard-header-recruiter',
  imports: [
    RouterLink
  ],
  templateUrl: './dashboard-header-recruiter.html',
  styleUrl: './dashboard-header-recruiter.css',
})
export class DashboardHeaderRecruiter {
  protected router = inject(Router);

  protected logOut() {
    localStorage.removeItem("tokenSpringRecruiter");
    this.router.navigate(["/home/recruiter"]);
  }
}
