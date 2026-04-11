import {Component} from '@angular/core';
import {StepsCandidate} from '../../data/Steps-Candidate';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-how-its-work-c-component',
  imports: [],
  templateUrl: './how-its-work-c-component.html',
  styleUrl: './how-its-work-c-component.css',
})
export class HowItsWorkCComponent {
  protected stepsArray = StepsCandidate;

  constructor(private sanizate: DomSanitizer) {
  }

  protected getSafeIcon(iconString: string) {
    return this.sanizate.bypassSecurityTrustHtml(iconString);
  }
}
