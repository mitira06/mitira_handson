import EmployeeCard from './EmployeeCard';
import { employees } from './employeesdata';
import './employeesapp.css';

// EmployeesList does NOT receive or pass down a theme prop anymore.
// Each EmployeeCard reads the theme directly from ThemeContext.
function EmployeesList() {
  return (
    <div className="employees-list">
      {employees.map((employee) => (
        <EmployeeCard key={employee.id} employee={employee} />
      ))}
    </div>
  );
}

export default EmployeesList;
