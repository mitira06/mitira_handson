import { Directive, ElementRef, HostListener, Input } from '@angular/core';

// Hands-On 3, Task 3 (#33, #37): custom attribute directive.
// Highlights the host element on mouseenter and removes the highlight on
// mouseleave. The colour is configurable via the appHighlight input.
@Directive({
  selector: '[appHighlight]',
  standalone: true
})
export class HighlightDirective {
  @Input() appHighlight = 'yellow';

  constructor(private el: ElementRef<HTMLElement>) {}

  @HostListener('mouseenter')
  onMouseEnter(): void {
    this.highlight(this.appHighlight || 'yellow');
  }

  @HostListener('mouseleave')
  onMouseLeave(): void {
    this.highlight('');
  }

  private highlight(color: string): void {
    this.el.nativeElement.style.backgroundColor = color;
  }
}
