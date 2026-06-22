import { Outlet, Link } from 'react-router-dom';

function Lab6() {
  return (
    <div className="lab-content">
      <h1>My Academy Trainers App</h1>
      <nav className="lab6-nav">
        <Link to="/lab6">Home</Link> | <Link to="/lab6/trainers">Show Trainers</Link>
      </nav>
      <Outlet />
    </div>
  );
}

export default Lab6;
