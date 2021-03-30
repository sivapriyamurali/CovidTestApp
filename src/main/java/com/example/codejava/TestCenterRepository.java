package com.example.codejava;

import com.example.codejava.TestCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestCenterRepository  extends JpaRepository<TestCenter, Long> {
}
