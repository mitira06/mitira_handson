import GuestPage from './GuestPage';
import UserPage from './UserPage';

// Element variable: pick which page to render based on the isLoggedIn prop,
// then return that single element. This is the core of conditional
// rendering -- only one of the two pages is ever rendered/mounted.
function Greeting(props) {
  const isLoggedIn = props.isLoggedIn;
  if (isLoggedIn) {
    return <UserPage />;
  }
  return <GuestPage />;
}

export default Greeting;
