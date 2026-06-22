import './officespace.css';

function rentColorClass(rent) {
  // Per the lab hint: red if Rent <= 60000, green if Rent > 60000
  return rent <= 60000 ? 'textRed' : 'textGreen';
}

function Lab10() {
  // Element created with JSX
  const element = 'Office Space';

  // Attribute created with JSX (an <img> tag with src/width/alt attributes)
  const imgSrc = 'https://picsum.photos/seed/officespace/400/240';
  const jsxatt = <img src={imgSrc} width="40%" alt="Office Space" />;

  // Object holding a single office's details
  const ItemName = { Name: 'DBS', Rent: 50000, Address: 'Chennai' };

  // List of office objects to loop through
  const moreOffices = [
    { Name: 'Spaces Inc.', Rent: 45000, Address: 'Bangalore' },
    { Name: 'WeWork', Rent: 75000, Address: 'Mumbai' },
    { Name: 'Regus', Rent: 60000, Address: 'Hyderabad' },
    { Name: 'Smartworks', Rent: 95000, Address: 'Pune' },
  ];

  return (
    <div className="lab-content office-space">
      <h1>{element} , at Affordable Range</h1>
      {jsxatt}
      <h1>Name: {ItemName.Name}</h1>
      <h3 className={rentColorClass(ItemName.Rent)}>Rent: Rs. {ItemName.Rent}</h3>
      <h3>Address: {ItemName.Address}</h3>

      <hr />
      <h2>More Office Spaces</h2>
      <div className="office-list">
        {moreOffices.map((office) => (
          <div className="office-card" key={office.Name}>
            <h3>Name: {office.Name}</h3>
            <h4 className={rentColorClass(office.Rent)}>Rent: Rs. {office.Rent}</h4>
            <p>Address: {office.Address}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Lab10;
