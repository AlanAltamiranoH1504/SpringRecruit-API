import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ContractType} from '../types';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ContractTypeService {
  private http = inject(HttpClient);
  private jwtToken = localStorage.getItem("tokenSpringRecruiter")!;

  public listContractTypes() {
    return this.http.post<ContractType[]>(`${environment.URL_API_BACKEND}/contract_type/list`, {
      status: true
    }, {
      headers: {
        "Authorization": "Bearer " + this.jwtToken
      }
    });
  }
}
