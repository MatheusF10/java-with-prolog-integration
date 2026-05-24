package application;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Atom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles connectivity, initialization, and queries to the Prolog inference engine via JPL7.
 */
public class IntegradorProlog {

    /**
     * Constructor initializes the connection by consulting the core inference engine file.
     */
    public IntegradorProlog(String caminhoFicheiro) {
        try {
            Query consultaCarga = new Query("consult", new Term[]{ new Atom(caminhoFicheiro) });

            if (!consultaCarga.hasSolution()) {
                throw new RuntimeException("The Prolog engine rejected or could not locate: " + caminhoFicheiro);
            }
        } catch (Exception e) {
            System.err.println("[CRITICAL ERROR] Failed to load JPL7. Check your system PATH / LD_LIBRARY_PATH native configurations.");
            throw e;
        }
    }

    /**
     * Executes a list-based predicate (e.g., listar_em_risco(L)) and parses the Prolog list into a Java List.
     */
    public List<Integer> obterListaDePredicado(String predicado) {
        List<Integer> listaIds = new ArrayList<>();
        try {
            Query q = new Query(predicado + "(L)");
            Map<String, Term>[] solucoes = q.allSolutions();
            if (solucoes != null && solucoes.length > 0) {
                Term listaTermos = solucoes[0].get("L");
                for (Term t : listaTermos.toTermArray()) {
                    listaIds.add(t.intValue());
                }
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Failed evaluating the listing predicate: " + e.getMessage());
        }
        return listaIds;
    }

    /**
     * Fetches the student's name by ID.
     */
    public String obterNomeAluno(int id) {
        Query q = new Query("aluno", new Term[]{ new org.jpl7.Integer(id), new org.jpl7.Variable("Nome") });
        if (q.hasSolution()) {
            return q.oneSolution().get("Nome").name();
        }
        return null;
    }

    /**
     * Fetches the student's average grade by ID.
     */
    public double obterMediaAluno(int id) {
        Query q = new Query("media", new Term[]{ new org.jpl7.Integer(id), new org.jpl7.Variable("M") });
        if (q.hasSolution()) {
            return q.oneSolution().get("M").doubleValue();
        }
        return -1.0;
    }

    /**
     * Fetches the student's forum participation count by ID.
     */
    public int obterParticipacoesAluno(int id) {
        Query q = new Query("forum", new Term[]{ new org.jpl7.Integer(id), new org.jpl7.Variable("P") });
        if (q.hasSolution()) {
            return q.oneSolution().get("P").intValue();
        }
        return -1;
    }

    /**
     * Invokes the dynamic predicate to insert a new student.
     */
    public boolean adicionarAluno(int id, String nome) {
        Query q = new Query("adicionar_aluno", new Term[]{ new org.jpl7.Integer(id), new Atom(nome) });
        return q.hasSolution();
    }

    /**
     * Invokes the dynamic predicate to update a student's average grade.
     */
    public boolean atualizarMedia(int id, double novaMedia) {
        Query q = new Query("atualizar_media", new Term[]{ new org.jpl7.Integer(id), new org.jpl7.Float(novaMedia) });
        return q.hasSolution();
    }

    /**
     * Invokes the dynamic predicate to update a student's forum count.
     */
    public boolean atualizarParticipacao(int id, int participacoes) {
        Query q = new Query("atualizar_participacao", new Term[]{ new org.jpl7.Integer(id), new org.jpl7.Integer(participacoes) });
        return q.hasSolution();
    }

    /**
     * Invokes the dynamic predicate to completely remove a student's records.
     */
    public boolean removerAluno(int id) {
        Query q = new Query("remover_aluno", new Term[]{ new org.jpl7.Integer(id) });
        return q.hasSolution();
    }
}