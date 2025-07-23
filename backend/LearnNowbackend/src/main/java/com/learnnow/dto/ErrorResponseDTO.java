package com.learnnow.dto;

import java.time.LocalDateTime;

<<<<<<< HEAD
import lombok.Data;

@Data
public class ErrorResponseDTO {
	    private LocalDateTime timestamp;
	    private int status;
	    private String error;
	    private String message;
	    private String path;
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Object message; // Can hold String OR Map<String, String>
    private String path;
>>>>>>> abhishek
}
