import { useState } from 'react';
import Greeting from './Greeting';
import LoginButton from './LoginButton';
import LogoutButton from './LogoutButton';
import './ticketbooking.css';

// Top-level stateful component for the ticketbookingapp lab.
// Holds the isLoggedIn flag and conditionally renders either the
// Login or Logout button alongside the Greeting (Guest/User page).
function TicketBookingApp() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  function handleLoginClick() {
    setIsLoggedIn(true);
  }

  function handleLogoutClick() {
    setIsLoggedIn(false);
  }

  // Element variable for the button: only one of Login/Logout is rendered
  let button;
  if (isLoggedIn) {
    button = <LogoutButton onClick={handleLogoutClick} />;
  } else {
    button = <LoginButton onClick={handleLoginClick} />;
  }

  return (
    <div className="lab-content">
      <Greeting isLoggedIn={isLoggedIn} />
      {button}
    </div>
  );
}

export default TicketBookingApp;
