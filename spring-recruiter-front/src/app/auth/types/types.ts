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

interface FormConfirmAccountCandidateDTO {
  token_confirm_account: string,
  randome_number: string
}

interface FormConfirmAccountRecruiterDTO {
  token_confirm_account: string,
  randome_number: string
}

interface FormForgetPasswordDTO {
  email: string
}

interface FormSaveNewPasswordDTO {
  token: string,
  "randomNumber": string,
  newPassword: string,
}

interface ResponseLogin {
  status: boolean,
  token: string
  isRecruiter: boolean
}

interface GeneralResponseAPI {
  status: boolean,
  message: string,
}
