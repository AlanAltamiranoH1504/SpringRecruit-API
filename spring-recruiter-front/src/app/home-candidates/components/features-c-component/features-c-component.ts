import {Component} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {featuresCandidate} from '../../data/Features-Candidate';

@Component({
  selector: 'app-features-c-component',
  imports: [],
  templateUrl: './features-c-component.html',
  styleUrl: './features-c-component.css',
})
export class FeaturesCComponent {
  protected featuresArray = featuresCandidate;

  constructor(private sanitizer: DomSanitizer) {
  }

  protected getSafeIcon(iconString: string) {
    return this.sanitizer.bypassSecurityTrustHtml(iconString);
  }
}
