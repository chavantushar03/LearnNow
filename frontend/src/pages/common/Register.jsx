import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { FaEye, FaEyeSlash } from "react-icons/fa";
import { toast } from 'react-toastify';
import { insertData } from '../../services/userServices';
 
function Register() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: '',
        dob: '',
        userRole: 'STUDENT',
    });
    const[error, setError] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

     const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        const { firstName, lastName, email, password, dob, userRole, confirmPassword} = formData;

        if (!firstName || !lastName || !email || !password || !dob || !userRole || !confirmPassword) {
            toast.error("All fields are required");
        } else if (!/^\S+@\S+\.\S+$/.test(email)) {
            toast.error("Enter a valid email address");
        } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,}$/.test(password)) {
            toast.error("Password must be at least 6 characters long and include uppercase, lowercase, number, and special character");
        } else if (password !== confirmPassword) {
            toast.error("Passwords do not match");
        } else {
            const response = await insertData(firstName, lastName, email, password, dob, userRole);
            if (response.status === "success") {
                toast.success("Registration done successfully!");
                console.log(response);
                navigate("/login");
            } else {
                toast.error("Unable to register");
            }
        }
    };

  return (
    <div className="p-6 max-w-md mx-auto">
      <h2 className="text-2xl font-bold mb-4">Register</h2>
      
      <form onSubmit={handleSubmit} className="space-y-3">
        <input type="text" name="firstName" placeholder="First Name" value={formData.firstName} onChange={handleChange} className="w-full border p-2" required />
        <input type="text" name="lastName" placeholder="Last Name" value={formData.lastName} onChange={handleChange} className="w-full border p-2" required />
        <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} className="w-full border p-2" required />


         <div className="mb-4">
            <label className="form-label">Password</label>
            <div className="input-group">
              <input
              name="password" 
                type={showPassword ? "text" : "password"}
                className="form-control"
                placeholder="Enter password"
                value={formData.password}
                onChange={handleChange}
                required
              />
              <button
                type="button"
                className="btn btn-outline-secondary"
                onClick={() =>
                  setShowPassword(!showPassword)
                }
                style={{
                  borderRadius: "0 var(--radius-sm) var(--radius-sm) 0",
                  backgroundColor: "transparent",
                  color: "var(--color-primary)",
                }}
                tabIndex={-1}
              >
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </button>
            </div>
          </div>
      <div className="mb-4">
            <label className="form-label">Confirm Password</label>
            <div className="input-group">
              <input
              name="confirmPassword" 
                type={showConfirmPassword ? "text" : "password"}
                className="form-control"
                placeholder="Confirm password"
                value={formData.confirmPassword}
                onChange={handleChange}
                required
              />
              <button
                type="button"
                className="btn btn-outline-secondary"
                onClick={() =>
                  setShowConfirmPassword(!showConfirmPassword)
                }
                style={{
                  borderRadius: "0 var(--radius-sm) var(--radius-sm) 0",
                  backgroundColor: "transparent",
                  color: "var(--color-primary)",
                }}
                tabIndex={-1}
              >
                {showConfirmPassword ? <FaEyeSlash /> : <FaEye />}
              </button>
            </div>
          </div>
        <input type="date" name="dob" value={formData.dob} onChange={handleChange} className="w-full border p-2" required />
        <select name="userRole" value={formData.userRole} onChange={handleChange} className="w-full border p-2">
          <option value="STUDENT">Student</option>
          <option value="TEACHER">Teacher</option>
        </select>
        <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded">Register</button>
        
        {error && <p className="text-red-500">{error}</p>}
      </form>
      <br></br>
        <button className="bg-green-600 text-white px-4 py-2 rounded"><Link to="/login" className="text-white">Login</Link></button>
      
    </div>
  )
}

export default Register