import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Course } from '../models/course.model';
import { EnrollmentRequest } from '../models/enrollment-request.model';
import { CourseService } from './course.service';

const ENROLLMENTS_API_URL = 'http://localhost:3000/enrollments';

// Hands-On 6, Task 2: EnrollmentService demonstrates service-to-service
// injection by depending on CourseService to resolve enrolled course IDs
// into full Course objects.
@Injectable({ providedIn: 'root' })
export class EnrollmentService {
  private enrolledCourseIds: number[] = [];

  constructor(private courseService: CourseService, private http: HttpClient) {}

  enroll(courseId: number): void {
    if (!this.enrolledCourseIds.includes(courseId)) {
      this.enrolledCourseIds = [...this.enrolledCourseIds, courseId];
    }
  }

  unenroll(courseId: number): void {
    this.enrolledCourseIds = this.enrolledCourseIds.filter(id => id !== courseId);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  getEnrolledCourseIds(): number[] {
    return this.enrolledCourseIds;
  }

  getEnrolledCourses(): Observable<Course[]> {
    return this.courseService.getCourses().pipe(
      map(courses => courses.filter(c => this.enrolledCourseIds.includes(c.id)))
    );
  }

  // Hands-On 8, Task 2: dependent HTTP call - used with switchMap so that
  // selecting a new course cancels the previous in-flight request.
  getStudentsByCourse(courseId: number): Observable<string[]> {
    return this.courseService.getCourseById(courseId).pipe(
      map(course => [`Student A (${course.code})`, `Student B (${course.code})`])
    );
  }

  // Hands-On 4: persists an enrollment request to its own /enrollments
  // collection - keeps /courses as the clean course catalog.
  submitEnrollmentRequest(request: EnrollmentRequest): Observable<EnrollmentRequest> {
    return this.http.post<EnrollmentRequest>(ENROLLMENTS_API_URL, request);
  }
}