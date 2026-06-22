import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import Lab1 from './labs/Lab1/Lab1';
import Lab2 from './labs/Lab2/Lab2';
import Lab3 from './labs/Lab3/Lab3';
import Lab4 from './labs/Lab4/Lab4';
import Lab5 from './labs/Lab5/Lab5';
import Lab6 from './labs/Lab6/Lab6';
import Lab6Home from './labs/Lab6/Home';
import TrainersList from './labs/Lab6/TrainersList';
import TrainerDetail from './labs/Lab6/TrainerDetail';
import trainersMock from './labs/Lab6/trainersmock';
import Lab7 from './labs/Lab7/Lab7';
import Lab8 from './labs/Lab8/Lab8';
import Lab9 from './labs/Lab9/Lab9';
import Lab10 from './labs/Lab10/Lab10';
import Lab11 from './labs/Lab11/Lab11';
import Lab12 from './labs/Lab12/Lab12';
import Lab13 from './labs/Lab13/Lab13';
import Lab14 from './labs/Lab14/Lab14';
import Lab15 from './labs/Lab15/Lab15';
import Lab16 from './labs/Lab16/Lab16';
import Lab17 from './labs/Lab17/Lab17';
import Lab18 from './labs/Lab18/Lab18';
import Lab19 from './labs/Lab19/Lab19';

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Home</Link>
        <Link to="/lab1">Lab 1</Link>
        <Link to="/lab2">Lab 2</Link>
        <Link to="/lab3">Lab 3</Link>
        <Link to="/lab4">Lab 4</Link>
        <Link to="/lab5">Lab 5</Link>
        <Link to="/lab6">Lab 6</Link>
        <Link to="/lab7">Lab 7</Link>
        <Link to="/lab8">Lab 8</Link>
        <Link to="/lab9">Lab 9</Link>
        <Link to="/lab10">Lab 10</Link>
        <Link to="/lab11">Lab 11</Link>
        <Link to="/lab12">Lab 12</Link>
        <Link to="/lab13">Lab 13</Link>
        <Link to="/lab14">Lab 14</Link>
        <Link to="/lab15">Lab 15</Link>
        <Link to="/lab16">Lab 16</Link>
        <Link to="/lab17">Lab 17</Link>
        <Link to="/lab19">Lab 19</Link>
      </nav>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/lab1" element={<Lab1 />} />
        <Route path="/lab2" element={<Lab2 />} />
        <Route path="/lab3" element={<Lab3 />} />
        <Route path="/lab4" element={<Lab4 />} />
        <Route path="/lab5" element={<Lab5 />} />
        <Route path="/lab6" element={<Lab6 />}>
          <Route index element={<Lab6Home />} />
          <Route path="trainers" element={<TrainersList trainers={trainersMock} />} />
          <Route path="trainers/:id" element={<TrainerDetail />} />
        </Route>
        <Route path="/lab7" element={<Lab7 />} />
        <Route path="/lab8" element={<Lab8 />} />
        <Route path="/lab9" element={<Lab9 />} />
        <Route path="/lab10" element={<Lab10 />} />
        <Route path="/lab11" element={<Lab11 />} />
        <Route path="/lab12" element={<Lab12 />} />
        <Route path="/lab13" element={<Lab13 />} />
        <Route path="/lab14" element={<Lab14 />} />
        <Route path="/lab15" element={<Lab15 />} />
        <Route path="/lab16" element={<Lab16 />} />
        <Route path="/lab17" element={<Lab17 />} />
        <Route path="/lab18" element={<Lab18 />} />
        <Route path="/lab19" element={<Lab19 />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
