import {Component, signal} from '@angular/core';
import {features} from '../../data/Features';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-features-component',
  imports: [],
  templateUrl: './features-component.html',
  styleUrl: './features-component.css',
})
export class FeaturesComponent {
  protected featuresArray = features;

  constructor(private sanitizer: DomSanitizer) {
  }

  getSafeIcon(iconString: string) {
    return this.sanitizer.bypassSecurityTrustHtml(iconString);
  }
}
