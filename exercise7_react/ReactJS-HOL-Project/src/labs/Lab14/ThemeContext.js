import { createContext } from 'react';

// Theme context for the employeesapp. Default value is 'light'.
// Any component nested under a ThemeContext.Provider can read this value
// with useContext(ThemeContext) without it being passed down via props.
const ThemeContext = createContext('light');

export default ThemeContext;
