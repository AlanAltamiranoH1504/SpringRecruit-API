import {Component, inject, signal} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {FormValidatorService} from '../../../services/form-validator-service';
import {AuthService} from '../../services/auth-service';

@Component({
  selector: 'app-confirm-account-candidate-page',
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './confirm-account-candidate-page.html',
  styleUrl: './confirm-account-candidate-page.css',
})
export class ConfirmAccountCandidatePage {
  protected formBuilder = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);
  protected authService = inject(AuthService);
  protected tokenConfirmPathVariable: string = inject(ActivatedRoute).snapshot.params["token"];

  protected errorMessage = signal<string | null>(null);
  protected successMessage = signal<string | null>(null);

  protected formConfirmCandidate: FormGroup = this.formBuilder.group({
    "random": ["", [Validators.required, Validators.maxLength(5), Validators.minLength(3)]]
  });

  protected onSubmit() {
    if (!this.formValidatorService.isFormValid(this.formConfirmCandidate)) return;

    const body: FormConfirmAccountCandidateDTO = {
      randome_number: this.formConfirmCandidate.value.random!,
      token_confirm_account: this.tokenConfirmPathVariable
    }

    this.authService.confirmCandidate(body).subscribe({
      next: (result) => {
        this.successMessage.set("Cuenta confirmada correctamente");
        this.formConfirmCandidate.reset();
      },
      error: (error) => {
        this.errorMessage.set(error.error.message);
      }
    })
  }
}
