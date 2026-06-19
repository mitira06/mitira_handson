import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface AppNotification {
  message: string;
  type: 'info' | 'error' | 'success';
}

// Hands-On 6, Task 2 (#67): this service is provided at the COMPONENT level
// (see NotificationComponent) rather than providedIn: 'root'. That creates a
// new, separate instance scoped to NotificationComponent and its children,
// rather than a single app-wide singleton.
@Injectable()
export class NotificationService {
  private notificationSubject = new BehaviorSubject<AppNotification | null>(null);
  notification$ = this.notificationSubject.asObservable();

  show(message: string, type: AppNotification['type'] = 'info'): void {
    this.notificationSubject.next({ message, type });
    setTimeout(() => this.notificationSubject.next(null), 4000);
  }

  clear(): void {
    this.notificationSubject.next(null);
  }
}
