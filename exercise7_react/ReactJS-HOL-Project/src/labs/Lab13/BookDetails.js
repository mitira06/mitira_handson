import './bloggerapp.css';

// BookDetails demonstrates:
// - Rendering a list with .map()
// - Using a unique "key" prop for each list item (book.id)
// - Conditional rendering with the && operator (Premium badge)
// - Preventing a component from rendering at all (returns null if no books)
function BookDetails(props) {
  // Preventing component from rendering: if there is no data, render nothing
  if (!props.books || props.books.length === 0) {
    return null;
  }

  const bookdet = (
    <ul>
      {props.books.map((book) => (
        <div key={book.id}>
          <h3>
            {book.bname}
            {/* Inline conditional rendering using the && operator */}
            {book.price > 700 && <span className="badge badge-premium">Premium</span>}
          </h3>
          <h4>{book.price}</h4>
        </div>
      ))}
    </ul>
  );

  return (
    <div className="st2">
      <h1> Book Details</h1>
      {bookdet}
    </div>
  );
}

export default BookDetails;
