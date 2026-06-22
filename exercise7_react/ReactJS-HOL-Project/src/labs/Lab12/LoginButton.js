import './ticketbooking.css';

// Stateless button that just reports the click to its parent via props.onClick
function LoginButton(props) {
  return (
    <button className="ticket-btn" onClick={props.onClick}>
      Login
    </button>
  );
}

export default LoginButton;
