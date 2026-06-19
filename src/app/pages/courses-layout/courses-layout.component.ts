import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';

// Hands-On 7, Task 1 (#72): nested routes layout - wraps CourseListComponent
// and CourseDetailComponent with a shared <router-outlet>.
@Component({
  selector: 'app-courses-layout',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './courses-layout.component.html',
  styleUrls: ['./courses-layout.component.css']
})
export class CoursesLayoutComponent {}
