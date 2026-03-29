import {Routes} from '@angular/router';
import {LoginPage} from './auth/pages/login-page/login-page';
import {RegisterCandidatePage} from './auth/pages/register-candidate-page/register-candidate-page';
import {RegisterRecruiterPage} from './auth/pages/register-recruiter-page/register-recruiter-page';

export const routes: Routes = [
  {path: "", component: LoginPage},
  {
    path: "register", children: [
      {path: "candidate", component: RegisterCandidatePage},
      {path: "recruiter", component: RegisterRecruiterPage}
    ]
  },
  {path: "**", redirectTo: ""}
];
