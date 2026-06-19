// Hands-On 6, Task 1: shared Course model used across services, store and components.
export interface Course {
  id: number;
  name: string;
  code: string;
  credits: number;
  gradeStatus: 'passed' | 'failed' | 'pending';
}
