package com.example.crud;

//import com.example.crud.students;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

	public interface StudentRepository extends JpaRepository<students, Long> {
	    // You can define custom query methods if needed
	}
