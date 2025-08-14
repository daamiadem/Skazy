// src/main/java/com/skazy/backend/service/SolutionService.java
package com.skazy.backend.service;

import com.skazy.backend.entity.Solution;
import com.skazy.backend.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private VietnamesePuzzleSolver puzzleSolver;

    public List<Solution> getAllSolutions() {
        return solutionRepository.findAll();
    }

    public Solution getSolutionById(Long id) {
        return solutionRepository.findById(id).orElse(null);
    }

    public List<Solution> searchSolutions(String searchTerm) {
        return solutionRepository.findByGridDataContaining(searchTerm);
    }

    public Solution saveSolution(Solution solution) {
        return solutionRepository.save(solution);
    }

    public Solution updateSolution(Long id, Solution solutionDetails) {
        return solutionRepository.findById(id)
            .map(sol -> {
                sol.setGridData(solutionDetails.getGridData());
                sol.setCorrect(solutionDetails.isCorrect());
                return solutionRepository.save(sol);
            })
            .orElse(null);
    }

    public boolean deleteSolution(Long id) {
        if (solutionRepository.existsById(id)) {
            solutionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteAllSolutions() {
        solutionRepository.deleteAll();
    }

    /**
     * Génère une solution aléatoire **valide** et **non dupliquée**
     */
    public Solution generateRandomSolution() {
        long start = System.nanoTime();

        // 1. Toutes les solutions valides
        List<int[]> allValidSolutions = puzzleSolver.findAllValidSolutions();

        // 2. Solutions existantes
        Set<String> existing = solutionRepository.findAll().stream()
            .map(Solution::getGridData)
            .collect(Collectors.toSet());

        // 3. Filtrer les nouvelles
        List<int[]> newSolutions = allValidSolutions.stream()
            .filter(sol -> {
                String str = puzzleSolver.convertSolutionToString(sol);
                return !existing.contains(str);
            })
            .collect(Collectors.toList());

        if (newSolutions.isEmpty()) {
            throw new RuntimeException("Toutes les solutions possibles ont déjà été générées.");
        }

        // 4. Choisir aléatoirement
        Random random = new Random();
        int[] chosen = newSolutions.get(random.nextInt(newSolutions.size()));

        long time = (System.nanoTime() - start) / 1_000; // µs
        Solution solution = puzzleSolver.solutionArrayToSolution(chosen, time);
        return solutionRepository.save(solution);
    }

    /**
     * Génère toutes les solutions valides (pour un bouton "Tout générer")
     */
    public List<Solution> generateAllSolutions() {
        long start = System.nanoTime();

        List<int[]> allValid = puzzleSolver.findAllValidSolutions();
        List<Solution> saved = new ArrayList<>();

        Set<String> existing = solutionRepository.findAll().stream()
            .map(Solution::getGridData)
            .collect(Collectors.toSet());

        for (int[] sol : allValid) {
            String data = puzzleSolver.convertSolutionToString(sol);
            if (!existing.contains(data)) {
                Solution solution = puzzleSolver.solutionArrayToSolution(sol, 0);
                saved.add(solutionRepository.save(solution));
            }
        }

        long total = (System.nanoTime() - start) / 1_000;
        // Option : mettre à jour le temps sur une solution exemple, ou stocker ailleurs
        return saved;
    }
}