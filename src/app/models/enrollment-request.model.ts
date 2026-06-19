// Used when a student submits the enrollment request form (Hands-On 4).
// Posted to /enrollments in db.json - kept separate from /courses so the
// course catalog stays clean.
export interface EnrollmentRequest {
  studentName: string;
  studentEmail: string;
  courseId: number;
  preferredSemester: 'Odd' | 'Even';
  agreeToTerms: boolean;
}