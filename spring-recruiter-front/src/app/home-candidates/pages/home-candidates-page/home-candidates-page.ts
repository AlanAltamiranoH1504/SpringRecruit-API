import { Component } from '@angular/core';
import {HeaderComponent} from '../../../home/components/header-component/header-component';
import {HeroCComponent} from '../../components/hero-c-component/hero-c-component';
import {FooterComponent} from '../../../home/components/footer-component/footer-component';
import {FeaturesCComponent} from '../../components/features-c-component/features-c-component';
import {HowItsWorkCComponent} from '../../components/how-its-work-c-component/how-its-work-c-component';

@Component({
  selector: 'app-home-candidates-page',
  imports: [
    HeaderComponent,
    HeroCComponent,
    FooterComponent,
    FeaturesCComponent,
    HowItsWorkCComponent
  ],
  templateUrl: './home-candidates-page.html',
  styleUrl: './home-candidates-page.css',
})
export class HomeCandidatesPage {

}
