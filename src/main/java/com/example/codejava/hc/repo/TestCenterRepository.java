package com.example.codejava.hc.repo;

import com.example.codejava.User;
import com.example.codejava.hc.domain.TestCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LiuJie
 * @since 25/03/2021
 */
@Repository
public interface TestCenterRepository  extends JpaRepository<TestCenter, Long> {
}
