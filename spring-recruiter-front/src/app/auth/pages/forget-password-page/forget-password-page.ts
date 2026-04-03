import {Component, inject, signal} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';
import {FormValidatorService} from '../../../services/form-validator-service';
import {AuthService} from '../../services/auth-service';

@Component({
  selector: 'app-forget-password-page',
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './forget-password-page.html',
  styleUrl: './forget-password-page.css',
})
export class ForgetPasswordPage {
  protected formBuilder = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);
  protected authService = inject(AuthService);
  protected errorMessage = signal<string | null>(null);
  protected successMessage = signal<string | null>(null);
  protected formForgetPassword: FormGroup = this.formBuilder.group({
    "email": ["", [Validators.required, Validators.email]]
  });

  constructor(private router: Router) {
  }


  protected onSubmit() {
    this.successMessage.set(null);
    this.errorMessage.set(null);

    if (!this.formValidatorService.isFormValid(this.formForgetPassword)) return;

    const body: FormForgetPasswordDTO = {
      email: this.formForgetPassword.value.email!
    }

    this.authService.forgetPassword(body).subscribe({
      error: (error) => {
        this.errorMessage.set(error.error.message);
      },
      next: (next) => {
        this.successMessage.set("Email enviado correctamente");
        setTimeout(() => {
          this.router.navigate([""]);
        }, 2500)
      }
    });
  }
}
