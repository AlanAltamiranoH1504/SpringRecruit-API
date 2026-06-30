export interface SaveVacancy {
  jwt: string
  name: string,
  description: string,
  location: string,
  salary: number,
  finish_date: string,
  requirements: string,
  responsibilities: string,
  idContract_type: number,
  idIndustrialSector: number,
  idWorkModality: number,
  idProgressStatus: number,
  idCategory: number
}

export interface ActionDetails {
  idAction: number,
  nameAction: string
}

export interface Vacancy {
  id_vacancy:       number;
  name:             string;
  description:      string;
  location:         string;
  salary:           number;
  publication_date: Date;
  finish_date:      Date;
  requirements:     string;
  responsibilities: string;
  image:            null;
  status:           boolean;
  contract_type:    ContractType;
  progressStatus:   ProgressStatus;
  industrialSector: IndustrialSector;
  workModality:     WorkModality;
  category:         null;
}

export interface ContractType {
  id_contract_type:   number;
  name_contract_type: string;
  status:             boolean;
}

export interface IndustrialSector {
  name_industrial_sector:        string;
  description_industrial_sector: string;
  status:                        boolean;
  id_industrial_sector:          number;
}

export interface ProgressStatus {
  id_progress_status:          number;
  name_progress_status:        string;
  description_progress_status: string;
  status:                      boolean;
}

export interface WorkModality {
  name_work_modality: string;
  status:             boolean;
  id_work_modality:   number;
}

