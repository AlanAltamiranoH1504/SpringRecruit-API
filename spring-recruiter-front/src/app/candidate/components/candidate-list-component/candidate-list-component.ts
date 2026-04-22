import {Component, input} from '@angular/core';
import {required} from '@angular/forms/signals';

@Component({
  selector: 'app-candidate-list-component',
  imports: [],
  templateUrl: './candidate-list-component.html',
  styleUrl: './candidate-list-component.css',
})
export class CandidateListComponent {
  public idVacancy = input.required<number>();
}
