import {Component, inject, signal} from '@angular/core';
import {MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {ApplicationService} from '../../../applications/services/application-service';
import {CandidateByRecruiterVacancy, EmailToCandidate} from '../../../applications/types';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {FormValidatorService} from '../../../services/form-validator-service';
import {RecruiterService} from '../../../recuiter/services/recruiter-service';

@Component({
  selector: 'app-send-message-to-candidate',
  imports: [
    MatDialogTitle,
    MatDialogContent,
    ReactiveFormsModule
  ],
  templateUrl: './send-message-to-candidate.html',
  styleUrl: './send-message-to-candidate.css',
})
export class SendMessageToCandidate {
  protected applicationService = inject(ApplicationService);
  protected recruiterService = inject(RecruiterService);
  protected formBuilder = inject(FormBuilder);
  protected formValidatorService = inject(FormValidatorService);

  protected candidates = signal<CandidateByRecruiterVacancy[]>([]);
  protected errorMessage = signal<string | null>(null);
  protected selectedCandidate = signal<CandidateByRecruiterVacancy | null>(null);
  protected successMessage = signal<string | null>(null);
  protected formSendMessageToCandidate: FormGroup = this.formBuilder.group({
    nameCandidate: ["", [Validators.required]],
    emailCandidate: ["", [Validators.required]],
    nameVacancy: ["", [Validators.required]],
    bodyEmail: ["", [Validators.required, Validators.maxLength(1200)]]
  });


  constructor(private dialogRef: MatDialogRef<SendMessageToCandidate>) {
  }

  ngOnInit() {
    this.applicationService.findAllCandidatesByRecruiterVacancies().subscribe({
      next: (data) => {
        this.candidates.set(data);
      },
      error: (error) => {
        this.errorMessage.set(error.message.message);
      }
    });
  }

  protected onCandidateChange(event: Event) {
    const select = event.target as HTMLSelectElement;
    const idVacancy = Number(select.value);

    const candidate = this.candidates().find((c) => {
      return c.idVacancy === idVacancy
    });
    this.selectedCandidate.set(candidate!); // ! Set de candidato y en formulario
    this.formSendMessageToCandidate.patchValue({
      nameCandidate: this.selectedCandidate()?.nameCandidate,
      emailCandidate: this.selectedCandidate()?.emailCandidate,
      nameVacancy: this.selectedCandidate()?.nameVacancy
    });
  }

  protected closeModal() {
    this.dialogRef.close();
  }

  protected onSubmit() {
    if (!this.formValidatorService.isFormValid(this.formSendMessageToCandidate)) {
      this.formSendMessageToCandidate.markAllAsTouched();
      return;
    }

    // * Extract data
    const requestBody: EmailToCandidate = {
      tokenJWT: localStorage.getItem("tokenSpringRecruiter")!,
      bodyEmail: this.formSendMessageToCandidate.value.bodyEmail,
      emailCandidate: this.formSendMessageToCandidate.value.emailCandidate,
      nameVacancy: this.formSendMessageToCandidate.value.nameVacancy,
      nameCandidate: this.formSendMessageToCandidate.value.nameCandidate
    };
    this.recruiterService.sendMessageToCandidate(requestBody).subscribe({
      next: (data) => {
        this.successMessage.set("Correo enviado correctamente");
        this.formSendMessageToCandidate.reset();
        setTimeout(() => {
          this.dialogRef.close();
        }, 3500);
      },
      error: (error) => {
        this.errorMessage.set(error.message.message);
      }
    });
  }
}
