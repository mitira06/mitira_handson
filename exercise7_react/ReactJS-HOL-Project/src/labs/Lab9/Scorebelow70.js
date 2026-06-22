function Scorebelow70({ players }) {
  const players70 = [];
  players.map((item) => {
    if (item.score <= 70) {
      players70.push(item);
    }
    return null;
  });

  return (
    <ul>
      {players70.map((item) => {
        return (
          <li key={item.name}>
            Mr. {item.name} <span> {item.score} </span>
          </li>
        );
      })}
    </ul>
  );
}

export default Scorebelow70;
