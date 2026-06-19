import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CoursesLayoutComponent } from './pages/courses-layout/courses-layout.component';
import { CourseListComponent } from './pages/course-list/course-list.component';
import { CourseDetailComponent } from './pages/course-detail/course-detail.component';
import { StudentProfileComponent } from './pages/student-profile/student-profile.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { authGuard } from './guards/auth.guard';

// Hands-On 7: route configuration with nested routes, lazy loading and guards.
// The ** wildcard route is always last, since Angular matches routes in order.
export const routes: Routes = [
  { path: '', component: HomeComponent, title: 'Home | Student Course Portal' },
  {
    path: 'courses',
    component: CoursesLayoutComponent,
    children: [
      { path: '', component: CourseListComponent, title: 'Courses | Student Course Portal' },
      { path: ':id', component: CourseDetailComponent, title: 'Course Detail | Student Course Portal' }
    ]
  },
  {
    path: 'profile',
    component: StudentProfileComponent,
    canActivate: [authGuard],
    title: 'My Profile | Student Course Portal'
  },
  {
    // Lazy loaded feature module (Hands-On 7, Task 2)
    path: 'enroll',
    canActivate: [authGuard],
    loadChildren: () => import('./features/enrollment/enrollment.routes').then(m => m.ENROLLMENT_ROUTES)
  },
  { path: '**', component: NotFoundComponent, title: 'Not Found | Student Course Portal' }
];
