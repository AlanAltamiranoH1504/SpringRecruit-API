import {Component, inject, signal} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {FormValidatorService} from '../../../services/form-validator-service';
import {AuthService} from '../../services/auth-service';

@Component({
  selector: 'app-register-recruiter-page',
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './register-recruiter-page.html',
  styleUrl: './register-recruiter-page.css',
})
export class RegisterRecruiterPage {
  protected formBuilder = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);
  protected authService = inject(AuthService);
  protected errorMessage = signal<string | null>(null);
  protected successMessage = signal<string | null>(null);

  protected formRegisterRecruiter: FormGroup = this.formBuilder.group({
    "email": ["", [Validators.required, Validators.email, Validators.minLength(3)]],
    "password": ["", [Validators.required, Validators.minLength(8)]],
    "name": ["", [Validators.required, Validators.minLength(3)]],
    "surnames": ["", [Validators.required, Validators.minLength(3)]],
    "username": ["", [Validators.required, Validators.minLength(3)]],
  });

  protected onSubmit() {
    // ! form is valid
    if (!this.formValidatorService.isFormValid(this.formRegisterRecruiter)) return;

    // * Extract data
    const body: FormCreateAccountRecruiterDTO = {
      name: this.formRegisterRecruiter.value.name!,
      surnames: this.formRegisterRecruiter.value.surnames!,
      username: this.formRegisterRecruiter.value.username!,
      email: this.formRegisterRecruiter.value.email!,
      password: this.formRegisterRecruiter.value.password!,
      roles: [1]
    }
    this.authService.createAccountRecruiter(body).subscribe({
      next: (result) => {
        this.successMessage.set("Cuenta creada. Confirma en tu correo.")
      },
      error: (error) => {
        this.errorMessage.set(error.error.message);
      }
    });
  }
}
