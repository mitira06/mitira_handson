import { TestBed } from '@angular/core/testing';
import { provideMockStore } from '@ngrx/store/testing';
import { StudentProfileComponent } from './student-profile.component';
import { selectEnrolledCourses } from '../../store/enrollment/enrollment.selectors';

describe('StudentProfileComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentProfileComponent],
      providers: [
        provideMockStore({
          selectors: [{ selector: selectEnrolledCourses, value: [] }]
        })
      ]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(StudentProfileComponent);
    expect(fixture.componentInstance).toBeTruthy();
  });

  it('shows the "no enrollments" message when there are none', () => {
    const fixture = TestBed.createComponent(StudentProfileComponent);
    fixture.detectChanges();
    expect(fixture.nativeElement.textContent).toContain('not enrolled');
  });
});
