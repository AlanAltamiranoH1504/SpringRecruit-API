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
      {token: this.jwtToken, idsProgressStatus: [1,2,3,4,5,6]}, {
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
}
