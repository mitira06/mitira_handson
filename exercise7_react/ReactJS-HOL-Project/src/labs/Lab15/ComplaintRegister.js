import React from 'react';
import './ticketraisingapp.css';

// ComplaintRegister: a controlled form with a textbox (employee name) and
// a textarea (complaint). Submitting raises a reference number for
// follow-ups, shown in an alert box.
class ComplaintRegister extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      ename: '',
      ecomplaint: '',
      // Reference number generated up-front so it is ready to quote
      // back to the employee at submit time.
      NumberHolder: Math.floor(Math.random() * 100) + 1,
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  // Single handler for every input: uses the input's "name" attribute as
  // the state key to update, so one function can drive both controls.
  handleChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  // Raises the complaint: builds a thank-you message containing the
  // employee's name and the reference number, then shows it in an alert.
  handleSubmit(event) {
    var msg =
      'Thanks ' +
      this.state.ename +
      ' \n Your Complaint was Submitted. \n Transaction ID is: ' +
      this.state.NumberHolder;
    alert(msg);
    event.preventDefault();
  }

  render() {
    return (
      <div>
        <h1 className="complaint-heading">Register your complaints here!!!</h1>
        <form onSubmit={this.handleSubmit}>
          <div className="complaint-form-row">
            <label htmlFor="ename">Name:</label>
            <input
              type="text"
              id="ename"
              name="ename"
              value={this.state.ename}
              onChange={this.handleChange}
            />
          </div>
          <div className="complaint-form-row">
            <label htmlFor="ecomplaint">Complaint:</label>
            <textarea
              id="ecomplaint"
              name="ecomplaint"
              value={this.state.ecomplaint}
              onChange={this.handleChange}
            />
          </div>
          <button type="submit" className="complaint-submit-btn">
            Submit
          </button>
        </form>
      </div>
    );
  }
}

export default ComplaintRegister;
