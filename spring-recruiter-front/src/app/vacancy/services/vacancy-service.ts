import {inject, Injectable, signal} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SaveVacancy} from '../types';
import {environment} from '../../../environments/environment.development';
import {ResponseListVacanciesByRecruiter, VacanciesByRecruiter} from '../../recuiter/types';

@Injectable({
  providedIn: 'root',
})
export class VacancyService {
  private http = inject(HttpClient);
  private jwtToken: string = localStorage.getItem("tokenSpringRecruiter")!;
  public vacancies = signal<ResponseListVacanciesByRecruiter[]>([]);

  public refreshVacancies() {
    return this.http.post<ResponseListVacanciesByRecruiter[]>(`${environment.URL_API_BACKEND}/vacancy/list/by_recruiter`,
      {token: this.jwtToken, idsProgressStatus: [1, 2, 3, 4, 5, 6]}, {
        headers: {
          "Authorization": "Bearer " + this.jwtToken
        }
      }).subscribe(data => this.vacancies.set(data));
  }

  public saveVacancy(vacancy: SaveVacancy) {
    return this.http.post<GeneralResponseAPI>(`${environment.URL_API_BACKEND}/vacancy/save_vacancy`, vacancy, {
      headers: {
        "Authorization": "Bearer " + vacancy.jwt
      }
    });
  }

  public getVacancyWithApplications(idVacancy: number) {
    return this.http.post<ResponseListVacanciesByRecruiter>(`${environment.URL_API_BACKEND}/vacancy/get_vacancy/${idVacancy}`, {token: this.jwtToken}, {
      headers: {
        "Authorization": "Bearer " + this.jwtToken
      }
    });
  }

  public getColorStatus(idProgressStatus: number): string {
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
}
