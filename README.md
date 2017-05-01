# Sudoku Solver

The goals of this project are:
* answering this question: is it feasible to use a genetic algorithm to solve a Sudoku?
* and learning Scala.

The algorithm is composed by three main operations:
* Pick the best candidates based on the score calculated by the fitting function, the lower the score the more probable is to pick the candidate.
* Cross candidates by mixing features from two candidates to produce two new candidates.
* Mutate candidates applying random changes.

With these three operations the algorithm repeats these steps until a solution is found or the program is terminated:
1. Cross the best candidates from the population.
2. Apply a few random mutations to the population.
3. Check if a member of the new population is a correct solution.

There is risk for the algorithm to get stuck in some local optima instead of descending to the global optima, but I hope the mutation step will allow for some climbing to avoid this situation.

## License

MIT License (see LICENSE in the repository).
