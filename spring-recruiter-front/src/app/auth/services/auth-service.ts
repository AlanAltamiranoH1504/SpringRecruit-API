import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);

  public loginApi(request: FormLoginDTO) {
    return this.http.post<ResponseLogin>(`${environment.URL_API_BACKEND}/auth/login`, request);
  }

  public createAccountRecruiter(request: FormCreateAccountRecruiterDTO) {
    return this.http.post<GeneralResponseAPI>(`${environment.URL_API_BACKEND}/auth/save_recruiter`, request);
  }

  public createAccountCandidate(req: FormCreateAccountCandidateDTO) {
    return this.http.post<GeneralResponseAPI>(`${environment.URL_API_BACKEND}/auth/save_candidate`, req);
  }
}
