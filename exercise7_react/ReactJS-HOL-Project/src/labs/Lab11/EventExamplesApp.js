import React from 'react';
import './eventexamplesapp.css';

// EventExamplesApp covers:
// - Increment / Decrement buttons that update counter state
// - The Increment button invokes TWO methods on a single click: it updates
//   the counter AND says hello with a static message
// - A "Say Welcome" button that invokes a handler with an argument ("welcome")
// - A button that uses React's Synthetic Event ("OnPress" style handler)
//   to display "I was clicked"
class EventExamplesApp extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      counter: 0,
      helloMessage: '',
      welcomeMessage: '',
      clickMessage: '',
    };

    // Binding "this" so it refers to the component instance inside the handlers
    this.handleIncrement = this.handleIncrement.bind(this);
    this.handleDecrement = this.handleDecrement.bind(this);
    this.sayHello = this.sayHello.bind(this);
    this.sayWelcome = this.sayWelcome.bind(this);
    this.handleOnPress = this.handleOnPress.bind(this);
  }

  // Increments the counter value
  handleIncrement() {
    this.setState((prevState) => ({ counter: prevState.counter + 1 }));
    // The Increment button invokes this second method as well
    this.sayHello();
  }

  // Decrements the counter value
  handleDecrement() {
    this.setState((prevState) => ({ counter: prevState.counter - 1 }));
  }

  // Says hello with a static message
  sayHello() {
    this.setState({ helloMessage: 'Hello! Counter value updated.' });
  }

  // Invoked by the "Say Welcome" button, takes "welcome" as an argument
  sayWelcome(name) {
    this.setState({ welcomeMessage: `Say ${name}!` });
  }

  // React Synthetic Event handler: every browser event React hands to a
  // handler (here, "event") is a SyntheticEvent that wraps the native
  // browser event with a consistent, cross-browser API.
  handleOnPress(event) {
    // event is the React SyntheticEvent, e.g. event.type === 'click'
    this.setState({ clickMessage: 'I was clicked' });
  }

  render() {
    return (
      <div className="lab-content">
        <h2>Event Examples App</h2>

        <section className="event-section">
          <h3>Increment / Decrement Counter</h3>
          <button className="event-btn" onClick={this.handleIncrement}>
            Increment
          </button>
          <button className="event-btn" onClick={this.handleDecrement}>
            Decrement
          </button>
          <span className="counter-value">Count: {this.state.counter}</span>
          {this.state.helloMessage && (
            <p className="event-message">{this.state.helloMessage}</p>
          )}
        </section>

        <section className="event-section">
          <h3>Say Welcome</h3>
          <button
            className="event-btn"
            onClick={() => this.sayWelcome('welcome')}
          >
            Say Welcome
          </button>
          {this.state.welcomeMessage && (
            <p className="event-message">{this.state.welcomeMessage}</p>
          )}
        </section>

        <section className="event-section">
          <h3>Synthetic Event (OnPress)</h3>
          <button className="event-btn" onClick={this.handleOnPress}>
            Press Me
          </button>
          {this.state.clickMessage && (
            <p className="event-message">{this.state.clickMessage}</p>
          )}
        </section>
      </div>
    );
  }
}

export default EventExamplesApp;
