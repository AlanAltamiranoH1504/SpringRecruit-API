import {CanActivateFn, Router} from '@angular/router';
import {isLogin} from '../utils/is-login.utils';
import {inject} from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  if (isLogin()) {
    return true;
  } else {
    return router.navigate(["/login"]);
  }
};
