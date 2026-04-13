export interface JWTRecruiter {
  token: string
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
  contract_type: ContractType
}

export interface ContractType {
  id_contract_type: number,
  name_contract_type: string,
  status: bigint
}


export interface ApplicationsByRecruiter {
  idApplication: number;
  applicationDate: string;
  commentsCandidate: string | null;
  status: string; // ajusta según tu enum
  idVacancy: number;
  nameVacancy: string;
  idCandidate: number;
  nameCandidate: string;
  surnameCandidate: string;
}
