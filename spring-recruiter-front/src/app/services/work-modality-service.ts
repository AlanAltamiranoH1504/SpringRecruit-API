import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {WorkModality} from '../types';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class WorkModalityService {
  private http = inject(HttpClient);
  private jwtToken = localStorage.getItem("tokenSpringRecruiter")!;

  public listWorkModalities() {
    return this.http.post<WorkModality[]>(`${environment.URL_API_BACKEND}/work_modality/list`, {
      status: true
    }, {
      headers: {
        "Authorization": "Bearer " + this.jwtToken
      }
    });
  }
}
