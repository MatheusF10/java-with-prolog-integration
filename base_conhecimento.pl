% ================================
% BASE DE CONHECIMENTO - E-FÓLIO B
% ================================

:- dynamic aluno/2.

aluno(1, 'Ana').
aluno(2, 'Bruno').
aluno(3, 'Carla').
aluno(4, 'Diogo').
aluno(5, 'Eva').
aluno(6, 'Filipe').
aluno(99, 'Aluno Bonus').

:- dynamic forum/2.

forum(1, 4).
forum(2, 1).
forum(3, 5).
forum(4, 3).
forum(5, 2).
forum(6, 6).
forum(99, 0).

:- dynamic tarefa/2.

tarefa(1, 15).
tarefa(2, 8).
tarefa(3, 17).
tarefa(4, 10).
tarefa(5, 9).
tarefa(6, 14).
tarefa(99, 0).

:- dynamic quiz/2.

quiz(1, 12).
quiz(2, 9).
quiz(3, 18).
quiz(4, 11).
quiz(5, 8).
quiz(6, 16).
quiz(99, 0).

:- dynamic media/2.

media(1, 13.5).
media(2, 8.5).
media(3, 17.5).
media(4, 10.5).
media(5, 8.5).
media(6, 15.0).
media(99, 14.5).

:- dynamic estado/2.

estado(1, aprovado).
estado(2, em_risco).
estado(3, aprovado).
estado(4, condicionado).
estado(5, em_risco).
estado(6, aprovado).
estado(99, aprovado).

