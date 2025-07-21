import { Route, Routes } from 'react-router-dom';
import Home from './pages/student/Home';
import CoursesList from './pages/student/CoursesList';
import CourseDetails from './pages/student/CourseDetails';
import MyEnrollments from './pages/student/MyEnrollments';
import Player from './pages/student/Player';
import Loading from './components/student/Loading';
import Teacher from './pages/teacher/Teacher';
import Register from './pages/common/Register';
import Dashboard from './pages/teacher/Dashboard';
import AddCourse from './pages/teacher/AddCourse';
import MyCourses from './pages/teacher/MyCourses';
import StudentsEnrolled from './pages/teacher/StudentsEnrolled';
import Navbar from './components/common/Navbar';
import Footer from './components/common/Footer';
import "quill/dist/quill.snow.css"
import { ToastContainer } from 'react-toastify';
import Login from './pages/common/Login';
import Admin from './pages/admin/Admin';
import PrivateRoute from './routes/PrivateRoute';
import RoleBasedRoute from './routes/RoleBasedRoute';

const App = () => {
  return (
    <div className="min-h-screen bg-[var(--color-bg)] text-[var(--color-text)]">
      <ToastContainer />
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/course-list" element={<CoursesList />} />
        <Route path="/course-list/:input" element={<CoursesList />} />
        <Route path="/course/:id" element={<CourseDetails />} />

        <Route path="/my-enrollments" element={
          <PrivateRoute><MyEnrollments /></PrivateRoute>
        } />
        <Route path="/player/:courseId" element={
          <PrivateRoute><Player /></PrivateRoute>
        } />
        <Route path="/loading/:path" element={<Loading />} />

        <Route path="/teacher" element={
          <RoleBasedRoute allowedRoles={['TEACHER']}><Teacher /></RoleBasedRoute>
        }>
          <Route index element={<Dashboard />} />
          <Route path="add-course" element={<AddCourse />} />
          <Route path="my-courses" element={<MyCourses />} />
          <Route path="student-enrolled" element={<StudentsEnrolled />} />
        </Route>

        <Route path="/admin" element={
          <RoleBasedRoute allowedRoles={['ADMIN']}><Admin /></RoleBasedRoute>
        } />
      </Routes>

      <Footer />
    </div>
  )
}

export default App;
