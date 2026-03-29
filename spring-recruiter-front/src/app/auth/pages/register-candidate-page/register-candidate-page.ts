import {Component, inject, signal} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {FormValidatorService} from '../../../services/form-validator-service';
import {AuthService} from '../../services/auth-service';

@Component({
  selector: 'app-register-candidate-page',
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './register-candidate-page.html',
  styleUrl: './register-candidate-page.css',
})
export class RegisterCandidatePage {
  protected formBuilder = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);
  protected authService = inject(AuthService);
  protected errorMessage = signal<string | null>(null);
  protected successMessage = signal<string | null>(null);

  protected formRegisterCandidate = this.formBuilder.group({
    "email": ["", [Validators.required, Validators.email, Validators.minLength(6)]],
    "password": ["", [Validators.required, Validators.minLength(8)]],
    "nameCandidate": ["", [Validators.required, Validators.minLength(3)]],
    "lastnameCandidate": ["", [Validators.required, Validators.minLength(3)]],
    "cellphone": ["", [
      Validators.required,
      Validators.maxLength(10),
      Validators.pattern(/^[0-9]{10}$/)
    ]],
    "address": ["", [Validators.required, Validators.maxLength(300)]],
  });

  protected onSubmit() {
    if (!this.formValidatorService.isFormValid(this.formRegisterCandidate)) return;

    // * Extract data
    const body: FormCreateAccountCandidateDTO = {
      name_candidate: this.formRegisterCandidate.value.nameCandidate!,
      lastname_candidate: this.formRegisterCandidate.value.lastnameCandidate!,
      email: this.formRegisterCandidate.value.email!,
      password: this.formRegisterCandidate.value.password!,
      cellphone: this.formRegisterCandidate.value.cellphone!,
      address: this.formRegisterCandidate.value.address!,
      roles: [3]
    }

    this.authService.createAccountCandidate(body).subscribe({
      next: (result) => {
        this.successMessage.set("Cuenta creada. Confirma en tu email.");
        this.formRegisterCandidate.reset();
      },
      error: (error) => {
        this.errorMessage = error.error.message;
      }
    })
  }
}
