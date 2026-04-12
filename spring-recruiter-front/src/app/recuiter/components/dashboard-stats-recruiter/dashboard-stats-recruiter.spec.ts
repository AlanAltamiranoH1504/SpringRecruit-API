import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardStatsRecruiter } from './dashboard-stats-recruiter';

describe('DashboardStatsRecruiter', () => {
  let component: DashboardStatsRecruiter;
  let fixture: ComponentFixture<DashboardStatsRecruiter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardStatsRecruiter]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardStatsRecruiter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
