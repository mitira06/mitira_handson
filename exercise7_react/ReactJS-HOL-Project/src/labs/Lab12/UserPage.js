import { useState } from 'react';
import './ticketbooking.css';
import flights from './flightsmock';
import BookingNotice from './BookingNotice';

// Logged in users see the User page, and can book tickets for any flight.
function UserPage() {
  const [bookedIds, setBookedIds] = useState([]);
  const [lastBookedId, setLastBookedId] = useState(null);

  function bookFlight(id) {
    setBookedIds((prevBookedIds) => [...prevBookedIds, id]);
    setLastBookedId(id);
  }

  return (
    <div className="ticket-booking">
      <h1>Welcome back</h1>
      <p className="signup-note">You're logged in &#8212; go ahead and book a flight.</p>
      {/* BookingNotice renders null until a flight has actually been booked */}
      <BookingNotice lastBookedId={lastBookedId} />
      <div className="flight-list">
        {flights.map((flight) => {
          const isBooked = bookedIds.includes(flight.id);
          return (
            <div className="flight-card" key={flight.id}>
              <div className="flight-info">
                <span className="flight-number">{flight.id}</span>
                <span className="flight-route">
                  {flight.from} &rarr; {flight.to} ({flight.time})
                </span>
              </div>
              <button
                className="book-btn"
                disabled={isBooked}
                onClick={() => bookFlight(flight.id)}
              >
                {isBooked ? 'Booked' : 'Book'}
              </button>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default UserPage;
