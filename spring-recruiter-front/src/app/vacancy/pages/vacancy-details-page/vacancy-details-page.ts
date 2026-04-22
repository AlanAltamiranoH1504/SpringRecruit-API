import {Component, inject, signal} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {ResponseListVacanciesByRecruiter} from '../../../recuiter/types';
import {VacancyService} from '../../services/vacancy-service';
import {CurrencyPipe, DatePipe, DecimalPipe} from '@angular/common';
import {ActionsData} from '../../data';
import {ActionDetails} from '../../types';
import {CandidateListComponent} from '../../../candidate/components/candidate-list-component/candidate-list-component';

@Component({
  selector: 'app-vacancy-details-page',
  imports: [
    RouterLink,
    DecimalPipe,
    CurrencyPipe,
    DatePipe,
    CandidateListComponent
  ],
  templateUrl: './vacancy-details-page.html',
  styleUrl: './vacancy-details-page.css',
})
export class VacancyDetailsPage {
  protected vacancyIdPathVariable = inject(ActivatedRoute).snapshot.params["id"];
  protected vacancy = signal<ResponseListVacanciesByRecruiter | null>(null);
  protected vacancyService = inject(VacancyService);
  protected messageError = signal<string | null>(null);
  protected actions = signal<ActionDetails[]>(ActionsData);
  protected activeTab = signal<number>(1);

  ngOnInit() {
    this.vacancyService.getVacancyWithApplications(this.vacancyIdPathVariable).subscribe({
      next: (vacancy) => {
        this.vacancy.set(vacancy);
      },
      error: (error) => {
        this.messageError.set(error.message.message);
      }
    });
  }

  protected changeActiveTab(idButton: number) {
    this.activeTab.set(idButton);
  }
}
