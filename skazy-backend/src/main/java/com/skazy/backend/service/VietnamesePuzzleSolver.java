// src/main/java/com/skazy/backend/service/VietnamesePuzzleSolver.java
package com.skazy.backend.service;

import com.skazy.backend.entity.Solution;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VietnamesePuzzleSolver {

    /**
     * Résout le puzzle vietnamien et retourne toutes les solutions valides.
     */
    public List<int[]> findAllValidSolutions() {
        List<int[]> solutions = new ArrayList<>();
        int[] current = new int[9];
        boolean[] used = new boolean[10]; // 1 à 9

        backtrack(solutions, current, used, 0);
        return solutions;
    }

    private void backtrack(List<int[]> solutions, int[] current, boolean[] used, int index) {
        if (index == 9) {
            if (isValidSolution(current)) {
                solutions.add(current.clone());
            }
            return;
        }

        for (int digit = 1; digit <= 9; digit++) {
            if (!used[digit]) {
                current[index] = digit;
                used[digit] = true;
                backtrack(solutions, current, used, index + 1);
                used[digit] = false;
            }
        }
    }

    /**
     * Valide une solution selon l'équation du puzzle vietnamien :
     * a + 13*b/c + d + 12*e - f - 11 + g*h/i - 10 = 66
     */
    private boolean isValidSolution(int[] sol) {
        double a = sol[0], b = sol[1], c = sol[2],
                d = sol[3], e = sol[4], f = sol[5],
                g = sol[6], h = sol[7], i = sol[8];

        if (c == 0 || i == 0) return false; // division par zéro

        double result = a +
                13.0 * b / c +
                d +
                12.0 * e -
                f -
                11 +
                g * h / i -
                10;

        return Math.abs(result - 66.0) < 1e-9;
    }

    /**
     * Convertit une solution en chaîne pour stockage (format CSV par ligne)
     */
    public String convertSolutionToString(int[] solution) {
        StringBuilder sb = new StringBuilder();
    
        // Ligne 0: a + b = 66
        sb.append(solution[0]).append(",+,").append(solution[1]).append(",=66;");
    
        // Ligne 1: × = - =
        sb.append("×,=,-,=;");
    
        // Ligne 2: 13, c, 11, 10
        sb.append("13,").append(solution[2]).append(",11,10;");
    
        // Ligne 3: × + + -
        sb.append("×,+,-,×;");
    
        // Ligne 4: d e f g
        sb.append(solution[3]).append(",").append(solution[4]).append(",").append(solution[5]).append(",").append(solution[6]).append(";");
    
        // Ligne 5: ÷ h × i
        sb.append("÷,").append(solution[7]).append(",×,").append(solution[8]);
    
        return sb.toString();
    }

    /**
     * Crée une entité Solution à partir d'un tableau
     */
    public Solution solutionArrayToSolution(int[] solutionArray, long generationTime) {
        String gridData = convertSolutionToString(solutionArray);
        boolean isCorrect = true;
        return new Solution(gridData, isCorrect, generationTime);
    }
}