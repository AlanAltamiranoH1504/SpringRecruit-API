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
