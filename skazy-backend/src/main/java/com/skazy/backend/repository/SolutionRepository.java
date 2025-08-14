package com.skazy.backend.repository;

import com.skazy.backend.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    
    @Query("SELECT s FROM Solution s WHERE s.gridData LIKE %:searchTerm%")
    List<Solution> findByGridDataContaining(@Param("searchTerm") String searchTerm);
    
    List<Solution> findByCorrect(boolean correct);
    boolean existsByGridData(String gridData);
}
