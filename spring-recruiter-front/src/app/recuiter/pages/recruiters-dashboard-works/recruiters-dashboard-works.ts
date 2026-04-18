import {Component, inject, signal} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {SaveNewVacancy} from '../../../vacancy/components/save-new-vacancy/save-new-vacancy';
import {ContractTypeService} from '../../../services/contract-type-service';
import {IndustrialSectorService} from '../../../services/industrial-sector-service';
import {WorkModalityService} from '../../../services/work-modality-service';
import {IndustrialSector, WorkModality, ContractType} from '../../../types';
import {ReactiveFormsModule} from '@angular/forms';
import {RecruiterService} from '../../services/recruiter-service';
import {ResponseListVacanciesByRecruiter} from '../../types';
import {getTimeAgo} from '../../../utils/date-utils';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-recruiters-dashboard-works',
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './recruiters-dashboard-works.html',
  styleUrl: './recruiters-dashboard-works.css',
})
export class RecruitersDashboardWorks {
  protected contractTypeService = inject(ContractTypeService);
  protected industrialSectorService = inject(IndustrialSectorService);
  protected workModalityService = inject(WorkModalityService);
  protected recruiterService = inject(RecruiterService);

  protected contractTypes = signal<ContractType[]>([])
  protected industrialSertors = signal<IndustrialSector[]>([]);
  protected workModalities = signal<WorkModality[]>([]);
  protected vacancies = signal<ResponseListVacanciesByRecruiter[]>([]);
  protected errorMessage = signal<string | null>(null);

  constructor(private dialog: MatDialog) {
  }

  ngOnInit() {
    this.contractTypeService.listContractTypes().subscribe({
      next: (data) => {
        this.contractTypes.set(data);
      }
    });
    this.industrialSectorService.listIndustrialSectors().subscribe({
      next: (data) => {
        this.industrialSertors.set(data);
      }
    });
    this.workModalityService.listWorkModalities().subscribe({
      next: (data) => {
        this.workModalities.set(data);
      }
    });
    this.recruiterService.listVacanciesByRecruiter({
      token: localStorage.getItem("tokenSpringRecruiter")!,
      idsProgressStatus: [1, 2, 3, 4, 5, 5]
    }).subscribe({
      next: (data) => {
        this.vacancies.set(data);
      },
      error: (error) => {
        this.errorMessage.set(error.message.message);
      }
    });
  }

  protected getStatusVacancy(idProgressStatus: number) {
    switch (idProgressStatus) {
      case 1: // * Inicio
        return "bg-green-50 text-green-700 hover:bg-green-100 p-2";
      case 2: // * Postulaciones Cerradas
        return "bg-red-50 text-red-700 hover:bg-red-100 p-2";
      case 3: // * Evaluación CVs
        return "bg-blue-50 text-blue-700 hover:bg-blue-100 p-2";
      case 4: // * Entrevistas
        return "bg-purple-50 text-purple-700 hover:bg-purple-100 p-2";
      case 5: // * Elección candidato
        return "bg-yellow-50 text-yellow-700 hover:bg-yellow-100 p-2";
      case 6: // * Concluida
        return "bg-gray-100 text-gray-700 hover:bg-gray-200 p-2";
      default:
        return "bg-gray-50 text-gray-500 p-2";
    }
  }

  protected openModal() {
    this.dialog.open(SaveNewVacancy, {
      width: "900px"
    });
  }

  protected readonly getTimeAgo = getTimeAgo;
}
