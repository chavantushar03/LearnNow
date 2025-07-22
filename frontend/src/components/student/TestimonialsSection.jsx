
// import { assets } from '../../assets/assets'
import {useState,useEffect} from 'react';
import {getTestimonials} from '../../services/getTestimonials';
// import axios from 'axios';

// const TestimonialsSection = () => {
//   return (
//     <div className='pb-14 px-8 md:px-10'>
//       <h2 className='text-3xl font-medium text-[var(--color-text)]'>Testimonials</h2>
      
//       <p className='md:text-base text-[var(--color-text-secondary)] mt-3'>
//         Hear from our learners as they share their journeys of transformation, success, and how our <br />
//         platform has made a difference in their lives.
//       </p>

//       <div className='grid grid-cols-auto gap-8 mt-14'>
//         {dummyTestimonial.map((testimonial, index) => (
//           <div 
//             key={index} 
//             className='text-sm text-left border border-[var(--color-border)] 
//               pb-6 rounded-lg bg-[var(--color-bg-light)] dark:bg-[var(--color-bg-dark)] 
//               shadow-[0px_4px_15px_0px] shadow-black/5 overflow-hidden'
//           >
//             {/* Top User Info */}
//             <div className='flex items-center gap-4 px-5 py-4 bg-[var(--color-bg-alt)] dark:bg-[var(--color-bg-darker)]'>
//               <img className='h-12 w-12 rounded-full' src={testimonial.image} alt={testimonial.name} />
//               <div>
//                 <h1 className='text-lg font-medium text-[var(--color-text)]'>{testimonial.name}</h1>
//                 <p className='text-[var(--color-text-secondary)]'>{testimonial.role}</p>
//               </div>
//             </div>

//             {/* Rating & Feedback */}
//             <div className='p-5 pb-7'>
//               <div className='flex gap-0.5'>
//                 {[...Array(5)].map((_, i) => (
//                   <img 
//                     className='h-5' 
//                     key={i} 
//                     src={i < Math.floor(testimonial.rating) ? assets.star : assets.star_blank} 
//                     alt="" 
//                   />
//                 ))}
//               </div>
//               <p className='text-[var(--color-text-secondary)] mt-5'>{testimonial.feedback}</p>
//             </div>

//             {/* Read more link */}
//             <a 
//               href="#" 
//               className='text-[var(--color-primary)] underline px-5 hover:opacity-90 transition-opacity duration-300'
//             >
//               Read more
//             </a>
//           </div>
//         ))}
//       </div>
//     </div>
//   )
// }

/*
const TestimonialsSection = () =>{
  const [testimonial,setTestimonaials] = useState([]);
}
useEffect(()=>{
  const fetchTestimonials=async()=>{
    try{
      const response = await axios.get('https://your-api-endpoint.com/api/testimonials');
      setTestimonaials(response.data);
    }catch(error)
    {
      console.error("Error fetching testimonials:", error);
    }
  };
  fetchTestimonials();

},[]);
*/

const TestimonialsSection = () => {
  const [testimonials, setTestimonials] = useState([]);

  useEffect(() => {
    const fetchTestimonials = async () => {
      const response = await getTestimonials();
      if (response.status === 'success') {
        setTestimonials(response.data);
      } else {
        console.error('Error fetching testimonials:', response.message);
      }
    };

    fetchTestimonials();
  }, []);

  return (
    <div className='pb-14 px-8 md:px-10'>
      <h2 className='text-3xl font-medium text-[var(--color-text)] mb-6'>Testimonials</h2>
      <div className='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6'>
        {testimonials.map((item, index) => (
          <div
            key={index}
            className='bg-white shadow-md rounded-2xl p-6 border border-gray-200'
          >
            <p className='text-gray-700 italic mb-4'>"{item.feedback}"</p>
            <h4 className='text-lg font-semibold text-gray-900'>{item.name}</h4>
            <p className='text-yellow-500'>Rating: {item.rating}/5</p>
          </div>
        ))}
      </div>
    </div>
  );
};


export default TestimonialsSection
