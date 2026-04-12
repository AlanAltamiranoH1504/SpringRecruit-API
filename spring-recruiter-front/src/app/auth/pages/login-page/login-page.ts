import {Component, inject, signal} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {FormValidatorService} from '../../../services/form-validator-service';
import {AuthService} from '../../services/auth-service';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-login-page',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage {
  protected formBuilder = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);
  protected authService = inject(AuthService);
  protected router = inject(Router);

  protected tokenJWT = signal<string>("");
  protected errorMessage = signal<string | null>(null);

  protected formLogin = this.formBuilder.group({
    email: ["", [Validators.required, Validators.minLength(3), Validators.email]],
    password: ["", [Validators.required, Validators.minLength(8)]]
  });

  protected onSubmit() {
    // ! Form valid?
    if (!this.formValidatorService.isFormValid(this.formLogin)) return;

    // * Extract data
    const body: FormLoginDTO = {
      email: this.formLogin.value.email!,
      password: this.formLogin.value.password!
    }

    this.authService.loginApi(body).subscribe({
      next: (response) => {
        this.tokenJWT.set(response.token);
        localStorage.setItem("tokenSpringRecruiter", response.token);
        switch (response.isRecruiter) {
          case true:
            this.router.navigate(["/dashboard/recruiter"]);
            // localStorage.setItem("isRecruiter", JSON.stringify(true));
            break;
          case false:
            console.log("Es un candidatos");
            break;
          default:
            console.log("Es un candidato");
        }
      },
      error: (error) => {
        this.errorMessage.set(error.error.message);
      }
    });
  }
}
