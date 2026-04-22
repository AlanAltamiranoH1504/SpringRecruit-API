import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ApplicationDetailsDTO, CandidateByRecruiterVacancy} from '../types';

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

  public getApplicationsDetailsByIdVacancy(idVacancy: number) {
    return this.http.post<ApplicationDetailsDTO[]>(`${environment.URL_API_BACKEND}/application/applications_details/by/vancancy/${idVacancy}`, {token: this.tokenJWT}, {
      headers: {
        "Authorization": "Bearer " + this.tokenJWT
      }
    });
  }

  public rejectApplication(idApplication: number) {
    return this.http.delete<GeneralResponseAPI>(`${environment.URL_API_BACKEND}/application/delete/${idApplication}`, {
      headers: {
        "Authorization": "Bearer " + this.tokenJWT
      }
    });
  }

  public getColorStatusApplication(statusApplication: string | undefined): string {
    if (!statusApplication) return "bg-gray-100 text-gray-700 border-gray-200";

    switch (statusApplication) {
      case "RECEIVED":
        return "bg-blue-100 text-blue-700 border-blue-200 p-1 rounded-lg";
      case "REVIEWING":
        return "bg-amber-100 text-amber-700 border-amber-200 p-1 rounded-lg";
      case "INTERVIEW_SCHEDULED":
        return "bg-purple-100 text-purple-700 border-purple-200 p-1 rounded-lg";
      case "TESTING_PHASE":
        return "bg-indigo-100 text-indigo-700 border-indigo-200 p-1 rounded-lg" ;
      case "REJECTED":
        return "bg-red-100 text-red-700 border-red-200 p-1 rounded-lg";
      case "OFFER_EXTENDED":
        return "bg-cyan-100 text-cyan-700 border-cyan-200 p-1 rounded-lg";
      case "HIRED":
        return "bg-green-100 text-green-700 border-green-200 p-1 rounded-lg";
      default:
        return "bg-gray-100 text-gray-700 border-gray-200 p-1 rounded-lg";
    }
  }

  public getStatusApplicationText(statusApplication: string | undefined): string {
    if (!statusApplication) return "Pendiente"; // O "Revisión" según prefieras

    switch (statusApplication) {
      case "RECEIVED":
        return "Recibida";
      case "REVIEWING":
        return "En Revisión";
      case "INTERVIEW_SCHEDULED":
        return "Entrevista";
      case "TESTING_PHASE":
        return "En Pruebas";
      case "REJECTED":
        return "Rechazado";
      case "OFFER_EXTENDED":
        return "Oferta Enviada";
      case "HIRED":
        return "Contratado";
      default:
        return "Revisión";
    }
  }
}
