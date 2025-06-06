package com.example.backend.controller;

import com.example.backend.domain.Aula;
import com.example.backend.domain.Professor;
import com.example.backend.domain.Usuario;
import com.example.backend.repository.ProfessorRepository;
import com.example.backend.repository.UsuarioRepository;
import com.example.backend.service.AulaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    /**
     * POST /api/aulas
     *
     * JSON esperado no corpo:
     * {
     *   "alunoId": 5,
     *   "professorId": 3,
     *   "dataHora": "2025-06-15T14:30:00",
     *   "modalidade": "PRESENCIAL"
     * }
     */
    @PostMapping
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<Aula> agendarAula(@Valid @RequestBody AulaRequestBody body) {
        Optional<Usuario> optAluno = usuarioRepository.findById(body.getAlunoId());
        Optional<Professor> optProfessor = professorRepository.findById(body.getProfessorId());

        if (!optAluno.isPresent() || !optProfessor.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Usuario aluno = optAluno.get();
        Professor professor = optProfessor.get();

        Aula novaAula = new Aula();
        novaAula.setAluno(aluno);
        novaAula.setProfessor(professor);
        novaAula.setDataHora(LocalDateTime.parse(body.getDataHora()));
        novaAula.setModalidade(body.getModalidade());

        Aula salva = aulaService.agendarAula(novaAula);
        URI uri = URI.create("/api/aulas/" + salva.getId());
        return ResponseEntity.created(uri).body(salva);
    }

    /**
     * GET /api/aulas/aluno
     * Retorna todas as aulas onde o aluno_id = ID do usuário que está logado.
     */
    @GetMapping("/aluno")
    @PreAuthorize("hasRole('ALUNO')")
    public ResponseEntity<List<Aula>> listarAulasDoAluno(Authentication authentication) {
        // authentication.getName() = e-mail do usuario
        String email = authentication.getName();
        Usuario u = usuarioRepository.findByEmail(email);
        if (u == null) {
            return ResponseEntity.status(401).build();
        }
        List<Aula> lista = aulaService.buscarAulasPorAlunoId(u.getId());
        return ResponseEntity.ok(lista);
    }

    /**
     * GET /api/aulas/professor
     * Retorna todas as aulas onde o professor_id = ID do professor vinculado ao usuário que está logado.
     */
    @GetMapping("/professor")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<List<Aula>> listarAulasDoProfessor(Authentication authentication) {
        String email = authentication.getName();
        Usuario u = usuarioRepository.findByEmail(email);
        Optional<Professor> optP = professorRepository.findByUsuarioId(u.getId());
        if (!optP.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Professor p = optP.get();
        List<Aula> lista = aulaService.buscarAulasPorProfessorId(p.getId());
        return ResponseEntity.ok(lista);
    }

    // DTO interno para receber o JSON do POST /api/aulas
    public static class AulaRequestBody {
        private Long alunoId;
        private Long professorId;
        private String dataHora;   // ex.: "2025-06-15T14:30:00"
        private String modalidade; // "ONLINE" ou "PRESENCIAL"

        public Long getAlunoId() { return alunoId; }
        public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
        public Long getProfessorId() { return professorId; }
        public void setProfessorId(Long professorId) { this.professorId = professorId; }
        public String getDataHora() { return dataHora; }
        public void setDataHora(String dataHora) { this.dataHora = dataHora; }
        public String getModalidade() { return modalidade; }
        public void setModalidade(String modalidade) { this.modalidade = modalidade; }
    }
}
