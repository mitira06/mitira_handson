import { Component, EventEmitter, Input, Output, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Course } from '../../models/course.model';
import { HighlightDirective } from '../../directives/highlight.directive';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';

// Hands-On 2 (Task 2 & 3) + Hands-On 3 (Tasks 1-3):
// CourseCardComponent renders a single course and demonstrates:
// - @Input / @Output parent-child communication
// - ngOnChanges lifecycle hook
// - *ngSwitch for the grade status badge
// - [ngClass] / [ngStyle] attribute directives
// - the custom appHighlight directive and creditLabel pipe
@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CommonModule, HighlightDirective, CreditLabelPipe],
  templateUrl: './course-card.component.html',
  styleUrls: ['./course-card.component.css']
})
export class CourseCardComponent implements OnChanges {
  @Input() course!: Course;
  /** Whether the current student is enrolled in this course (driven by the NgRx store). */
  @Input() enrolled = false;
  @Output() enrollRequested = new EventEmitter<number>();

  isExpanded = false;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      console.log(
        'CourseCardComponent course changed - previous:',
        changes['course'].previousValue,
        'current:',
        changes['course'].currentValue
      );
    }
  }

  toggleExpanded(): void {
    this.isExpanded = !this.isExpanded;
  }

  onEnrollClick(): void {
    this.enrollRequested.emit(this.course.id);
  }

  // A getter keeps [ngClass] bindings out of the template - the template
  // simply binds [ngClass]="cardClasses" instead of an inline object literal,
  // which is easier to read and easier to unit test.
  get cardClasses(): Record<string, boolean> {
    return {
      'card--enrolled': this.enrolled,
      'card--full': this.course.credits >= 4,
      expanded: this.isExpanded
    };
  }

  get borderStyle(): Record<string, string> {
    const colors: Record<Course['gradeStatus'], string> = {
      passed: '#16a34a',
      failed: '#dc2626',
      pending: '#9ca3af'
    };
    return { 'border-left-color': colors[this.course.gradeStatus] };
  }
}
