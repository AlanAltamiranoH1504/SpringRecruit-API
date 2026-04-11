import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {isLogin} from '../../../utils/is-login.utils';

@Component({
  selector: 'app-hero-c-component',
  imports: [],
  templateUrl: './hero-c-component.html',
  styleUrl: './hero-c-component.css',
})
export class HeroCComponent {
  constructor(private router: Router) {
  }

  protected onClick() {
    const resultIsLogin = isLogin();
    if (isLogin()) {
      console.log("Se dirige a parte autenticada de candidatos");
      return;
    }
    this.router.navigate(["/login"]);
  }
}
