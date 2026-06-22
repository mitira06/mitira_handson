import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';
import { books } from './booksdata';
import './bloggerapp.css';

// Top-level bloggerapp component. Renders the three components
// (Book Details, Blog Details, Course Details) inside one wrapper div,
// matching the structure shown in the lab hint.
function BloggerApp() {
  return (
    <div className="blogger-app">
      <BookDetails books={books} />
      <BlogDetails />
      <CourseDetails />
    </div>
  );
}

export default BloggerApp;
