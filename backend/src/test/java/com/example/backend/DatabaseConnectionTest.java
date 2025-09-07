package com.example.backend;

import com.example.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deveConectarAoBancoEInjetarRepository() {
        // O objetivo deste teste é simples:
        // 1. A anotação @SpringBootTest tenta iniciar a aplicação.
        // 2. A anotação @Autowired tenta injetar o UsuarioRepository.
        // 3. Se o repositório não for nulo, significa que o Spring conseguiu
        //    criar o bean do repositório, o que requer uma conexão bem-sucedida
        //    com o banco de dados.

        assertNotNull(usuarioRepository, "O UsuarioRepository não deveria ser nulo. A conexão com o banco pode ter falhado.");

        // Como um passo extra, podemos tentar usar o repositório:
        long count = usuarioRepository.count();
        System.out.println("Encontrados " + count + " usuários no banco de dados.");
    }
}