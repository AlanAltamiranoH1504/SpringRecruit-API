import {CanActivateFn, Router} from '@angular/router';
import {inject, signal} from '@angular/core';
import {AuthService} from '../auth/services/auth-service';
import {map} from 'rxjs';

export const recruiterGuardGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.isRecruiter(localStorage.getItem("tokenSpringRecruiter")!)
    .pipe(
      map((response) => {
        if (response.status) {
          return true;
        } else {
          router.navigate(["/home/candidate"])
          return false;
        }
      })
    );
};
