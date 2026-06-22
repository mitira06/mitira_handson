import './ticketbooking.css';
import flights from './flightsmock';

// Guest users can only browse the available flight details.
// Booking is only available once the user is logged in (see UserPage).
function GuestPage() {
  return (
    <div className="ticket-booking">
      <h1>Please sign up.</h1>
      <p className="signup-note">
        Login to book tickets. Until then, you can browse today&#8217;s flights below.
      </p>
      <div className="flight-list">
        {flights.map((flight) => (
          <div className="flight-card" key={flight.id}>
            <div className="flight-info">
              <span className="flight-number">{flight.id}</span>
              <span className="flight-route">
                {flight.from} &rarr; {flight.to} ({flight.time})
              </span>
            </div>
            <button className="book-btn" disabled>
              Book
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default GuestPage;
