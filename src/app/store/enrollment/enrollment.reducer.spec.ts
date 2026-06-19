import { enrollmentReducer, initialEnrollmentState } from './enrollment.reducer';
import { enrollInCourse, setEnrolledCourses, unenrollFromCourse } from './enrollment.actions';

describe('enrollmentReducer', () => {
  it('should add a course id on enrollInCourse', () => {
    const state = enrollmentReducer(initialEnrollmentState, enrollInCourse({ courseId: 1 }));
    expect(state.enrolledCourseIds).toEqual([1]);
  });

  it('should not duplicate ids when enrolling twice', () => {
    let state = enrollmentReducer(initialEnrollmentState, enrollInCourse({ courseId: 1 }));
    state = enrollmentReducer(state, enrollInCourse({ courseId: 1 }));
    expect(state.enrolledCourseIds).toEqual([1]);
  });

  it('should remove a course id on unenrollFromCourse', () => {
    let state = enrollmentReducer(initialEnrollmentState, enrollInCourse({ courseId: 1 }));
    state = enrollmentReducer(state, unenrollFromCourse({ courseId: 1 }));
    expect(state.enrolledCourseIds).toEqual([]);
  });

  it('should set the enrolled course ids on setEnrolledCourses', () => {
    const state = enrollmentReducer(initialEnrollmentState, setEnrolledCourses({ courseIds: [1, 2, 3] }));
    expect(state.enrolledCourseIds).toEqual([1, 2, 3]);
  });
});
