export interface CandidateByRecruiterVacancy {
  idCandidate: number;
  nameCandidate: string;
  lastnameCandidate: string;
  emailCandidate: string;
  cellphoneCandidate: string;
  idVacancy: number;
  nameVacancy: string;
  locationVacancy: string;
  publicationDate: string;
}

export interface EmailToCandidate {
  nameCandidate: string,
  emailCandidate: string,
  nameVacancy: string,
  bodyEmail: string,
  tokenJWT: string
}

export interface ApplicationDetailsDTO {
  idApplication: number;
  applicationDate: string;
  notesCandidate: string | null;
  statusApplication: string;
  urlCV: string;

  idCandidate: number;
  nameCandidate: string;
  surnameCandidate: string;
  emailCandidate: string;
  cellphoneCandidate: string;
  imgProfileCandidate: string | null;

  idVacancy: number;
  nameVacancy: string;
}
