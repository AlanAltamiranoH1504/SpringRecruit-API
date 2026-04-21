import {Component, inject, signal} from '@angular/core';
import {NavbarSearch} from '../../components/navbar-search/navbar-search';
import {ListVacancies} from '../../../../../vacancy/components/list-vacancies/list-vacancies';
import {RecruiterService} from '../../../../services/recruiter-service';
import {VacancyService} from '../../../../../vacancy/services/vacancy-service';
import {SaveNewVacancy} from '../../../../../vacancy/components/save-new-vacancy/save-new-vacancy';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-recruiter-dashboard-work-page',
  imports: [
    NavbarSearch,
    ListVacancies
  ],
  templateUrl: './recruiter-dashboard-work-page.html',
  styleUrl: './recruiter-dashboard-work-page.css',
})
export class RecruiterDashboardWorkPage {
  protected recruiterService = inject(RecruiterService);
  protected vacancyService = inject(VacancyService);
  protected vacancies = this.vacancyService.vacancies;
  protected errorMessage = signal<string | null>(null);

  ngOnInit() {
    this.vacancyService.refreshVacancies();
    this.recruiterService.listVacanciesByRecruiter({
      token: localStorage.getItem("tokenSpringRecruiter")!,
      idsProgressStatus: [1, 2, 3, 4, 5, 5]
    }).subscribe({
      next: (data) => {
        this.vacancies.set(data);
      },
      error: (error) => {
        this.errorMessage.set(error.message.message);
      }
    });
  }
}
