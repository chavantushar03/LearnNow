package com.learnnow.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnnow.dto.CourseRequestDTO;
import com.learnnow.dto.CourseResponseDTO;
import com.learnnow.exception.ApiException;
import com.learnnow.exception.ResourceNotFoundException;
import com.learnnow.pojo.Course;
import com.learnnow.pojo.Teacher;
import com.learnnow.dao.CourseDao;
import com.learnnow.dao.TeacherDao;
import com.learnnow.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
        Teacher teacher = teacherDao.findById(courseRequestDTO.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher not found with ID: " + courseRequestDTO.getTeacherId()));

        Course course = new Course();
        course.setTitle(courseRequestDTO.getTitle());
        course.setDescription(courseRequestDTO.getDescription());
        course.setTeacher(teacher);

        Course savedCourse = courseDao.save(course);
        return mapToCourseResponseDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
        return mapToCourseResponseDTO(course);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseDao.findAll();
        if (courses.isEmpty()) {
            throw new ApiException("No courses available.");
        }
        return courses.stream()
                .map(this::mapToCourseResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {
        Course course = courseDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));

        Teacher teacher = teacherDao.findById(courseRequestDTO.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher not found with ID: " + courseRequestDTO.getTeacherId()));

        course.setTitle(courseRequestDTO.getTitle());
        course.setDescription(courseRequestDTO.getDescription());
        course.setTeacher(teacher);

        Course updatedCourse = courseDao.save(course);
        return mapToCourseResponseDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseDao.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with ID: " + id);
        }
        courseDao.deleteById(id);
    }

    private CourseResponseDTO mapToCourseResponseDTO(Course course) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setTeacherName(course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
        return dto;
    }
}
