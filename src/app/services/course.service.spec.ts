import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { CourseService } from './course.service';
import { Course } from '../models/course.model';

// Hands-On 10, Task 2: service testing with HttpClientTestingModule
// (provideHttpClientTesting in standalone Angular).
describe('CourseService', () => {
  let service: CourseService;
  let httpMock: HttpTestingController;

  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' },
    { id: 2, name: 'Algorithms', code: 'CS102', credits: 3, gradeStatus: 'pending' }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CourseService, provideHttpClient(), provideHttpClientTesting()]
    });

    service = TestBed.inject(CourseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should fetch courses from the API', () => {
    service.getCourses().subscribe((courses) => {
      expect(courses.length).toBe(2);
    });

    const req = httpMock.expectOne('http://localhost:3000/courses');
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);
  });

  it('should propagate an error when the request fails', () => {
    service.getCourses().subscribe({
      next: () => fail('expected an error'),
      error: (err) => expect(err.message).toContain('Failed to load courses')
    });

    // retry(2) means the request fires 3 times in total before erroring.
    for (let i = 0; i < 3; i++) {
      const req = httpMock.expectOne('http://localhost:3000/courses');
      req.flush('Server error', { status: 500, statusText: 'Internal Server Error' });
    }
  });

  it('should post a new course', () => {
    const newCourse = { name: 'New Course', code: 'CS200', credits: 2, gradeStatus: 'pending' as const };

    service.createCourse(newCourse).subscribe((course) => {
      expect(course.id).toBe(3);
    });

    const req = httpMock.expectOne('http://localhost:3000/courses');
    expect(req.request.method).toBe('POST');
    req.flush({ id: 3, ...newCourse });
  });
});
