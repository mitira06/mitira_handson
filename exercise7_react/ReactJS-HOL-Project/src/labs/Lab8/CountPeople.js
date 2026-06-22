import React from 'react';
import './counterapp.css';

class CountPeople extends React.Component {
  constructor() {
    super();
    this.state = {
      entrycount: 0,
      exitcount: 0,
      c: 0,
    };
    this.updateEntry = this.updateEntry.bind(this);
    this.updateExit = this.updateExit.bind(this);
  }

  updateEntry() {
    this.setState((prevState, props) => {
      return { entrycount: prevState.entrycount + 1 };
    });
  }

  updateExit() {
    this.setState((prevState, props) => {
      return { exitcount: prevState.exitcount + 1 };
    });
  }

  render() {
    return (
      <div className="counter-row">
        <span className="counter-block">
          <button className="counter-btn" onClick={this.updateEntry}>
            Login
          </button>
          {' '}
          {this.state.entrycount} People Entered!!!
        </span>
        <span className="counter-block">
          <button className="counter-btn" onClick={this.updateExit}>
            Exit
          </button>
          {' '}
          {this.state.exitcount} People Left!!!
        </span>
      </div>
    );
  }
}

export default CountPeople;
