interface FormLoginDTO {
  email: string,
  password: string
}

interface FormCreateAccountRecruiterDTO {
  name: string,
  surnames: string,
  email: string,
  password: string,
  username: string,
  roles: number[]
}

interface FormCreateAccountCandidateDTO {
  email: string,
  password: string,
  name_candidate: string,
  lastname_candidate: string,
  cellphone: string,
  address: string,
  roles: number[]
}

interface ResponseLogin {
  status: boolean,
  token: string
}

interface GeneralResponseAPI {
  status: boolean,
  message: string,
}
