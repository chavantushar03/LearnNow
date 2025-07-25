<<<<<<< HEAD
import React from 'react';
import { Outlet } from 'react-router-dom';
import Sidebar from '../../components/teacher/Sidebar';

const Teacher = () => {
  return (
    <div className="flex flex-col min-h-screen bg-white text-default">
     
      <div className="flex flex-1 min-h-[calc(100vh-64px)]"> 
        <div className="w-[250px] border-r bg-gray-100">
          <Sidebar />
        </div>
        <div className="flex-1 p-4 overflow-y-auto">
          <Outlet />
        </div>
      </div>
    </div>
  );
};

export default Teacher;
=======
import React from 'react'
import { Outlet } from 'react-router-dom'
import Navbar from '../../components/teacher/Navbar'
import Sidebar from '../../components/teacher/Sidebar'
import Footer from '../../components/teacher/Footer'

const Teacher = () => {
  return (
    <div className='text-default min-h-screen bg-white'>
      <Navbar />
      <div className='flex'>
        <Sidebar/>
       <div className='flex-1'>
       {<Outlet />}
       </div>
      </div>
      <Footer/>
    </div>
  )
}

export default Teacher
>>>>>>> abhishek
