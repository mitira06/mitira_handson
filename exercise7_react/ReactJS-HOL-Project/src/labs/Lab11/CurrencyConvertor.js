import React from 'react';
import './eventexamplesapp.css';

// Fixed conversion rate used for this lab: 1 Indian Rupee = 0.011 Euro
const INR_TO_EUR_RATE = 0.011;

// CurrencyConvertor converts Indian Rupees to Euro when the Convert
// button is clicked, by handling the button's onClick event and invoking
// handleSubmit.
class CurrencyConvertor extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rupees: '',
      euro: null,
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  // Updates state as the user types into the Rupees input
  handleChange(event) {
    this.setState({ rupees: event.target.value });
  }

  // Handles the Convert button click event: converts rupees to euro
  handleSubmit(event) {
    event.preventDefault();
    const rupeeValue = parseFloat(this.state.rupees) || 0;
    const euroValue = (rupeeValue * INR_TO_EUR_RATE).toFixed(2);
    this.setState({ euro: euroValue });
  }

  render() {
    return (
      <div className="lab-content">
        <h2>Currency Convertor</h2>
        <div className="currency-row">
          <label htmlFor="rupees">Indian Rupees (₹):</label>
          <input
            id="rupees"
            type="number"
            value={this.state.rupees}
            onChange={this.handleChange}
            placeholder="Enter amount in Rs."
          />
          <button className="event-btn" onClick={this.handleSubmit}>
            Convert
          </button>
        </div>
        {this.state.euro !== null && (
          <p className="currency-result">
            Rs. {this.state.rupees} = &euro;{this.state.euro}
          </p>
        )}
      </div>
    );
  }
}

export default CurrencyConvertor;
