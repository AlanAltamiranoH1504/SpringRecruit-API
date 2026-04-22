import {Component, inject, input, signal} from '@angular/core';
import {required} from '@angular/forms/signals';
import {ApplicationDetailsDTO} from '../../../applications/types';
import {ApplicationService} from '../../../applications/services/application-service';
import {CandidateDetailsComponent} from '../candidate-details-component/candidate-details-component';

@Component({
  selector: 'app-candidate-list-component',
  imports: [
    CandidateDetailsComponent
  ],
  templateUrl: './candidate-list-component.html',
  styleUrl: './candidate-list-component.css',
})
export class CandidateListComponent {
  public idVacancy = input.required<number>();
  protected applications = signal<ApplicationDetailsDTO[]>([]);
  protected applicationService = inject(ApplicationService);
  protected errorMessage = signal<string | null>(null);

  ngOnInit() {
    this.applicationService.getApplicationsDetailsByIdVacancy(+this.idVacancy()).subscribe({
      next: (applications) => {
        this.applications.set(applications)
      },
      error: (error) => {
        this.errorMessage.set(error.message.message);
      }
    });
  }
}
