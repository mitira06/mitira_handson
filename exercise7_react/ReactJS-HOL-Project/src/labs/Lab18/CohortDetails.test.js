import React from 'react';
import ReactDOM from 'react-dom/client';
import { act } from 'react-dom/test-utils';
import CohortDetails from '../Lab5/CohortDetails';
import { CohortsData } from '../Lab5/Cohort';

let container;

beforeEach(() => {
  container = document.createElement('div');
  document.body.appendChild(container);
});

afterEach(() => {
  document.body.removeChild(container);
  container = null;
});

describe('Cohort Details Component', () => {

  // Test 1: load the CohortDetails component in isolation
  test('should create the component', () => {
    act(() => {
      ReactDOM.createRoot(container).render(
        <CohortDetails cohort={CohortsData[0]} />
      );
    });
    expect(container).toBeTruthy();
  });

  // Test 2: verify props are assigned and rendered
  test('should initialize the props', () => {
    act(() => {
      ReactDOM.createRoot(container).render(
        <CohortDetails cohort={CohortsData[0]} />
      );
    });
    expect(container.textContent).toContain('Aathma');
    expect(container.textContent).toContain('Jojo Jose');
  });

  // Test 3: verify cohort code is displayed in h3
  test('should display cohort code in h3', () => {
    const cohort = CohortsData[0];
    act(() => {
      ReactDOM.createRoot(container).render(
        <CohortDetails cohort={cohort} />
      );
    });
    const h3 = container.querySelector('h3');
    expect(h3.textContent).toContain(cohort.cohortCode);
  });

  // Test 4: snapshot test
  test('should always render same html', () => {
    act(() => {
      ReactDOM.createRoot(container).render(
        <CohortDetails cohort={CohortsData[0]} />
      );
    });
    expect(container.innerHTML).toMatchSnapshot();
  });

});
