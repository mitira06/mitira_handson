import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div className="lab-list">
      <h2>ReactJS Hands-On Labs</h2>
      <ul>
        <li><Link to="/lab1">Lab 1 — Create React App (myfirstreact)</Link></li>
        <li><Link to="/lab2">Lab 2 — Components (StudentApp)</Link></li>
        <li><Link to="/lab3">Lab 3 — Function Component + Styling (scorecalculatorapp)</Link></li>
        <li><Link to="/lab4">Lab 4 — Lifecycle Hooks (blogapp)</Link></li>
        <li><Link to="/lab5">Lab 5 — Styling with CSS Modules (cohorttracker)</Link></li>
        <li><Link to="/lab6">Lab 6 — React Router (TrainersApp)</Link></li>
        <li><Link to="/lab7">Lab 7 — Props (shoppingapp)</Link></li>
        <li><Link to="/lab8">Lab 8 — State (counterapp)</Link></li>
        <li><Link to="/lab9">Lab 9 — ES6 Features (cricketapp)</Link></li>
        <li><Link to="/lab10">Lab 10 — JSX (officespacerentalapp)</Link></li>
        <li><Link to="/lab11">Lab 11 — Event Handling (eventexamplesapp)</Link></li>
        <li><Link to="/lab12">Lab 12 — Conditional Rendering (ticketbookingapp)</Link></li>
        <li><Link to="/lab13">Lab 13 — Lists, Keys & Conditional Rendering (bloggerapp)</Link></li>
        <li><Link to="/lab14">Lab 14 — Context API (employeesapp)</Link></li>
        <li><Link to="/lab15">Lab 15 — Forms (ticketraisingapp)</Link></li>
        <li><Link to="/lab16">Lab 16 — Form Validation (mailregisterapp)</Link></li>
        <li><Link to="/lab17">Lab 17 — Consuming REST APIs (fetchuserapp)</Link></li>
        <li><Link to="/lab18">Lab 18 — Unit Testing with Jest</Link></li>
        <li><Link to="/lab19">Lab 19 — Mocking with Jest (gitclientapp)</Link></li>
      </ul>
    </div>
  );
}

export default Home;
