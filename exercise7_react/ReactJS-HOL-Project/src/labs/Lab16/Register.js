import { useState } from 'react';
import './mailregisterapp.css';

// Register component: a controlled form (name, email, password) validated
// on every keystroke (event handle, via handleChange) and re-checked on
// submit (event submit, via handleSubmit), exactly as the lab asks.
//
// Rules:
// - Name must be at least 5 characters
// - Email must contain "@" and "."
// - Password must be at least 8 characters
function Register() {
  const [values, setValues] = useState({ fullName: '', email: '', password: '' });

  // Errors start populated, since an untouched/empty form is not valid yet.
  const [errors, setErrors] = useState({
    fullName: 'Full Name must be 5 characters long!',
    email: 'Email is not valid!',
    password: 'Password must be 8 characters long!',
  });

  // Runs on every input's onChange -- validates just the field that changed.
  const handleChange = (event) => {
    const { name, value } = event.target;
    setValues({ ...values, [name]: value });

    const newErrors = { ...errors };
    switch (name) {
      case 'fullName':
        newErrors.fullName = value.length < 5 ? 'Full Name must be 5 characters long!' : '';
        break;
      case 'email': {
        // Validates that the email contains "@" and "." in the expected places
        const validEmailRegex = RegExp(
          /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@(([^<>()[\]\\.,;:\s@"]+\.)+[^<>()[\]\\.,;:\s@"]{2,})$/i
        );
        newErrors.email = validEmailRegex.test(value) ? '' : 'Email is not valid!';
        break;
      }
      case 'password':
        newErrors.password = value.length < 8 ? 'Password must be 8 characters long!' : '';
        break;
      default:
        break;
    }
    setErrors(newErrors);
  };

  // A form is valid only when every field's error message is empty
  const validateForm = (errs) => {
    let valid = true;
    Object.values(errs).forEach((val) => val.length > 0 && (valid = false));
    return valid;
  };

  // Runs when the form is submitted -- re-checks everything before allowing it through
  const handleSubmit = (event) => {
    event.preventDefault();
    if (validateForm(errors)) {
      alert('Valid Form');
    } else {
      if (errors.fullName !== '') {
        alert(errors.fullName);
      }
      if (errors.email !== '') {
        alert(errors.email);
      }
      if (errors.password !== '') {
        alert(errors.password);
      }
    }
  };

  return (
    <div>
      <h1 className="register-heading">Register Here!!!</h1>
      <form onSubmit={handleSubmit} noValidate>
        <div className="register-form-row">
          <label htmlFor="fullName">Name:</label>
          <input
            type="text"
            id="fullName"
            name="fullName"
            value={values.fullName}
            onChange={handleChange}
          />
        </div>
        <div className="register-form-row">
          <label htmlFor="email">Email:</label>
          <input
            type="text"
            id="email"
            name="email"
            value={values.email}
            onChange={handleChange}
          />
        </div>
        <div className="register-form-row">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={values.password}
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="register-submit-btn">
          Submit
        </button>
      </form>
    </div>
  );
}

export default Register;
