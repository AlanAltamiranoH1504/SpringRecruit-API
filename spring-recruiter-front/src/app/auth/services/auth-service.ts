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
}
