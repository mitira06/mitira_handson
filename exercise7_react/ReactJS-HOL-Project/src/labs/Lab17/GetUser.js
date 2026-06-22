import React from 'react';
import './fetchuserapp.css';

// GetUser fetches a random user from the Random User API inside the
// asynchronous componentDidMount() lifecycle method, then displays the
// person's title, first name (and last name) plus their photo in render().
class GetUser extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      person: null,
      loading: true,
    };
  }

  async componentDidMount() {
    const url = 'https://api.randomuser.me/';
    const response = await fetch(url);
    const data = await response.json();
    this.setState({ person: data.results[0], loading: false });
    console.log(data.results[0]);
  }

  render() {
    const { person, loading } = this.state;

    // Prevents rendering the person's details before the fetch resolves
    if (loading) {
      return <p className="fetchuser-loading">Loading user...</p>;
    }

    return (
      <div className="fetchuser-app">
        <h1>
          {person.name.title} {person.name.first} {person.name.last}
        </h1>
        <img src={person.picture.large} alt={person.name.first} />
      </div>
    );
  }
}

export default GetUser;
