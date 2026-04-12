import {Component, inject, signal} from '@angular/core';
import {ActiveJob} from '../../types/ActiveJob';
import {ActiveJobsData} from '../../data/active-jobs.data';
import {RecruiterService} from '../../services/recruiter-service';
import {ResponseListVacanciesByRecruiter, VacancyByRecruiter} from '../../types';
import {getTimeAgo} from '../../../utils/date-utils';

@Component({
  selector: 'app-active-jobs',
  imports: [],
  templateUrl: './active-jobs.html',
  styleUrl: './active-jobs.css',
})
export class ActiveJobs {
  protected recruiterService = inject(RecruiterService);
  protected activeJobs = signal<ResponseListVacanciesByRecruiter[]>([]);
  protected isError = signal<string | null>(null);

  ngOnInit(): void {
    const token = localStorage.getItem("tokenSpringRecruiter");
    this.recruiterService.listVacanciesByRecruiter({token: token!})
      .subscribe({
        next: (next) => {
          this.activeJobs.set(next);
        },
        error: (error) => {
          this.isError.set(error.error.message);
        }
      });
  }

  protected readonly getTimeAgo = getTimeAgo;
}
