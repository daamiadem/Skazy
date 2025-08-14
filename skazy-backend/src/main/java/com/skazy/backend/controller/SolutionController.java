// src/main/java/com/skazy/backend/controller/SolutionController.java
package com.skazy.backend.controller;

import com.skazy.backend.entity.Solution;
import com.skazy.backend.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solutions")
@CrossOrigin(origins = "http://localhost:3000") // À ajuster si ton front est sur un autre port
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    /**
     * GET /api/solutions
     * Récupère toutes les solutions en base
     */
    @GetMapping
    public ResponseEntity<List<Solution>> getAllSolutions() {
        return ResponseEntity.ok(solutionService.getAllSolutions());
    }

    /**
     * GET /api/solutions/{id}
     * Récupère une solution par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Solution> getSolutionById(@PathVariable Long id) {
        Solution solution = solutionService.getSolutionById(id);
        return solution != null
                ? ResponseEntity.ok(solution)
                : ResponseEntity.notFound().build();
    }

    /**
     * GET /api/solutions/search?q=...
     * Recherche des solutions contenant un terme (dans gridData)
     * Le paramètre s'appelle bien 'q' → important pour le front
     */
    @GetMapping("/search")
    public ResponseEntity<List<Solution>> searchSolutions(@RequestParam String q) {
        List<Solution> results = solutionService.searchSolutions(q);
        return ResponseEntity.ok(results);
    }

    /**
     * POST /api/solutions
     * Crée une nouvelle solution (manuellement)
     */
    @PostMapping
    public ResponseEntity<Solution> createSolution(@RequestBody Solution solution) {
        Solution saved = solutionService.saveSolution(solution);
        return ResponseEntity.ok(saved);
    }

    /**
     * PUT /api/solutions/{id}
     * Met à jour une solution (statut correct/incorrect, etc.)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Solution> updateSolution(
            @PathVariable Long id,
            @RequestBody Solution solutionDetails) {
        Solution updated = solutionService.updateSolution(id, solutionDetails);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    /**
     * DELETE /api/solutions/{id}
     * Supprime une solution par ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolution(@PathVariable Long id) {
        boolean deleted = solutionService.deleteSolution(id);
        return deleted
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    /**
     * DELETE /api/solutions
     * Supprime toutes les solutions
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllSolutions() {
        solutionService.deleteAllSolutions();
        return ResponseEntity.ok().build(); // 200 OK (ou 204 si tu préfères)
    }

    /**
     * POST /api/solutions/generate
     * Génère une nouvelle solution aléatoire et unique
     * Retourne la solution avec le temps de génération
     */
    @PostMapping("/generate")
    public ResponseEntity<Solution> generateSolution() {
        try {
            Solution generated = solutionService.generateRandomSolution();
            return ResponseEntity.ok(generated);
        } catch (RuntimeException e) {
            // En cas d'échec (ex: plus de solution unique), on pourrait gérer autrement
            // Mais ici, on laisse le front gérer l'erreur si nécessaire
            return ResponseEntity.badRequest().build();
        }
    }
}