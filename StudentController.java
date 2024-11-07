package com.example.crud;

//import com.example.crud.students;
//import com.example.crud.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Fetch and save data from an external API
    @PostMapping("/save-from-api")
    public ResponseEntity<String> saveStudentFromExternalApi() {
        String externalApiUrl = "https://api.example.com/students"; // Replace with your external API URL

        try {
            // Make the API call to fetch data (assuming it's a list of students)
            ResponseEntity<students[]> response = restTemplate.exchange(
                externalApiUrl, HttpMethod.GET, null, students[].class
            );
            
            // Process the received students
            students[] students = response.getBody();

            if (students != null) {
                // Save each student to the database
                for (students student : students) {
                    studentRepository.save(student);
                }
                return ResponseEntity.ok("Students saved successfully!");
            } else {
                return ResponseEntity.status(404).body("No data returned from the external API.");
            }
        } catch (HttpClientErrorException e) {
            // Handle error from external API (e.g., 404, 500)
            return ResponseEntity.status(e.getStatusCode()).body("Error calling external API: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    // Get all students from the database
    @GetMapping
    public List<students> getAllStudents() {
        return studentRepository.findAll();
    }
}
