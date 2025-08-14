package com.skazy.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solutions")
public class Solution {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String gridData;
    
    @Column(nullable = false)
    private boolean correct;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column
    private Long generationTimeMs;
    
    public Solution() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Solution(String gridData, boolean correct, Long generationTimeMs) {
        this();
        this.gridData = gridData;
        this.correct = correct;
        this.generationTimeMs = generationTimeMs;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getGridData() {
        return gridData;
    }
    
    public void setGridData(String gridData) {
        this.gridData = gridData;
    }
    
    public boolean isCorrect() {
        return correct;
    }
    
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public Long getGenerationTimeMs() {
        return generationTimeMs;
    }
    
    public void setGenerationTimeMs(Long generationTimeMs) {
        this.generationTimeMs = generationTimeMs;
    }
}
