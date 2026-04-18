import {Component, computed, inject, signal} from '@angular/core';
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
import {CommonModule} from '@angular/common';
import {MatPaginatorModule, PageEvent} from '@angular/material/paginator';
import {ListVacancies} from '../../../vacancy/components/list-vacancies/list-vacancies';
import {VacancyService} from '../../../vacancy/services/vacancy-service';

@Component({
  selector: 'app-recruiters-dashboard-works',
  imports: [
    ReactiveFormsModule,
    RouterLink,
    CommonModule,
    MatPaginatorModule,
    ListVacancies
  ],
  templateUrl: './recruiters-dashboard-works.html',
  styleUrl: './recruiters-dashboard-works.css',
})
export class RecruitersDashboardWorks {
  protected contractTypeService = inject(ContractTypeService);
  protected industrialSectorService = inject(IndustrialSectorService);
  protected workModalityService = inject(WorkModalityService);
  protected recruiterService = inject(RecruiterService);
  protected vacancyService = inject(VacancyService);

  protected contractTypes = signal<ContractType[]>([])
  protected industrialSertors = signal<IndustrialSector[]>([]);
  protected workModalities = signal<WorkModality[]>([]);
  protected vacancies = this.vacancyService.vacancies;
  protected errorMessage = signal<string | null>(null);

  constructor(private dialog: MatDialog) {
  }

  ngOnInit() {
    this.vacancyService.refreshVacancies();
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

  protected openModal() {
    this.dialog.open(SaveNewVacancy, {
      width: "900px"
    });
  }
}
