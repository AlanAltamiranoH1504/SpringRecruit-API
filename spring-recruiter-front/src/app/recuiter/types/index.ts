export interface JWTRecruiter {
  token: string
}

export interface VacanciesByRecruiter {
  token: string,
  idsProgressStatus: Number[]
}

export interface ResponseRecruiterInSession {
  name: string
  surnames: string,
  username: string,
  img_profile: string | null
}

export interface ResponseListVacanciesByRecruiter {
  totalApplications: number,
  vacancy: VacancyByRecruiter
}

export interface VacancyByRecruiter {
  id_vacancy: number,
  name: string,
  description: string,
  location: string,
  salary: number
  publication_date: string,
  finish_date: string,
  requirements: string,
  responsibilities: string,
  image: string,
  status: boolean,
  contract_type: ContractType,
  progressStatus: ProgressStatus,
  industrialSector: IndustrialSector,
  workModality: WorkModality
}

export interface ContractType {
  id_contract_type: number,
  name_contract_type: string,
  status: bigint
}
export interface ProgressStatus {
  id_progress_status: number,
  name_progress_status: string,
  description_progress_status: string,
  status: boolean
}

export interface IndustrialSector {
  id_industrial_sector: number
  name_industrial_sector: string,
  description_industrial_sector: string,
  status: boolean,
}

export interface WorkModality {
  id_work_modality: number
  name_work_modality: string,
  status: boolean,
}


export interface ApplicationsByRecruiter {
  idApplication: number;
  applicationDate: string;
  commentsCandidate: string | null;
  status: string;
  idVacancy: number;
  nameVacancy: string;
  idCandidate: number;
  nameCandidate: string;
  surnameCandidate: string;
}
