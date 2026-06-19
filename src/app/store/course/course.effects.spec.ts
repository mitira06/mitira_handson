import { TestBed } from '@angular/core/testing';
import { Actions } from '@ngrx/effects';
import { provideMockActions } from '@ngrx/effects/testing';
import { Observable, of, throwError } from 'rxjs';
import { CourseEffects } from './course.effects';
import { CourseService } from '../../services/course.service';
import { loadCourses, loadCoursesFailure, loadCoursesSuccess } from './course.actions';
import { Course } from '../../models/course.model';

describe('CourseEffects', () => {
  let actions$: Observable<any>;
  let effects: CourseEffects;
  let courseServiceSpy: jasmine.SpyObj<CourseService>;

  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' }
  ];

  beforeEach(() => {
    courseServiceSpy = jasmine.createSpyObj('CourseService', ['getCourses']);

    TestBed.configureTestingModule({
      providers: [
        CourseEffects,
        provideMockActions(() => actions$),
        { provide: CourseService, useValue: courseServiceSpy }
      ]
    });

    effects = TestBed.inject(CourseEffects);
  });

  it('should dispatch loadCoursesSuccess on success', (done) => {
    courseServiceSpy.getCourses.and.returnValue(of(mockCourses));
    actions$ = of(loadCourses());

    effects.loadCourses$.subscribe((action) => {
      expect(action).toEqual(loadCoursesSuccess({ courses: mockCourses }));
      done();
    });
  });

  it('should dispatch loadCoursesFailure on error', (done) => {
    courseServiceSpy.getCourses.and.returnValue(throwError(() => new Error('Network error')));
    actions$ = of(loadCourses());

    effects.loadCourses$.subscribe((action) => {
      expect(action).toEqual(loadCoursesFailure({ error: 'Network error' }));
      done();
    });
  });
});
