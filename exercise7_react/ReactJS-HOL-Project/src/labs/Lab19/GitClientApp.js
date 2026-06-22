import { useEffect, useState } from 'react';
import GitClient from './GitClient';
import './gitclient.css';

function GitClientApp() {
  const [repositories, setRepositories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    GitClient.getRepositories('techiesyed')
      .then(r => {
        setRepositories(r.data);
        setLoading(false);
      })
      .catch(err => {
        setError('Failed to fetch repositories. Check your internet connection.');
        setLoading(false);
      });
  }, []);

  return (
    <div className="gitclient-container">
      <h1>Git repositories of User - TechieSyed</h1>
      {loading && <p>Loading repositories...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}
      {repositories.map(r => (
        <p key={r.name}>{r.name}</p>
      ))}
    </div>
  );
}

export default GitClientApp;
