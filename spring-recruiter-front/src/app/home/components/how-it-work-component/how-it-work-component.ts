import { Component } from '@angular/core';
import {steps} from '../../data/Steps';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-how-it-work-component',
  imports: [],
  templateUrl: './how-it-work-component.html',
  styleUrl: './how-it-work-component.css',
})
export class HowItWorkComponent {
  protected stepsArray = steps;

  constructor(private sanitizer: DomSanitizer) {
  }

  protected getIconSanitizer(iconString: string) {
    return this.sanitizer.bypassSecurityTrustHtml(iconString);
  }
}
