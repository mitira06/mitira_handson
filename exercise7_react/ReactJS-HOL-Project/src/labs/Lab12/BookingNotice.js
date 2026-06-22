import './ticketbooking.css';

// Demonstrates "preventing components from rendering": if there is nothing
// booked yet, this component returns null and renders nothing at all.
function BookingNotice(props) {
  if (!props.lastBookedId) {
    return null;
  }

  return (
    <div className="booking-notice">
      Booking confirmed for flight {props.lastBookedId}!
    </div>
  );
}

export default BookingNotice;
