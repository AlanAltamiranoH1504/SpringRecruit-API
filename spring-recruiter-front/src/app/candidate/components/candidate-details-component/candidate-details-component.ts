import {Component, inject, input} from '@angular/core';
import {ApplicationDetailsDTO} from '../../../applications/types';
import {DatePipe} from '@angular/common';
import {ApplicationService} from '../../../applications/services/application-service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-candidate-details-component',
  imports: [
    DatePipe
  ],
  templateUrl: './candidate-details-component.html',
  styleUrl: './candidate-details-component.css',
})
export class CandidateDetailsComponent {
  public applicationCandidate = input.required<ApplicationDetailsDTO | null>();
  protected applicationService = inject(ApplicationService);
  protected router = inject(Router);

  protected accepApplication(idApplication: number) {
    console.log(`Aceptando la aplicacion id ${idApplication}`);
  }

  public rejectApplication(idApplication: number) {
    this.applicationService.rejectApplication(idApplication).subscribe({
      next: (data) => {
        this.router.navigate([`/dashboard/recruiter/work`])
      },
      error: (error) => {
        console.log(error)
      }
    });
  }
}
