import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HeaderComponent} from '../../components/header-component/header-component';
import {HeroComponent} from '../../components/hero-component/hero-component';
import {FeaturesComponent} from '../../components/features-component/features-component';
import {HowItWorkComponent} from '../../components/how-it-work-component/how-it-work-component';
import {FooterComponent} from '../../components/footer-component/footer-component';

@Component({
  selector: 'app-home-page',
  imports: [
    RouterOutlet,
    HeaderComponent,
    HeroComponent,
    FeaturesComponent,
    HowItWorkComponent,
    FooterComponent
  ],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage {

}
