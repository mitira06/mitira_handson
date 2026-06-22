import './ticketbooking.css';

// Stateless button that just reports the click to its parent via props.onClick
function LogoutButton(props) {
  return (
    <button className="ticket-btn" onClick={props.onClick}>
      Logout
    </button>
  );
}

export default LogoutButton;
