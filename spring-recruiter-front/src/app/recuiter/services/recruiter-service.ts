import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JWTRecruiter, ResponseListVacanciesByRecruiter, ResponseRecruiterInSession, VacancyByRecruiter} from '../types';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class RecruiterService {
  private http = inject(HttpClient);
  private jwtToken: string = localStorage.getItem("tokenSpringRecruiter")!;

  public listVacanciesByRecruiter(req: JWTRecruiter) {
    return this.http.post<ResponseListVacanciesByRecruiter[]>(`${environment.URL_API_BACKEND}/vacancy/list/by_recruiter`, req, {
      headers: {
        "Authorization": "Bearer " + this.jwtToken
      }
    });
  }

  public recruiterInSession(req: JWTRecruiter) {
    return this.http.post<ResponseRecruiterInSession>(`${environment.URL_API_BACKEND}/recruiter/recruiter_in_session`, req, {
      headers: {
        "Authorization": "Bearer " + this.jwtToken
      }
    });
  }
}
