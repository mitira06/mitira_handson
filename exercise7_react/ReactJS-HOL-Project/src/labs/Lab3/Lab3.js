import { CalculateScore } from './Components/CalculateScore';

function Lab3() {
  return (
    <div className="lab-content">
      <CalculateScore
        Name={"Steeve"}
        School={"DNV Public School"}
        total={284}
        goal={3}
      />
    </div>
  );
}

export default Lab3;
