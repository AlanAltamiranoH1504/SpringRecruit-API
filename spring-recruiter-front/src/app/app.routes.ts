import {Routes} from '@angular/router';
import {LoginPage} from './auth/pages/login-page/login-page';
import {RegisterCandidatePage} from './auth/pages/register-candidate-page/register-candidate-page';
import {RegisterRecruiterPage} from './auth/pages/register-recruiter-page/register-recruiter-page';
import {ConfirmAccountCandidatePage} from './auth/pages/confirm-account-candidate-page/confirm-account-candidate-page';
import {ConfirmAccountRecruiterPage} from './auth/pages/confirm-account-recruiter-page/confirm-account-recruiter-page';
import {ForgetPasswordPage} from './auth/pages/forget-password-page/forget-password-page';
import {SaveNewPasswordPage} from './auth/pages/save-new-password-page/save-new-password-page';
import {HomePage} from './home/pages/home-page/home-page';

export const routes: Routes = [
  {path: "login", component: LoginPage},
  {
    path: "home", children: [
      {path: "recruiter", component: HomePage}
    ]
  },
  {
    path: "register", children: [
      {path: "candidate", component: RegisterCandidatePage},
      {path: "recruiter", component: RegisterRecruiterPage}
    ]
  },
  {
    path: "confirm", children: [
      {path: "candidate/:token", component: ConfirmAccountCandidatePage},
      {path: "recruiter/:token", component: ConfirmAccountRecruiterPage}
    ]
  },
  {
    path: "forget-password", children: [
      {path: "", component: ForgetPasswordPage},
      {path: ":token", component: SaveNewPasswordPage}
    ]
  },
  {path: "**", redirectTo: "/home/recruiter"}
];
