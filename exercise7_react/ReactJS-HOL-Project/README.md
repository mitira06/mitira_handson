# ReactJS-HOL-Project

A single combined React project for all ReactJS Hands-On Labs. Each lab
lives in its own folder under `src/labs/` and is reachable as its own
route, so you can verify each lab's output independently without
managing 19 separate projects.

## How to run

1. Make sure Node.js and npm are installed: https://nodejs.org/en/download/
2. Unzip this project and open the folder in a terminal (or in Visual Studio Code).
3. Install dependencies (only needs to be done once, for the whole project):

   npm install

4. Start the app:

   npm start

5. Your browser should open automatically to http://localhost:3000.
   Use the nav bar at the top to jump to any lab, or go directly to:
   - http://localhost:3000/lab1
   - http://localhost:3000/lab2
   - http://localhost:3000/lab3
   - http://localhost:3000/lab4
   - http://localhost:3000/lab5
   - http://localhost:3000/lab6
   - http://localhost:3000/lab7
   - http://localhost:3000/lab8
   - http://localhost:3000/lab9
   - http://localhost:3000/lab10
   - http://localhost:3000/lab11
   - http://localhost:3000/lab12
   - http://localhost:3000/lab13
   - http://localhost:3000/lab14
   - http://localhost:3000/lab15
   - http://localhost:3000/lab16
   - http://localhost:3000/lab17
   - (more labs will be added here as routes, e.g. /lab18, /lab19, ...)

## Labs included so far

| Route   | Lab                          | Description                                  |
|---------|-------------------------------|-----------------------------------------------|
| /lab1   | ReactJS-HOL 1 (myfirstreact)  | Basic CRA setup, prints a welcome heading      |
| /lab2   | ReactJS-HOL 2 (StudentApp)    | Home/About/Contact components rendered together |
| /lab3   | ReactJS-HOL 3 (scorecalculatorapp) | Function component with props + CSS styling, computes and displays a score |
| /lab4   | ReactJS-HOL 4 (blogapp)       | Class component lifecycle hooks: componentDidMount fetches posts from a public API, componentDidCatch handles errors. Requires internet access in your browser to load posts. |
| /lab5   | ReactJS-HOL 5 (cohorttracker) | Styling with a CSS Module (.module.css) for a "box" card layout, plus inline styles for conditional h3 color (green for Ongoing, blue otherwise) |
| /lab6   | ReactJS-HOL 6 (TrainersApp)   | React Router: Home and Trainers List pages, clickable trainer names link to a Trainer Detail page using useParams to read the :id from the URL |
| /lab7   | ReactJS-HOL 7 (shoppingapp)   | Props: OnlineShopping class component passes an array of Cart items as props to a Cart class component, which renders them as a table |
| /lab8   | ReactJS-HOL 8 (counterapp)    | State: CountPeople class component with Login/Exit buttons that increment entrycount/exitcount in state via setState |
| /lab9   | ReactJS-HOL 9 (cricketapp)    | ES6 features: map() to list players, arrow function to filter scores <=70, array destructuring for Odd/Even players, spread operator to merge T20/Ranji arrays. A toggle button switches between the flag=true and flag=false views. |
| /lab10  | ReactJS-HOL 10 (officespacerentalapp) | JSX: a JSX element/attribute/object, plus a looped list of more office objects, with rent text colored red (<=60000) or green (>60000). Requires internet access in your browser to load the placeholder office image. |
| /lab11  | ReactJS-HOL 11 (eventexamplesapp) | Event handling: Increment/Decrement counter buttons (Increment also says hello via a second method call), a "Say Welcome" button invoking a handler with an argument, a button using a React Synthetic Event to show "I was clicked", and a CurrencyConvertor component that converts Rupees to Euro on a Convert button click |
| /lab12  | ReactJS-HOL 12 (ticketbookingapp) | Conditional rendering: a Greeting element variable renders either GuestPage (browse flight details only) or UserPage (book tickets) based on isLoggedIn state, toggled by Login/Logout buttons. BookingNotice demonstrates preventing a component from rendering (returns null until a flight is booked) |
| /lab13  | ReactJS-HOL 13 (bloggerapp) | Lists & keys: BookDetails, BlogDetails and CourseDetails each render a list with .map() and a unique key per item. Demonstrates several ways of conditional rendering: the && operator (Premium badge), the ternary operator (New badge), an if/else element variable (Trending badge), and preventing a component from rendering (BookDetails returns null with no data) |
| /lab14  | ReactJS-HOL 14 (employeesapp) | React Context API: ThemeContext is created with createContext('light') and provided from EmployeesApp's state via ThemeContext.Provider. EmployeesList no longer passes the theme down as a prop -- EmployeeCard reads it directly with useContext(ThemeContext) to set its button className (btn-light / btn-dark). A toggle button switches the theme live |
| /lab15  | ReactJS-HOL 15 (ticketraisingapp) | Forms: ComplaintRegister is a controlled component with a Name textbox and a Complaint textarea, both driven by a single handleChange(event) that uses event.target.name as the state key. handleSubmit(event) builds a thank-you message with a generated reference number and shows it in an alert, then calls event.preventDefault() |
| /lab16  | ReactJS-HOL 16 (mailregisterapp) | Form validation: Register validates Name (>=5 chars), Email (via regex requiring @ and .) and Password (>=8 chars) on every keystroke (handleChange) using a switch(name) statement, and re-checks via validateForm(errors) on submit (handleSubmit), alerting whichever field(s) are still invalid, or "Valid Form" if everything passes |
| /lab17  | ReactJS-HOL 17 (fetchuserapp) | Consuming REST APIs: GetUser fetches https://api.randomuser.me/ inside an async componentDidMount(), stores the first result in state with a loading flag, and renders the person's title/first/last name and photo once loaded. Requires internet access in your browser to reach the Random User API |

As new lab docx files are provided, they'll be added here as new folders
under `src/labs/` and new routes/nav links in `src/App.js`.
