import { CohortsData } from './Cohort';
import CohortDetails from './CohortDetails';

function Lab5() {
  return (
    <div className="lab-content">
      <h1>Cohorts Details</h1>
      {CohortsData.map((cohort) => (
        <CohortDetails cohort={cohort} key={cohort.cohortCode} />
      ))}
    </div>
  );
}

export default Lab5;
