import {Component, computed, input, signal} from '@angular/core';
import {ResponseListVacanciesByRecruiter} from '../../../recuiter/types';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {getTimeAgo} from '../../../utils/date-utils';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-list-vacancies',
  imports: [
    RouterLink,
    MatPaginator
  ],
  templateUrl: './list-vacancies.html',
  styleUrl: './list-vacancies.css',
})
export class ListVacancies {
  public vacancies = input.required<ResponseListVacanciesByRecruiter[]>();
  protected pageSize = signal<number>(12);
  protected from = signal<number>(0);

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

  protected displayedVacancies = computed(() => {
    const start = this.from();
    const end = start + this.pageSize();
    return this.vacancies().slice(start, end);
  });

  protected changePage(e: PageEvent) {
    this.from.set(e.pageIndex * e.pageSize);
    this.pageSize.set(e.pageSize);
  }

  protected readonly getTimeAgo = getTimeAgo;
}
