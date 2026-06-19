import { courseReducer, initialCourseState } from './course.reducer';
import { loadCourses, loadCoursesFailure, loadCoursesSuccess } from './course.actions';
import { Course } from '../../models/course.model';

describe('courseReducer', () => {
  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' }
  ];

  it('should set loading to true on loadCourses', () => {
    const state = courseReducer(initialCourseState, loadCourses());
    expect(state.loading).toBeTrue();
    expect(state.error).toBeNull();
  });

  it('should set courses and loading false on loadCoursesSuccess', () => {
    const state = courseReducer({ ...initialCourseState, loading: true }, loadCoursesSuccess({ courses: mockCourses }));
    expect(state.courses).toEqual(mockCourses);
    expect(state.loading).toBeFalse();
  });

  it('should set the error and loading false on loadCoursesFailure', () => {
    const state = courseReducer({ ...initialCourseState, loading: true }, loadCoursesFailure({ error: 'Boom' }));
    expect(state.error).toBe('Boom');
    expect(state.loading).toBeFalse();
  });
});
