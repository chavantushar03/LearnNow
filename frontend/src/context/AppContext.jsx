import { createContext, useEffect, useState } from "react";
import { dummyCourses } from "../assets/assets";
import { useNavigate } from "react-router-dom";
import humanizeDuration from "humanize-duration";
<<<<<<< HEAD

// Create context
export const AppContext = createContext();

// Provider
export const AppContextProvider = (props) => {
  const backendUrl = import.meta.env.VITE_BACKEND_URL;
  const currency = import.meta.env.VITE_CURRENCY;
  const navigate = useNavigate();

  // Local states
  const [allCourses, setAllCourses] = useState([]);
  const [enrolledCourses, setEnrolledCourses] = useState([]);
  const [token, setToken] = useState(null);
const [user, setUser] = useState(() => {
  const storedUser = localStorage.getItem('user');
  return storedUser ? JSON.parse(storedUser) : null;
});

const isTeacher = user?.userRole?.toUpperCase() === 'TEACHER';



  // Derived states
  const isLoggedIn = !!user && !!token;
  const userRole = user?.userRole || null;

  // On initial mount, sync from localStorage
  useEffect(() => {
    const localUser = localStorage.getItem("user");
    const localToken = localStorage.getItem("token");

    if (localUser && localToken) {
      const parsedUser = JSON.parse(localUser);
      setUser(parsedUser);
      setToken(localToken);
    }
  }, []);

  // Login function
  const login = (userData, jwtToken) => {
    console.log("Logging in:", userData); // Debug

    localStorage.setItem("user", JSON.stringify(userData));
    localStorage.setItem("token", jwtToken);

    setUser(userData);
    setToken(jwtToken);

    // Redirect after login (optional)
    if (userData.userRole === "STUDENT") {
      navigate("/");
    } else if (userData.userRole === "TEACHER") {
      navigate("/teacher/dashboard");
    } else if (userData.userRole === "ADMIN") {
      navigate("/admin/dashboard");
    } else {
      navigate("/");
    }
  };

  // Logout function
  const logout = () => {
    console.log("Logging out"); // Debug
    localStorage.removeItem("user");
    localStorage.removeItem("token");
    setUser(null);
    setToken(null);
    navigate("/login");
  };

  // ======= COURSE HELPERS =======
  const fetchAllCourses = async () => {
    // Replace with real API call
    setAllCourses(dummyCourses);
  };

  const fetchUserEnrolledCourse = async () => {
    // Replace with real API call
    setEnrolledCourses(dummyCourses);
  };

  const calculateRating = (course) => {
    if (!course.courseRatings?.length) return 0;
    const total = course.courseRatings.reduce((sum, r) => sum + r.rating, 0);
    return total / course.courseRatings.length;
  };

  const calculateChapterTime = (chapter) => {
    const time = chapter.chapterContent.reduce((sum, lecture) => sum + lecture.lectureDuration, 0);
    return humanizeDuration(time * 60 * 1000, { units: ["h", "m"] });
  };

  const calculateCourseDuration = (course) => {
    let total = 0;
    course.courseContent.forEach((chapter) => {
      total += chapter.chapterContent.reduce((sum, lecture) => sum + lecture.lectureDuration, 0);
    });
    return humanizeDuration(total * 60 * 1000, { units: ["h", "m"] });
  };

  const calculateNoOfLectures = (course) => {
    return course.courseContent.reduce((total, chapter) => {
      return total + (Array.isArray(chapter.chapterContent) ? chapter.chapterContent.length : 0);
    }, 0);
  };

  // Fetch courses on mount or when user changes
  useEffect(() => {
    fetchAllCourses();
    if (user) fetchUserEnrolledCourse();
  }, [user]);
  


  // Global context value
  const value = {
    backendUrl,
    currency,
    navigate,
    allCourses,
    enrolledCourses,
    fetchUserEnrolledCourse,
    calculateRating,
    calculateChapterTime,
    calculateCourseDuration,
    calculateNoOfLectures,
    user,
    token,
    login,
    logout,
    isLoggedIn,
    userRole,
    isTeacher,
  };

  return <AppContext.Provider value={value}>{props.children}</AppContext.Provider>;
};
=======
import {useAuth, useUser} from "@clerk/clerk-react"


export const AppContext = createContext();

export const AppContextProvider = (props) =>{

    const backendUrl = import.meta.env.VITE_BACKEND_URL

    const currency = import.meta.env.VITE_CURRENCY
    const navigate = useNavigate()

    const {getToken} = useAuth()
    const {user} = useUser()

    const [allCourses, setAllCourses] = useState([])
    const [isTeacher, setIsTeacher] = useState(true)
    const [enrolledCourses, setEnrolledCourses] = useState([])

    //fetch all courses
    const fetchAllCourses = async () =>{
     setAllCourses(dummyCourses)
    }

     //function to calculate average rating of course
     const calculateRating = (course) =>{
        if(course.courseRatings.length === 0){
            return 0;
        }
        let totalRating = 0
        course.courseRatings.forEach((rating) =>{
            totalRating += rating.rating
        })
        return totalRating/course.courseRatings.length
     }

     //function to claculate course chapter time
     const calculateChapterTime = (chapter) =>{
        let time =0
        chapter.chapterContent.map((lecture) => time +=lecture.lectureDuration)
        return humanizeDuration(time*60*1000, {nits:["h","m"]})
     }

     //function to calculate course duration
     const calculateCourseDuration = (course) =>{
        let time =0
        course.courseContent.map((chapter)=> chapter.chapterContent.map((lecture) => time += lecture.lectureDuration))

        return humanizeDuration(time*60*1000, {nits:["h","m"]})

     }

     // function to calculate total no of lectures in course
     const calculateNoOfLectures = (course) =>{
        let totalLectures = 0;
        course.courseContent.forEach(chapter=>{
            if(Array.isArray(chapter.chapterContent)){
                totalLectures += chapter.chapterContent.length;
            }
        })
        return totalLectures;
     }

     //fetch user enrolled courses
     const fetchUserEnrolledCourse = async () =>{
        setEnrolledCourses(dummyCourses)
     }


    useEffect(()=>{
        fetchAllCourses()
        fetchUserEnrolledCourse()
    },[])

    const logToken = async ()=>{
        console.log(await getToken())
    }

    useEffect(()=>{
        if(user){
            logToken()
        }
    },[user])


    const value = {
        currency ,
        allCourses ,
        navigate,
        calculateRating,
        isTeacher,
        setIsTeacher,
        calculateChapterTime,
        calculateCourseDuration,
        calculateNoOfLectures,
        fetchUserEnrolledCourse,
        enrolledCourses
    }

    return (
        <AppContext.Provider value={value}>
            {props.children}
        </AppContext.Provider>
    )
}
>>>>>>> abhishek
