import { TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { CourseCardComponent } from './course-card.component';
import { Course } from '../../models/course.model';

// Hands-On 10, Task 1: component tests covering rendering, @Input and
// @Output, and the ngOnChanges lifecycle hook.
describe('CourseCardComponent', () => {
  const mockCourse: Course = {
    id: 1,
    name: 'Data Structures',
    code: 'CS101',
    credits: 4,
    gradeStatus: 'passed'
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseCardComponent]
    }).compileComponents();
  });

  it('should create', () => {
    const fixture = TestBed.createComponent(CourseCardComponent);
    fixture.componentInstance.course = mockCourse;
    expect(fixture.componentInstance).toBeTruthy();
  });

  it('should render the course name from @Input', () => {
    const fixture = TestBed.createComponent(CourseCardComponent);
    fixture.componentInstance.course = mockCourse;
    fixture.detectChanges();

    const heading = fixture.debugElement.query(By.css('h3')).nativeElement as HTMLElement;
    expect(heading.textContent).toContain('Data Structures');
  });

  it('should emit enrollRequested with the course id on enroll click', () => {
    const fixture = TestBed.createComponent(CourseCardComponent);
    const component = fixture.componentInstance;
    component.course = mockCourse;
    fixture.detectChanges();

    spyOn(component.enrollRequested, 'emit');

    const enrollButton = fixture.debugElement.query(By.css('.actions button')).nativeElement as HTMLButtonElement;
    enrollButton.click();

    expect(component.enrollRequested.emit).toHaveBeenCalledWith(1);
  });

  it('should log on ngOnChanges when the course input changes', () => {
    const fixture = TestBed.createComponent(CourseCardComponent);
    const component = fixture.componentInstance;
    component.course = mockCourse;

    spyOn(console, 'log');

    component.ngOnChanges({
      course: {
        previousValue: undefined,
        currentValue: mockCourse,
        firstChange: true,
        isFirstChange: () => true
      }
    });

    expect(console.log).toHaveBeenCalled();
  });

  it('should toggle expanded details on "Show Details" click', () => {
    const fixture = TestBed.createComponent(CourseCardComponent);
    const component = fixture.componentInstance;
    component.course = mockCourse;
    fixture.detectChanges();

    const detailsButton = fixture.debugElement.query(By.css('.actions button.secondary')).nativeElement as HTMLButtonElement;
    detailsButton.click();
    fixture.detectChanges();

    expect(component.isExpanded).toBeTrue();
    expect(fixture.debugElement.query(By.css('.details'))).toBeTruthy();
  });
});
