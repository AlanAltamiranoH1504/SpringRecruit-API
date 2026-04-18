import {Component, inject, signal} from '@angular/core';
import {ActiveJob} from '../../types/ActiveJob';
import {ActiveJobsData} from '../../data/active-jobs.data';
import {RecruiterService} from '../../services/recruiter-service';
import {ResponseListVacanciesByRecruiter, VacancyByRecruiter} from '../../types';
import {getTimeAgo} from '../../../utils/date-utils';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-active-jobs',
  imports: [
    RouterLink
  ],
  templateUrl: './active-jobs.html',
  styleUrl: './active-jobs.css',
})
export class ActiveJobs {
  protected recruiterService = inject(RecruiterService);
  protected activeJobs = signal<ResponseListVacanciesByRecruiter[]>([]);
  protected isError = signal<string | null>(null);

  ngOnInit(): void {
    const token = localStorage.getItem("tokenSpringRecruiter");
    this.recruiterService.listVacanciesByRecruiter({token: token!, idsProgressStatus: [1, 3]})
      .subscribe({
        next: (next) => {
          this.activeJobs.set(next);
        },
        error: (error) => {
          this.isError.set(error.error.message);
        }
      });
  }

  protected getStatusVacancy(idProgressStatus: number) {
    switch (idProgressStatus) {
      case 1: // * Inicio
        return "bg-green-50 text-green-700 hover:bg-green-100 p-2";
      case 2: // * Postulaciones Cerradas
        return "bg-red-50 text-red-700 hover:bg-red-100 p-2";
      case 3: // * Evaluación CVs
        return "bg-blue-50 text-blue-700 hover:bg-blue-100 p-2";
      case 4: // * Entrevistas
        return "bg-purple-50 text-purple-700 hover:bg-purple-100 p-2";
      case 5: // * Elección candidato
        return "bg-yellow-50 text-yellow-700 hover:bg-yellow-100 p-2";
      case 6: // * Concluida
        return "bg-gray-100 text-gray-700 hover:bg-gray-200 p-2";
      default:
        return "bg-gray-50 text-gray-500 p-2";
    }
  }

  protected readonly getTimeAgo = getTimeAgo;
}
