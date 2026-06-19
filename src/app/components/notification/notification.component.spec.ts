import { TestBed } from '@angular/core/testing';
import { NotificationComponent } from './notification.component';

describe('NotificationComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotificationComponent]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(NotificationComponent);
    expect(fixture.componentInstance).toBeTruthy();
  });

  it('should show a message after notificationService.show()', () => {
    const fixture = TestBed.createComponent(NotificationComponent);
    fixture.componentInstance.notificationService.show('Hello', 'success');
    fixture.detectChanges();
    expect(fixture.nativeElement.textContent).toContain('Hello');
  });
});
