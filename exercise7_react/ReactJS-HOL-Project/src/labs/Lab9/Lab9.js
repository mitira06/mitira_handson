import { useState } from 'react';
import ListofPlayers from './ListofPlayers';
import Scorebelow70 from './Scorebelow70';
import { OddPlayers, EvenPlayers } from './IndianPlayers';
import ListofIndianPlayers from './ListofIndianPlayers';

const players = [
  { name: 'Jack', score: 50 },
  { name: 'Michael', score: 70 },
  { name: 'John', score: 40 },
  { name: 'Ann', score: 61 },
  { name: 'Elisabeth', score: 61 },
  { name: 'Sachin', score: 95 },
  { name: 'Dhoni', score: 100 },
  { name: 'Virat', score: 84 },
  { name: 'Jadeja', score: 64 },
  { name: 'Raina', score: 75 },
  { name: 'Rohit', score: 80 },
];

const IndianTeam = ['Sachin1', 'Dhoni2', 'Virat3', 'Rohit4', 'Yuvaraj5', 'Raina6'];

const T20Players = ['First Player', 'Second Player', 'Third Player'];
const RanjiTrophyPlayers = ['Fourth Player', 'Fifth Player', 'Sixth Player'];
const IndianPlayers = [...T20Players, ...RanjiTrophyPlayers];

function Lab9() {
  // The lab's hint uses a hardcoded `var flag = true / false`. To let you see
  // both outputs without editing code, this is wired to a toggle button —
  // the conditional rendering logic itself is the same simple if/else.
  const [flag, setFlag] = useState(true);

  return (
    <div className="lab-content">
      <button onClick={() => setFlag(!flag)} style={{ marginBottom: '20px' }}>
        Toggle Flag (currently {flag ? 'true' : 'false'})
      </button>

      {flag ? (
        <div>
          <h1> List of Players</h1>
          <ListofPlayers players={players} />
          <hr />
          <h1> List of Players having Scores Less than 70 </h1>
          <Scorebelow70 players={players} />
        </div>
      ) : (
        <div>
          <div>
            <h1> Indian Team </h1>
            <h1> Odd Players </h1>
            {OddPlayers(IndianTeam)}
            <hr />
            <h1>Even Players</h1>
            {EvenPlayers(IndianTeam)}
          </div>
          <hr />
          <div>
            <h1> List of Indian Players Merged:</h1>
            <ListofIndianPlayers IndianPlayers={IndianPlayers} />
          </div>
        </div>
      )}
    </div>
  );
}

export default Lab9;
