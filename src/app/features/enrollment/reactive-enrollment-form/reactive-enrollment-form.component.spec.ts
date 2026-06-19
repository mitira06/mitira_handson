import { TestBed } from '@angular/core/testing';
import { ReactiveEnrollmentFormComponent, noCourseCode } from './reactive-enrollment-form.component';
import { FormControl } from '@angular/forms';

describe('ReactiveEnrollmentFormComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveEnrollmentFormComponent]
    }).compileComponents();
  });

  it('should create and build the form', () => {
    const fixture = TestBed.createComponent(ReactiveEnrollmentFormComponent);
    fixture.detectChanges();
    expect(fixture.componentInstance.enrollForm).toBeTruthy();
    expect(fixture.componentInstance.enrollForm.get('preferredSemester')?.value).toBe('Odd');
  });

  it('noCourseCode validator rejects codes starting with XX', () => {
    const control = new FormControl('XX101');
    expect(noCourseCode(control)).toEqual({ noCourseCode: true });
  });

  it('noCourseCode validator allows normal codes', () => {
    const control = new FormControl('CS101');
    expect(noCourseCode(control)).toBeNull();
  });

  it('addAdditionalCourse pushes a new control into the FormArray', () => {
    const fixture = TestBed.createComponent(ReactiveEnrollmentFormComponent);
    fixture.detectChanges();
    const component = fixture.componentInstance;

    expect(component.additionalCourses.length).toBe(0);
    component.addAdditionalCourse();
    expect(component.additionalCourses.length).toBe(1);

    component.removeCourse(0);
    expect(component.additionalCourses.length).toBe(0);
  });
});
