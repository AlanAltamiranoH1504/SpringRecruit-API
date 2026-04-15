import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IndustrialSector} from '../types';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class IndustrialSectorService {
  private http = inject(HttpClient);
  private jwtToken = localStorage.getItem("tokenSpringRecruiter")!;

  public listIndustrialSectors() {
    return this.http.post<IndustrialSector[]>(`${environment.URL_API_BACKEND}/industrial_sector/list`, {
      status: true
    }, {
      headers: {
        "Authorization": "Bearer " + this.jwtToken
      }
    });
  }
}
