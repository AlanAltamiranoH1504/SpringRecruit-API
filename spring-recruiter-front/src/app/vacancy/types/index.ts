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
