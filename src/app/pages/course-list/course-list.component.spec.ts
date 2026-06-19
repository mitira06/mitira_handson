import { TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { provideMockStore } from '@ngrx/store/testing';
import { CourseListComponent } from './course-list.component';
import { selectAllCourses, selectCoursesError, selectCoursesLoading } from '../../store/course/course.selectors';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

describe('CourseListComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseListComponent],
      providers: [
        provideRouter([]),
        provideMockStore({
          selectors: [
            { selector: selectAllCourses, value: [] },
            { selector: selectCoursesLoading, value: false },
            { selector: selectCoursesError, value: null },
            { selector: selectEnrolledIds, value: [] }
          ]
        })
      ]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(CourseListComponent);
    expect(fixture.componentInstance).toBeTruthy();
  });

  it('trackByCourseId returns the course id', () => {
    const fixture = TestBed.createComponent(CourseListComponent);
    const id = fixture.componentInstance.trackByCourseId(0, {
      id: 7, name: 'X', code: 'X1', credits: 1, gradeStatus: 'pending'
    });
    expect(id).toBe(7);
  });

  it('shows "No courses available." when the list is empty', () => {
    const fixture = TestBed.createComponent(CourseListComponent);
    fixture.detectChanges();
    expect(fixture.nativeElement.textContent).toContain('No courses available.');
  });
});
