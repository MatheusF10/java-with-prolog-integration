% =================================================================
% U.C. 21077 – Programming Languages | e-Fólio B 25/26
% File: motor_inferencia.pl
% Description: Core inference engine rules and dynamic database management.
% =================================================================

% Import and consult the untouched original knowledge base
:- consult('base_conhecimento.pl').

% Declare the original predicates as dynamic so they can be modified in memory
:- dynamic aluno/2.
:- dynamic forum/2.
:- dynamic tarefa/2.
:- dynamic quiz/2.
:- dynamic media/2.
:- dynamic estado/2.

% =================================================================
% 1. Basic Queries about Students
% =================================================================

% a) Student at risk: average grade < 10 OR less than 3 forum participations.
em_risco(X) :-
    aluno(X, _),
    ( (media(X, M), M < 10) ; (forum(X, P), P < 3) ).

% b) Student with adequate participation: 3 or more forum participations.
participativo(X) :-
    aluno(X, _),
    forum(X, P),
    P >= 3.

% c) Student with positive performance: average grade >= 10.
bom_desempenho(X) :-
    aluno(X, _),
    media(X, M),
    M >= 10.

% =================================================================
% 2. Inference Based on Class Average
% =================================================================

% a) Dynamically calculates the arithmetic mean of all media/2 facts.
calcular_media_turma(MediaTurma) :-
    findall(M, media(_, M), ListaMedias),
    sum_list(ListaMedias, Soma),
    length(ListaMedias, Count),
    Count > 0,
    MediaTurma is Soma / Count.

% b) Student above class average: individual average >= class average.
acima_media(X) :-
    aluno(X, _),
    media(X, M),
    calcular_media_turma(MTurma),
    M >= MTurma.

% =================================================================
% 3. Composite Queries and Lists (Using findall/3)
% =================================================================

listar_em_risco(L) :- findall(X, em_risco(X), L).
listar_participativos(L) :- findall(X, participativo(X), L).
listar_bons(L) :- findall(X, bom_desempenho(X), L).
listar_acima_media(L) :- findall(X, acima_media(X), L).

% =================================================================
% Bonus Feature (0.1 points value)
% =================================================================
% Student under observation: positive grade (>= 10) but low participation (< 3).
em_observacao(X) :-
    bom_desempenho(X),
    \+ participativo(X).

listar_em_observacao(L) :- findall(X, em_observacao(X), L).

% =================================================================
% 4. Dynamic Management and Data Persistence
% =================================================================

% Saves the entire current state back to 'base_conhecimento.pl' keeping facts clean
salvar_base_dados :-
    tell('base_conhecimento.pl'),
    format('% ================================~n', []),
    format('% BASE DE CONHECIMENTO - E-FÓLIO B~n', []),
    format('% ================================~n~n', []),
    listing(aluno/2),
    listing(forum/2),
    listing(tarefa/2),
    listing(quiz/2),
    listing(media/2),
    listing(estado/2),
    told.

% a) Add a new student (initializes other related facts to zero/default)
adicionar_aluno(Id, Nome) :-
    \+ aluno(Id, _),
    assertz(aluno(Id, Nome)),
    assertz(forum(Id, 0)),
    assertz(tarefa(Id, 0)),
    assertz(quiz(Id, 0)),
    assertz(media(Id, 0.0)),
    assertz(estado(Id, condicionado)),
    salvar_base_dados.

% b) Add or update a student's average grade
atualizar_media(Id, NovaMedia) :-
    aluno(Id, _),
    retractall(media(Id, _)),
    assertz(media(Id, NovaMedia)),
    % Logical status update based on the new average grade
    retractall(estado(Id, _)),
    (NovaMedia >= 10.0 -> assertz(estado(Id, aprovado)) ; assertz(estado(Id, em_risco))),
    salvar_base_dados.

% c) Add or update the number of forum participations
atualizar_participacao(Id, NovasParticipacoes) :-
    aluno(Id, _),
    retractall(forum(Id, _)),
    assertz(forum(Id, NovasParticipacoes)),
    salvar_base_dados.

% d) Delete all facts associated with a student from the knowledge base
remover_aluno(Id) :-
    retractall(aluno(Id, _)),
    retractall(forum(Id, _)),
    retractall(tarefa(Id, _)),
    retractall(quiz(Id, _)),
    retractall(media(Id, _)),
    retractall(estado(Id, _)),
    salvar_base_dados.