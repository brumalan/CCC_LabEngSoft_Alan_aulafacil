// src/test/java/com/example/backend/service/UsuarioServiceTest.java
package com.example.backend.service;

import com.example.backend.domain.Usuario;
import com.example.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para a classe UsuarioService.
 */
@ExtendWith(MockitoExtension.class) // Habilita o uso do Mockito nesta classe de teste
class UsuarioServiceTest {

    @Mock // Cria um "dublê" (mock) do UsuarioRepository. Não vamos usar o banco de dados real.
    private UsuarioRepository usuarioRepository;

    @InjectMocks // Cria uma instância real do UsuarioService, injetando os mocks (como o usuarioRepository) nela.
    private UsuarioService usuarioService;

    private Usuario usuarioExistente;
    private Usuario dadosParaAtualizar;

    @BeforeEach // Este método roda antes de CADA teste
    void setUp() {
        // Prepara dados comuns que serão usados nos testes
        usuarioExistente = new Usuario("Nome Antigo", "teste@email.com", "senha123", "USER");
        usuarioExistente.setId(1L);

        dadosParaAtualizar = new Usuario("Nome Novo", "emailnovo@email.com", "senha456", "USER");
    }

    // --- TESTE 1: Caminho Feliz (Happy Path) ---
    @Test
    void deveAtualizarUsuarioComSucesso() {
        // 1. Arrange (Organização)
        // O que esperamos que aconteça quando o service chamar o repositório?

        // Quando o repo.findById(1L) for chamado, finja que encontrou o 'usuarioExistente'
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        // Quando o repo.save() for chamado com qualquer objeto Usuario, retorne esse mesmo objeto.
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));


        // 2. Act (Ação)
        // Chama o método que queremos testar
        Usuario usuarioAtualizado = usuarioService.atualizar(1L, dadosParaAtualizar);


        // 3. Assert (Verificação)
        // Verifica se o resultado é o que esperávamos

        assertNotNull(usuarioAtualizado); // Garante que o resultado não é nulo
        assertEquals(1L, usuarioAtualizado.getId()); // O ID não deve mudar
        assertEquals("Nome Novo", usuarioAtualizado.getNome()); // Verifica se o nome foi atualizado
        assertEquals("emailnovo@email.com", usuarioAtualizado.getEmail()); // Verifica se o email foi atualizado
        assertEquals("senha456", usuarioAtualizado.getSenha()); // Verifica se a senha foi atualizada

        // Opcional: Verifica se os métodos do repositório foram chamados exatamente 1 vez.
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(usuarioExistente);
    }


    // --- TESTE 2: Caminho de Erro ---
    @Test
    void deveLancarExcecaoAoTentarAtualizarUsuarioInexistente() {
        // 1. Arrange (Organização)
        // Quando o repo.findById com o ID 99L for chamado, finja que não encontrou ninguém (retorne um Optional vazio)
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());


        // 2. Act & Assert (Ação e Verificação)
        // Verificamos se o método 'atualizar' lança a exceção esperada (RuntimeException)
        // quando chamado com um ID que não existe.
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.atualizar(99L, dadosParaAtualizar);
        });

        // Verificamos se a mensagem da exceção está correta
        assertEquals("Usuário não encontrado para id: 99", exception.getMessage());

        // Garante que o método save NUNCA foi chamado, já que o usuário não foi encontrado
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}