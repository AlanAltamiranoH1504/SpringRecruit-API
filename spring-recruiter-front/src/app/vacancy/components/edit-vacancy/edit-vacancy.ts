import {Component, inject, signal} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {FormValidatorService} from '../../../services/form-validator-service';
import {ContractTypeService} from '../../../services/contract-type-service';
import {IndustrialSectorService} from '../../../services/industrial-sector-service';
import {WorkModalityService} from '../../../services/work-modality-service';
import {ContractType, IndustrialSector, WorkModality} from '../../types';
import {CommonModule} from '@angular/common';
import {MAT_DIALOG_DATA, MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import {toast} from 'ngx-sonner';

@Component({
  selector: 'app-edit-vacancy',
  imports: [ReactiveFormsModule, CommonModule, MatDialogModule, MatButtonModule],
  templateUrl: './edit-vacancy.html',
  styleUrl: './edit-vacancy.css',
})
export class EditVacancy {
  protected contractTypesService = inject(ContractTypeService)
  protected industralSectorService = inject(IndustrialSectorService);
  protected workModalityService = inject(WorkModalityService);

  protected vacancyData = inject(MAT_DIALOG_DATA);
  protected formBuild = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);
  protected isError = signal<string | null>(null);
  protected isSuccess = signal<string | null>(null);
  protected contractTypes = signal<ContractType[]>([]);
  protected industrialSectors = signal<IndustrialSector[]>([]);
  protected worksModality = signal<WorkModality[]>([]);
  protected formUpdateVacancy = this.formBuild.group({
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


  ngOnInit() {
    if (this.vacancyData) {
      // * SETEO DE INFORMACION DE VACANTE
      const predData = {
        ...this.vacancyData,
        idContract_type: this.vacancyData.contract_type?.id_contract_type,
        idIndustrialSector: this.vacancyData.industrialSector?.id_industrial_sector,
        idWorkModality: this.vacancyData.workModality?.id_work_modality
      }
      this.formUpdateVacancy.patchValue(predData);
      toast.success("Data cargada correctamete", {
        description: "Confirma tus cambios antes de actualizar la vacante"
      });
    } else {
      toast.error("Ocurrio un error", {
        description: "Ocurrio un error al buscar la informacion de la vacante"
      });
    }
    this.contractTypesService.listContractTypes().subscribe({
      next: (dataResponse) => {
        this.contractTypes.set(dataResponse);
      }
    });
    this.industralSectorService.listIndustrialSectors().subscribe({
      next: (dataResponse) => {
        this.industrialSectors.set(dataResponse);
      }
    });
    this.workModalityService.listWorkModalities().subscribe({
      next: (dataResponse) => {
        this.worksModality.set(dataResponse);
      }
    });
  }

  protected onSubmit() {
    if(!this.formValidatorService.isFormValid(this.formUpdateVacancy)) return;
    // * EXTRAC DATA

  }

  protected close() {

  }
}
