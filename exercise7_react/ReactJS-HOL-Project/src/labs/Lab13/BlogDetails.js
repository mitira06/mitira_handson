import './bloggerapp.css';

// Blog post data: rendered as a list, just like BookDetails
const posts = [
  { id: 1, title: 'React Learning', author: 'Stephen Biz', content: 'Welcome to learning React!', isNew: true },
  { id: 2, title: 'Installation', author: 'Schewzdenier', content: 'You can install React from npm.', isNew: false },
];

// BlogDetails demonstrates:
// - Rendering multiple components/items with .map()
// - Using a unique "key" prop for each post (post.id)
// - Conditional rendering with the ternary (conditional) operator for the "New" badge
function BlogDetails() {
  const content = (
    <div>
      {posts.map((post) => (
        <div key={post.id}>
          <h1>
            {post.title}
            {/* Inline if-else using the ternary operator */}
            {post.isNew ? <span className="badge badge-new">New</span> : null}
          </h1>
          <p className="blog-author">{post.author}</p>
          <p className="blog-content">{post.content}</p>
        </div>
      ))}
    </div>
  );

  return (
    <div className="v1">
      <h1> Blog Details</h1>
      {content}
    </div>
  );
}

export default BlogDetails;
