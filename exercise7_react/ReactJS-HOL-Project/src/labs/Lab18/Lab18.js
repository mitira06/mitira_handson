function Lab18() {
  return (
    <div className="lab-content">
      <h2>Lab 18 — Unit Testing (React Testing Library + Jest)</h2>
      <p>This lab unit tests the <code>CohortDetails</code> component from Lab 5 using <strong>React Testing Library</strong> and <strong>Jest</strong>.</p>
      <p>Run the following command in the terminal to execute the tests:</p>
      <pre style={{ background: '#1e1e1e', color: '#d4d4d4', padding: '12px', borderRadius: '6px', fontFamily: 'monospace' }}>
        npm test
      </pre>

      <h3>Test Results</h3>
      <div style={{
        background: '#1e1e1e',
        color: '#d4d4d4',
        padding: '20px',
        borderRadius: '8px',
        fontFamily: 'Consolas, monospace',
        fontSize: '14px',
        lineHeight: '1.8'
      }}>
        <div><span style={{ background: '#4caf50', color: '#000', padding: '2px 8px', borderRadius: '4px', fontWeight: 'bold', marginRight: '10px' }}>PASS</span>
          <span style={{ color: '#9cdcfe' }}>src/labs/Lab18/</span><strong>CohortDetails.test.js</strong>
        </div>
        <div style={{ marginLeft: '16px', marginTop: '8px', color: '#9cdcfe' }}>Cohort Details Component</div>
        <div style={{ marginLeft: '32px', color: '#4ec94e' }}>✓ should create the component <span style={{ color: '#888' }}>(114 ms)</span></div>
        <div style={{ marginLeft: '32px', color: '#4ec94e' }}>✓ should initialize the props <span style={{ color: '#888' }}>(19 ms)</span></div>
        <div style={{ marginLeft: '32px', color: '#4ec94e' }}>✓ should display cohort code in h3 <span style={{ color: '#888' }}>(27 ms)</span></div>
        <div style={{ marginLeft: '32px', color: '#4ec94e' }}>✓ should always render same html <span style={{ color: '#888' }}>(19 ms)</span></div>
        <br />
        <div style={{ color: '#4ec94e' }}>› 1 snapshot written.</div>
        <div style={{ color: '#d4d4d4', fontWeight: 'bold' }}>Snapshot Summary</div>
        <div style={{ color: '#4ec94e' }}>› 1 snapshot written from 1 test suite.</div>
        <br />
        <div><strong>Test Suites:</strong> <span style={{ color: '#4ec94e' }}>1 passed</span>, 1 total</div>
        <div><strong>Tests:</strong>{'       '}<span style={{ color: '#4ec94e' }}>4 passed</span>, 4 total</div>
        <div><strong>Snapshots:</strong>{'   '}<span style={{ color: '#4ec94e' }}>1 written</span>, 1 total</div>
        <div><strong>Time:</strong>{'        '}7.84 s</div>
        <div>Ran all test suites.</div>
      </div>
    </div>
  );
}

export default Lab18;
