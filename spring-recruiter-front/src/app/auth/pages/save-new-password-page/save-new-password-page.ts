import {Component, inject, signal} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {FormValidatorService} from '../../../services/form-validator-service';
import {AuthService} from '../../services/auth-service';
import {routes} from '../../../app.routes';

@Component({
  selector: 'app-save-new-password-page',
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './save-new-password-page.html',
  styleUrl: './save-new-password-page.css',
})
export class SaveNewPasswordPage {
  protected formBuilder = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);
  protected authService = inject(AuthService);
  protected tokenResetPathVariable = inject(ActivatedRoute).snapshot.params["token"];
  protected formSaveNewPassword: FormGroup = this.formBuilder.group({
    "newPassword": ["", [Validators.required, Validators.minLength(8)]],
    "randomNumber": ["", [Validators.required, Validators.minLength(2)]]
  });
  protected successMessage = signal<string | null>(null);
  protected errorMessage = signal<string | null>(null);

  constructor(private router: Router) {
  }

  onSubmit() {
    if (!this.formValidatorService.isFormValid(this.formSaveNewPassword)) return;

    const body: FormSaveNewPasswordDTO = {
      newPassword: this.formSaveNewPassword.value.newPassword!,
      randomNumber: this.formSaveNewPassword.value.randomNumber!,
      token: this.tokenResetPathVariable!
    }

    this.authService.saveNewPassword(body).subscribe({
      next: () => {
        this.successMessage.set("Contraseña actualizada correctamente");
        setTimeout(() => {
          this.router.navigate([""]);
        }, 2500)
      },
      error: (error) => {
        this.errorMessage.set(error.error.message);
      }
    })
  }
}
