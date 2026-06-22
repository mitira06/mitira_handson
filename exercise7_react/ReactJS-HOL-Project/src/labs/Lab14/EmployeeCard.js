import { useContext } from 'react';
import ThemeContext from './ThemeContext';
import './employeesapp.css';

// EmployeeCard no longer receives the theme name as a prop.
// It imports ThemeContext directly and reads the current value with
// useContext(), then uses that value to decide the button className.
function EmployeeCard(props) {
  const theme = useContext(ThemeContext);
  const buttonClassName = theme === 'dark' ? 'btn-dark' : 'btn-light';

  return (
    <div className="employee-card">
      <div className="employee-info">
        <span className="employee-name">{props.employee.name}</span>
        <span className="employee-role">{props.employee.role}</span>
      </div>
      <div className="employee-actions">
        <button className={buttonClassName}>View Profile</button>
        <button className={buttonClassName}>Send Message</button>
      </div>
    </div>
  );
}

export default EmployeeCard;
