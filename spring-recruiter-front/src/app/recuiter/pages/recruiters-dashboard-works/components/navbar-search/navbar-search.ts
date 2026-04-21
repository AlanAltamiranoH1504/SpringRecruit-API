import {Component, inject, signal} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {IndustrialSector, WorkModality, ContractType} from '../../../../../types';
import {ContractTypeService} from '../../../../../services/contract-type-service';
import {IndustrialSectorService} from '../../../../../services/industrial-sector-service';
import {WorkModalityService} from '../../../../../services/work-modality-service';

@Component({
  selector: 'app-navbar-search',
  imports: [
    FormsModule
  ],
  templateUrl: './navbar-search.html',
  styleUrl: './navbar-search.css',
})
export class NavbarSearch {
  protected contractTypeService = inject(ContractTypeService);
  protected industrialSectorService = inject(IndustrialSectorService);
  protected workModalityService = inject(WorkModalityService);

  protected contractTypes = signal<ContractType[]>([])
  protected industrialSertors = signal<IndustrialSector[]>([]);
  protected workModalities = signal<WorkModality[]>([]);

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
}
