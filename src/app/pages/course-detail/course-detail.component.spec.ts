import { TestBed } from '@angular/core/testing';
import { ActivatedRoute, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';
import { CourseDetailComponent } from './course-detail.component';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import { Course } from '../../models/course.model';

describe('CourseDetailComponent', () => {
  const mockCourse: Course = { id: 2, name: 'Algorithms', code: 'CS102', credits: 3, gradeStatus: 'pending' };

  beforeEach(async () => {
    const courseServiceSpy = jasmine.createSpyObj('CourseService', ['getCourseById']);
    courseServiceSpy.getCourseById.and.returnValue(of(mockCourse));

    const enrollmentServiceSpy = jasmine.createSpyObj('EnrollmentService', ['getStudentsByCourse']);
    enrollmentServiceSpy.getStudentsByCourse.and.returnValue(of(['Student A', 'Student B']));

    await TestBed.configureTestingModule({
      imports: [CourseDetailComponent],
      providers: [
        { provide: CourseService, useValue: courseServiceSpy },
        { provide: EnrollmentService, useValue: enrollmentServiceSpy },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: { paramMap: convertToParamMap({ id: '2' }) },
            paramMap: of(convertToParamMap({ id: '2' }))
          }
        }
      ]
    }).compileComponents();
  });

  it('should create and load the course for the route id', () => {
    const fixture = TestBed.createComponent(CourseDetailComponent);
    fixture.detectChanges();
    expect(fixture.componentInstance.courseId).toBe(2);
    expect(fixture.nativeElement.textContent).toContain('Algorithms');
  });
});
