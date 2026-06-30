import {Component, inject, signal} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {ResponseListVacanciesByRecruiter} from '../../../recuiter/types';
import {VacancyService} from '../../services/vacancy-service';
import {CurrencyPipe, DatePipe, DecimalPipe} from '@angular/common';
import {ActionsData} from '../../data';
import {ActionDetails, Vacancy} from '../../types';
import {CandidateListComponent} from '../../../candidate/components/candidate-list-component/candidate-list-component';
import {MatDialog} from '@angular/material/dialog';
import {EditVacancy} from '../../components/edit-vacancy/edit-vacancy';
import { ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-vacancy-details-page',
  imports: [
    RouterLink,
    DecimalPipe,
    CurrencyPipe,
    DatePipe,
    CandidateListComponent,
    ReactiveFormsModule
  ],
  templateUrl: './vacancy-details-page.html',
  styleUrl: './vacancy-details-page.css',
})
export class VacancyDetailsPage {
  protected vacancyIdPathVariable = inject(ActivatedRoute).snapshot.params["id"];
  protected vacancy = signal<ResponseListVacanciesByRecruiter | null>(null);
  protected vacancyToEdit = signal<Vacancy | null>(null);
  protected vacancyService = inject(VacancyService);
  protected messageError = signal<string | null>(null);
  protected actions = signal<ActionDetails[]>(ActionsData);
  protected activeTab = signal<number>(1);
  protected dialog = inject(MatDialog);

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

  protected showModalEdit(idVacancy: number) {
    this.vacancyService.searchVacancyById(idVacancy).subscribe({
      next: (vacancy) => {
        this.vacancyToEdit.set(vacancy);
        this.dialog.open(EditVacancy, {
          width: "900px",
          data: this.vacancyToEdit() // * PASAMOS DATA DE VACANTE A FORMULARIO MODAL
        });
      },
      error: (error) => {
        alert(error.error.message);
      }
    });
  }

}
