import { TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { CoursesLayoutComponent } from './courses-layout.component';

describe('CoursesLayoutComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoursesLayoutComponent],
      providers: [provideRouter([])]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(CoursesLayoutComponent);
    expect(fixture.componentInstance).toBeTruthy();
  });
});
