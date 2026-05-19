import {Component, inject, signal} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {IndustrialSector, WorkModality, ContractType} from '../../../../../types';
import {ContractTypeService} from '../../../../../services/contract-type-service';
import {IndustrialSectorService} from '../../../../../services/industrial-sector-service';
import {WorkModalityService} from '../../../../../services/work-modality-service';
import {FormValidatorService} from '../../../../../services/form-validator-service';
import {VacancyByTitle} from '../../../../types/VacancyByTitle';
import {VacancyService} from '../../../../../vacancy/services/vacancy-service';
import {VacanciesByFilters} from '../../../../types/VacanciesByFilters';

@Component({
  selector: 'app-navbar-search',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './navbar-search.html',
  styleUrl: './navbar-search.css',
})
export class NavbarSearch {
  protected contractTypeService = inject(ContractTypeService);
  protected industrialSectorService = inject(IndustrialSectorService);
  protected workModalityService = inject(WorkModalityService);
  protected vacancyService = inject(VacancyService);
  protected formBuilder = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);

  protected contractTypes = signal<ContractType[]>([])
  protected industrialSertors = signal<IndustrialSector[]>([]);
  protected workModalities = signal<WorkModality[]>([]);
  protected formByTitle = this.formBuilder.group({
    titleVacancy: ["", [Validators.required, Validators.minLength(3)]]
  });
  protected formByFilters = this.formBuilder.group({
    contractType: ["", []],
    industrialSector: ["", []],
    workModality: ["", []]
  });

  protected isErrorMessage = signal<string | null>(null);
  protected isErrorMessageFilters = signal<string | null>(null);

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
  }

  protected searchByTitle() {
    // ! IS FORM VALID
    if (!this.formValidatorService.isFormValid(this.formByTitle)) return;

    // * EXTRACT DATA
    const requestBody: VacancyByTitle = {
      titleVacancy: this.formByTitle.value.titleVacancy!
    }

    // * CALLED SERVICE
    this.vacancyService.searchVacancyByTitle(requestBody.titleVacancy).subscribe({
      next: (vacancies) => {
        this.vacancyService.vacancies.set(vacancies);
      },
      error: (error) => {
        this.isErrorMessage.set("No se encontraron vacantes");
      }
    });
  }

  protected searchByFilters() {
    // * EXTRACT DATA
    const requestBody: VacanciesByFilters = {};
    this.isErrorMessageFilters.set(null);
    if (this.formByFilters.value.contractType !== null && this.formByFilters.value.contractType !== "") {
      requestBody.contractTypes = [+this.formByFilters.value.contractType!];
    }

    if (this.formByFilters.value.industrialSector !== null && this.formByFilters.value.industrialSector !== "") {
      requestBody.industriesSector = [+this.formByFilters.value.industrialSector!];
    }

    if (this.formByFilters.value.workModality !== null && this.formByFilters.value.workModality !== "") {
      requestBody.worksModalities = [+this.formByFilters.value.workModality!];
    }

    if (Object.keys(requestBody).length === 0) {
      this.isErrorMessageFilters.set("Es necesario aplicar al menos un filtro");
      return;
    }

    this.vacancyService.searchVacanciesByFiltersAndRecruiter(requestBody).subscribe({
      next: (vancancies) => {
        if (vancancies.length === 0) {
          this.vacancyService.vacancies.set([]);
          this.isErrorMessageFilters.set("No se encontraron vacantes");
          return;
        }
        this.vacancyService.vacancies.set(vancancies);
      }
    });
  }
}
