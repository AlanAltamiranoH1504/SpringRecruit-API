import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {
  ApplicationsByRecruiter,
  JWTRecruiter, ResponseListVacanciesByRecruiter, ResponseRecruiterInSession, VacancyByRecruiter
} from '../types';
import {environment} from '../../../environments/environment';
import {EmailToCandidate} from '../../applications/types';

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

  public applicationsByRecruiter(req: JWTRecruiter) {
    return this.http.post<ApplicationsByRecruiter[]>(`${environment.URL_API_BACKEND}/application/list/by_recruiter`, req, {
      headers: {
        "Authorization": "Bearer " + this.jwtToken
      }
    });
  }

  public sendMessageToCandidate(req: EmailToCandidate) {
    return this.http.post<GeneralResponseAPI>(`${environment.URL_API_BACKEND}/recruiter/send_message_to_candidate`, req, {
      headers: {
        "Authorization": "Bearer " + this.jwtToken
      }
    });
  }
}
