import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SaveVacancy} from '../types';
import {environment} from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class VacancyService {
  private http = inject(HttpClient);

  public saveVacancy(vacancy: SaveVacancy) {
    return this.http.post<GeneralResponseAPI>(`${environment.URL_API_BACKEND}/vacancy/save_vacancy`, vacancy, {
      headers: {
        "Authorization": "Bearer " + vacancy.jwt
      }
    });
  }
}
