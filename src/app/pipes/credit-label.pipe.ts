import { Pipe, PipeTransform } from '@angular/core';

// Hands-On 3, Task 3 (#35): transforms a credits number into a
// human-readable label. Handles null/0 as 'No Credits'.
@Pipe({
  name: 'creditLabel',
  standalone: true
})
export class CreditLabelPipe implements PipeTransform {
  transform(credits: number | null | undefined): string {
    if (credits === null || credits === undefined || credits === 0) {
      return 'No Credits';
    }
    if (credits === 1) {
      return '1 Credit';
    }
    return `${credits} Credits`;
  }
}
