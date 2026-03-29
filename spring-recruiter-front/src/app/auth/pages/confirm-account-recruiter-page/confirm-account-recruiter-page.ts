import {Component, inject, signal} from '@angular/core';
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthService} from '../../services/auth-service';
import {FormValidatorService} from '../../../services/form-validator-service';

@Component({
  selector: 'app-confirm-account-recruiter-page',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './confirm-account-recruiter-page.html',
  styleUrl: './confirm-account-recruiter-page.css',
})
export class ConfirmAccountRecruiterPage {
  protected formBuilder = inject(FormBuilder);
  protected authService = inject(AuthService);
  protected formValidatorService = inject(FormValidatorService);
  protected tokenConfirmPathVariable: string = inject(ActivatedRoute).snapshot.params["token"];

  protected successMessage = signal<string | null>(null);
  protected errorMessage = signal<string | null>(null);

  protected formConfirmRecruiter = this.formBuilder.group({
    random: ["", [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(5),
    ]]
  });

  protected onSubmit() {
    this.errorMessage.set(null);
    this.successMessage.set(null);
    if (!this.formValidatorService.isFormValid(this.formConfirmRecruiter)) return;

    const body: FormConfirmAccountRecruiterDTO = {
      randome_number: this.formConfirmRecruiter.value.random!,
      token_confirm_account: this.tokenConfirmPathVariable
    }

    this.authService.confirmRecruiter(body).subscribe({
      next: () => {
        this.successMessage.set("Cuenta Confirmada Correctamente");
        this.formConfirmRecruiter.reset();
      },
      error: (error) => {
        this.errorMessage.set(error.error.message);
      }
    })
  }
}
