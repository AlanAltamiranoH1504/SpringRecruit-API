import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacancyDetailsPage } from './vacancy-details-page';

describe('VacancyDetailsPage', () => {
  let component: VacancyDetailsPage;
  let fixture: ComponentFixture<VacancyDetailsPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VacancyDetailsPage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VacancyDetailsPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
