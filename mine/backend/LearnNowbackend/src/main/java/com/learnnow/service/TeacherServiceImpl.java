package com.learnnow.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnnow.dto.TeacherRequestDTO;
import com.learnnow.dto.TeacherResponseDTO;
import com.learnnow.exception.ResourceNotFoundException;
import com.learnnow.pojo.Teacher;
import com.learnnow.dao.TeacherDao;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherRequestDTO.getFirstName());
        teacher.setLastName(teacherRequestDTO.getLastName());
        teacher.setEmail(teacherRequestDTO.getEmail());
        teacher.setPassword(teacherRequestDTO.getPassword());
        teacher.setDob(LocalDate.parse(teacherRequestDTO.getDob()));
        teacher.setUserRole(teacherRequestDTO.getUserRole());
        
        // ✅ Add these missing mappings
        teacher.setQualification(teacherRequestDTO.getQualification());
        teacher.setSpecialization(teacherRequestDTO.getSpecialization());

        Teacher savedTeacher = teacherDao.save(teacher);
        return mapToTeacherResponseDTO(savedTeacher);
    }

    @Override
    public TeacherResponseDTO getTeacherById(Long id) {
        Teacher teacher = teacherDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + id));
        return mapToTeacherResponseDTO(teacher);
    }

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        List<Teacher> teachers = teacherDao.findAll();
        return teachers.stream()
                .map(this::mapToTeacherResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = teacherDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + id));

        teacher.setFirstName(teacherRequestDTO.getFirstName());
        teacher.setLastName(teacherRequestDTO.getLastName());
        teacher.setEmail(teacherRequestDTO.getEmail());
        teacher.setPassword(teacherRequestDTO.getPassword());
        teacher.setDob(LocalDate.parse(teacherRequestDTO.getDob()));

        Teacher updatedTeacher = teacherDao.save(teacher);
        return mapToTeacherResponseDTO(updatedTeacher);
    }

    @Override
    public String deleteTeacher(Long id) {
        if (!teacherDao.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found with ID: " + id);
        }
        teacherDao.deleteById(id);
        return "Teacher deleted successfully with id: " + id; // ✅ Return message
    }

    private TeacherResponseDTO mapToTeacherResponseDTO(Teacher teacher) {
        TeacherResponseDTO dto = new TeacherResponseDTO();
        dto.setId(teacher.getId());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setEmail(teacher.getEmail());
        dto.setDob(teacher.getDob().toString());
        return dto;
    }
}
