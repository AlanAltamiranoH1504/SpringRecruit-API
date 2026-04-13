import {Component, inject, signal} from '@angular/core';
import {RecentApplication} from '../../types/RecentApplicartion';
import {RecentApplicationsData} from '../../data/recent-applications.data';
import {ApplicationsByRecruiter} from '../../types';
import {RecruiterService} from '../../services/recruiter-service';

@Component({
  selector: 'app-recent-applications',
  imports: [],
  templateUrl: './recent-applications.html',
  styleUrl: './recent-applications.css',
})
export class RecentApplications {
  protected recruiterService = inject(RecruiterService);
  protected recenteApplicatios = signal<ApplicationsByRecruiter[]>([]);
  protected isError = signal<string | null>(null);

  ngOnInit() {
    this.recruiterService.applicationsByRecruiter({token: localStorage.getItem("tokenSpringRecruiter")!}).subscribe({
      next: (next) => {
        this.recenteApplicatios.set(next);
      },
      error: (error) => {
        this.isError.set(error.error.message);
      }
    });
  }

  protected rejectApplication(idApplication: number) {
    console.log("Eliminando aplicacio");
  }
}
