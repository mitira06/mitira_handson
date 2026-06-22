function ListofIndianPlayers({ IndianPlayers }) {
  return (
    <ul>
      {IndianPlayers.map((item) => (
        <li key={item}>Mr. {item}</li>
      ))}
    </ul>
  );
}

export default ListofIndianPlayers;
