# Student Course Portal — Angular (v20)

A single Angular application built incrementally across the 10 Hands-On
exercises of the Digital Nurture 5.0 Angular module: components, data
binding & lifecycle hooks, directives & pipes, template-driven and
reactive forms, services & DI, routing with guards & lazy loading, HTTP
integration with interceptors, NgRx state management, and unit tests.

## 1. Install dependencies

```bash
npm install
```

If you hit peer-dependency conflicts between NgRx and Angular 20, run:

```bash
npm install --legacy-peer-deps
```

or use `ng add @ngrx/store @ngrx/effects @ngrx/store-devtools @ngrx/entity`
to let the Angular CLI pick compatible versions for your installed Angular
version.

## 2. Start the mock backend (Hands-On 8)

The course list, course detail, and HTTP interceptors expect a JSON Server
backend on port 3000, using the included `db.json`.

```bash
npm install -g json-server
json-server --watch db.json --port 3000
```

## 3. Run the app

```bash
ng serve
```

Open http://localhost:4200.

## 4. Run unit tests (Hands-On 10)

```bash
ng test
```

Add `--code-coverage` to generate a report in `coverage/`.

## Project map

| Folder | Hands-On | What's there |
| --- | --- | --- |
| `src/app/components/header` | 1 | Top nav bar |
| `src/app/pages/home` | 1, 2 | Dashboard, binding types, lifecycle hooks |
| `src/app/components/course-card`, `pages/course-list` | 2, 3, 9 | @Input/@Output, *ngFor/*ngIf/*ngSwitch, ngClass/ngStyle, NgRx-driven list |
| `src/app/directives/highlight.directive.ts` | 3 | Custom `appHighlight` attribute directive |
| `src/app/pipes/credit-label.pipe.ts` | 3 | Custom `creditLabel` pipe |
| `src/app/features/enrollment/enrollment-form` | 4 | Template-driven form + validation |
| `src/app/features/enrollment/reactive-enrollment-form` | 5 | Reactive form, FormBuilder, custom + async validators, FormArray |
| `src/app/services` | 6 | CourseService, EnrollmentService, AuthService, NotificationService, LoadingService |
| `src/app/pages/courses-layout`, `pages/course-detail`, `pages/student-profile`, `pages/not-found`, `app.routes.ts`, `guards/` | 7 | Routing, params, query params, nested routes, lazy loading, guards |
| `src/app/services/course.service.ts`, `interceptors/` | 8 | HttpClient CRUD, RxJS operators, interceptors |
| `src/app/store` | 9 | NgRx actions/reducers/selectors/effects for courses & enrollments |
| `**/*.spec.ts` | 10 | Jasmine/Karma component, service, effects and reducer tests |

## Notes

- `notes.txt` contains the Hands-On 1 file-by-file write-up.
- `AuthService.isLoggedIn` is hardcoded to `true` so `/profile` and `/enroll`
  are reachable out of the box. Set it to `false` (in
  `src/app/services/auth.service.ts`) to see the `authGuard` redirect.
- The reactive enrollment form (`/enroll/reactive`) is protected by
  `unsavedChangesGuard` — make an edit and try navigating away to see the
  confirmation dialog.
- Open Redux DevTools to watch NgRx actions (`[Course] Load Courses`, etc.)
  as you navigate.
