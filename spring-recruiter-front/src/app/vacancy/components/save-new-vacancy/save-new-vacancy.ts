import {Component, inject, signal} from '@angular/core';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {CommonModule} from '@angular/common';
import {ContractTypeService} from '../../../services/contract-type-service';
import {ContractType, IndustrialSector, WorkModality} from '../../../types';
import {IndustrialSectorService} from '../../../services/industrial-sector-service';
import {WorkModalityService} from '../../../services/work-modality-service';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {FormValidatorService} from '../../../services/form-validator-service';
import {SaveVacancy} from '../../types';
import {VacancyService} from '../../services/vacancy-service';

@Component({
  selector: 'app-save-new-vacancy',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, MatDialogModule, MatButtonModule],
  templateUrl: './save-new-vacancy.html',
  styleUrl: './save-new-vacancy.css',
})
export class SaveNewVacancy {
  protected contractTypeService = inject(ContractTypeService);
  protected industrialSectorService = inject(IndustrialSectorService);
  protected workModalityService = inject(WorkModalityService);
  protected formValidatorService = inject(FormValidatorService);
  protected vacancyService = inject(VacancyService);

  protected formBuilder = inject(FormBuilder);
  protected formSaveVacancy: FormGroup = this.formBuilder.group({
    name: ["", [Validators.required, Validators.minLength(3)]],
    description: ["", [Validators.required, Validators.minLength(3)]],
    location: ["", [Validators.required, Validators.minLength(3)]],
    salary: [null, [Validators.required, Validators.min(10)]],
    finish_date: ["", Validators.required],
    requirements: ["", [Validators.required, Validators.minLength(3)]],
    responsibilities: ["", [Validators.required, Validators.minLength(3)]],
    idContract_type: ["", [Validators.required, Validators.min(1)]],
    idIndustrialSector: ["", [Validators.required, Validators.min(1)]],
    idWorkModality: ["", [Validators.required, Validators.min(1)]]
  });

  protected contracTypes = signal<ContractType[]>([]);
  protected industrialSectors = signal<IndustrialSector[]>([]);
  protected worksModality = signal<WorkModality[]>([]);
  protected isError = signal<string | null>(null);
  protected isSuccess = signal<string | null>(null);

  constructor(private dialogRef: MatDialogRef<SaveNewVacancy>) {
  }

  ngOnInit() {
    this.contractTypeService.listContractTypes().subscribe({
      next: (data) => {
        this.contracTypes.set(data);
      }
    });

    this.industrialSectorService.listIndustrialSectors().subscribe({
      next: (data) => {
        this.industrialSectors.set(data);
      }
    });

    this.workModalityService.listWorkModalities().subscribe({
      next: (data) => {
        this.worksModality.set(data);
      }
    })
  }

  protected close() {
    this.dialogRef.close();
  }

  onSubmit() {
    if (!this.formValidatorService.isFormValid(this.formSaveVacancy)) return;
    // * Extract Data
    const vacancyToSave: SaveVacancy = {
      jwt: localStorage.getItem("tokenSpringRecruiter")!,
      name: this.formSaveVacancy.value.name,
      description: this.formSaveVacancy.value.description,
      location: this.formSaveVacancy.value.location,
      salary: Number(this.formSaveVacancy.value.salary),
      finish_date: this.formSaveVacancy.value.finish_date,
      requirements: this.formSaveVacancy.value.requirements,
      responsibilities: this.formSaveVacancy.value.responsibilities,
      idContract_type: Number(this.formSaveVacancy.value.idContract_type),
      idIndustrialSector: Number(this.formSaveVacancy.value.idIndustrialSector),
      idWorkModality: Number(this.formSaveVacancy.value.idWorkModality),
      idProgressStatus: 1,
      idCategory: 3
    }

    this.vacancyService.saveVacancy(vacancyToSave).subscribe({
      next: (data) => {
        this.isSuccess.set("Vacante guardada correctamente");
        this.vacancyService.refreshVacancies();
        this.formSaveVacancy.reset();
        setTimeout(() => {
          this.dialogRef.close();
        }, 3000)
      },
      error: (error) => {
        this.isError.set(error.message.message);
      }
    })
  }
}
