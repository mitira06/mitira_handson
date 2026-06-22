import { useState } from 'react';
import ThemeContext from './ThemeContext';
import EmployeesList from './EmployeesList';
import './employeesapp.css';

// Top-level component for the employeesapp lab.
// Holds the theme in state and provides it to the whole tree via
// ThemeContext.Provider, instead of passing it down as props through
// EmployeesList to EmployeeCard.
function EmployeesApp() {
  const [theme, setTheme] = useState('light');

  function toggleTheme() {
    setTheme((prevTheme) => (prevTheme === 'light' ? 'dark' : 'light'));
  }

  // The theme provider wraps the entire JSX of this component.
  // Its value comes from the component's state.
  return (
    <ThemeContext.Provider value={theme}>
      <div className="employees-app">
        <h2>Employee Management</h2>
        <button className="theme-toggle-btn" onClick={toggleTheme}>
          Switch to {theme === 'light' ? 'Dark' : 'Light'} Theme
        </button>
        {/* EmployeesList no longer receives the theme as a prop */}
        <EmployeesList />
      </div>
    </ThemeContext.Provider>
  );
}

export default EmployeesApp;
