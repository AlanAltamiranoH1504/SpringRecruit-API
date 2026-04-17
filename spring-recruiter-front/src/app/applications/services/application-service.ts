import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {CandidateByRecruiterVacancy} from '../types';

@Injectable({
  providedIn: 'root',
})
export class ApplicationService {
  private http = inject(HttpClient);
  private tokenJWT = localStorage.getItem("tokenSpringRecruiter");

  public findAllCandidatesByRecruiterVacancies() {
    return this.http.post<CandidateByRecruiterVacancy[]>(`${environment.URL_API_BACKEND}/application/find_all/by_recruiter`, {token: this.tokenJWT}, {
      headers: {
        "Authorization": "Bearer " + this.tokenJWT
      }
    });
  }
}
