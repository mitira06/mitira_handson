import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../services/notification.service';

// Hands-On 6, Task 2 (#67): NotificationService is provided HERE, at the
// component level, via the `providers` array. This creates a new, separate
// instance of NotificationService scoped to this component (and any
// children) - distinct from any root-level singleton. Useful when isolated,
// per-component state is desired (e.g. a form wizard with multiple steps).
@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule],
  providers: [NotificationService],
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent {
  constructor(public notificationService: NotificationService) {}
}
