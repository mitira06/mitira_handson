import './bloggerapp.css';

// Course data, kept exactly as given in the hand-on lab hint (including the
// original date value for "React").
const courses = [
  { id: 1, cname: 'Angular', date: '4/5/2021' },
  { id: 2, cname: 'React', date: '6/3/20201' },
];

// Returns an element variable: a badge element decided with a plain
// if / else statement (one of the "many ways" of conditional rendering).
function getBadge(course) {
  let badge;
  if (course.cname === 'React') {
    badge = <span className="badge badge-new">Trending</span>;
  } else {
    badge = null;
  }
  return badge;
}

// CourseDetails demonstrates:
// - Rendering multiple components/items with .map()
// - Using a unique "key" prop for each course (course.id)
// - Conditional rendering via an if/else statement that builds an element variable
function CourseDetails() {
  const coursedet = (
    <div>
      {courses.map((course) => {
        const badge = getBadge(course);
        return (
          <div key={course.id}>
            <h3>
              {course.cname}
              {badge}
            </h3>
            <h4>{course.date}</h4>
          </div>
        );
      })}
    </div>
  );

  return (
    <div className="mystyle1">
      <h1> Course Details</h1>
      {coursedet}
    </div>
  );
}

export default CourseDetails;
