import {Routes} from '@angular/router';
import {LoginPage} from './auth/pages/login-page/login-page';
import {RegisterCandidatePage} from './auth/pages/register-candidate-page/register-candidate-page';
import {RegisterRecruiterPage} from './auth/pages/register-recruiter-page/register-recruiter-page';
import {ConfirmAccountCandidatePage} from './auth/pages/confirm-account-candidate-page/confirm-account-candidate-page';
import {ConfirmAccountRecruiterPage} from './auth/pages/confirm-account-recruiter-page/confirm-account-recruiter-page';
import {ForgetPasswordPage} from './auth/pages/forget-password-page/forget-password-page';
import {SaveNewPasswordPage} from './auth/pages/save-new-password-page/save-new-password-page';
import {HomePage} from './home/pages/home-page/home-page';
import {HomeCandidatesPage} from './home-candidates/pages/home-candidates-page/home-candidates-page';
import {RecruiterDashboardPage} from './recuiter/pages/recruiter-dashboard-page/recruiter-dashboard-page';
import {authGuard} from './guardians/auth-guard';
import {recruiterGuardGuard} from './guardians/recruiter-guard-guard';
import {RecruitersDashboardWorks} from './recuiter/pages/recruiters-dashboard-works/recruiters-dashboard-works';
import {
  RecruitersDashboardCandidates
} from './recuiter/pages/recruiters-dashboard-candidates/recruiters-dashboard-candidates';
import {
  RecruitersDashboardApplications
} from './recuiter/pages/recruiters-dashboard-applications/recruiters-dashboard-applications';
import {RecruitersDashboardReports} from './recuiter/pages/recruiters-dashboard-reports/recruiters-dashboard-reports';
import {RecruitersDashboardHelp} from './recuiter/pages/recruiters-dashboard-help/recruiters-dashboard-help';
import {RecruitersDashboardSumary} from './recuiter/pages/recruiters-dashboard-sumary/recruiters-dashboard-sumary';
import {VacancyDetailsPage} from './vacancy/pages/vacancy-details-page/vacancy-details-page';
import {
  RecruiterDashboardWorkPage
} from './recuiter/pages/recruiters-dashboard-works/pages/recruiter-dashboard-work-page/recruiter-dashboard-work-page';

export const routes: Routes = [
  {path: "login", component: LoginPage},
  {
    path: "home", children: [
      {path: "recruiter", component: HomePage},
      {path: "candidate", component: HomeCandidatesPage}
    ]
  },
  {
    path: "dashboard", canActivate: [authGuard], children: [ // * Validacion de guardian de login
      {
        path: "recruiter",
        canActivate: [recruiterGuardGuard], // * Validacion de guardian para usuarios reclutadores
        component: RecruiterDashboardPage,
        children: [
          {path: "", component: RecruitersDashboardSumary},
          {
            path: "work", component: RecruitersDashboardWorks, children: [
              {path: "", component: RecruiterDashboardWorkPage},
              {path: "details/:id", component: VacancyDetailsPage}
            ]
          },
          {path: "candidates", component: RecruitersDashboardCandidates},
          {path: "applications", component: RecruitersDashboardApplications},
          {path: "reports", component: RecruitersDashboardReports},
          {path: "help", component: RecruitersDashboardHelp}
        ]
      }
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
  {path: "**", redirectTo: "/home/candidate"}
];
