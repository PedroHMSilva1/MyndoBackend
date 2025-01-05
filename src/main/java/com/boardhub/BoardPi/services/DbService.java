package com.boardhub.BoardPi.services;

import com.boardhub.BoardPi.entities.*;
import com.boardhub.BoardPi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DbService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private MembroProjetoRepository membroProjetoRepository;

    @Autowired
    private ListaRepository listaRepository;
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private PrioridadeRepository prioridadeRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;
    public void criarDadosMySQL() {
        try {
            Usuario usu1 = new Usuario("Fernando", "fer14@gmail.com", "12345678");
            Usuario usu2 = new Usuario("Ana", "anaa@gmail.com", "12345678");
            Usuario usu3 = new Usuario("Rob", "robert@gmail.com", "12345678");

            usuarioRepository.saveManual(usu1.getNome(), usu1.getEmail(), usu1.getSenha(), usu1.getDataCadastro());
            usuarioRepository.saveManual(usu2.getNome(), usu2.getEmail(), usu2.getSenha(), usu2.getDataCadastro());
            usuarioRepository.saveManual(usu3.getNome(), usu3.getEmail(), usu3.getSenha(), usu3.getDataCadastro());
            usu1 = usuarioRepository.findById((Long) 1L).orElse(null);
            usu2 = usuarioRepository.findById((Long) 2L).orElse(null);
            usu3 = usuarioRepository.findById((Long) 3L).orElse(null);

            Projeto p1 = new Projeto("Primeiro Projeto", usu1);
            Projeto p2 = new Projeto("Segundo Projeto", usu2);
            Projeto p3 = new Projeto("Terceiro Projeto", usu3);
            projetoRepository.saveAll(List.of(p1, p2, p3));

            p1 = projetoRepository.findById((Long) 1L).orElse(null);
            p2 = projetoRepository.findById((Long) 2L).orElse(null);
            p3 = projetoRepository.findById((Long) 3L).orElse(null);


            MembroProjeto mp1 = new MembroProjeto(p1, usu2, MembroProjeto.ADMIN);
            MembroProjeto mp2 = new MembroProjeto(p1, usu3, MembroProjeto.MEMBRO);
            MembroProjeto mp3 = new MembroProjeto(p2, usu1, MembroProjeto.MEMBRO);

            membroProjetoRepository.saveAll(List.of(mp1, mp2, mp3));

            Lista l1 = new Lista("Lista 1", 7, p1);
            Lista l2 = new Lista("Lista 2", 6, p1);
            Lista l3 = new Lista("Lista 1", 6, p2);
            Lista l4 = new Lista("Lista 1", 6, p3);
            l1.setId((Long) 1L);
            l2.setId((Long) 2L);
            l3.setId((Long) 3L);
            l4.setId((Long) 4L);

            listaRepository.saveAll(List.of(l1, l2, l3, l4));

            Prioridade pri1 = new Prioridade(p1, "Importante", "#FF0000");
            prioridadeRepository.save(pri1);
            pri1.setId((Long) 1L);

            Tarefa t1 = new Tarefa("Fazer o Teste", usu1, null, l1);
            t1.setTagPrioridade(pri1);
            Tarefa t2 = new Tarefa("Verificar", usu2, null, l1);
            Tarefa t3 = new Tarefa("Back-end", usu2, null, l4);

            tarefaRepository.saveAll(List.of(t1, t2, t3));
            t1.setId((Long) 1L);
            t2.setId((Long) 2L);
            t3.setId((Long) 3L);

            Comentario com1 = new Comentario("Legal", t1, usu1);
            Comentario com2 = new Comentario("louco", t1, usu2);
            Comentario com3 = new Comentario("Legal", t3, usu3);
            Comentario com4 = new Comentario("Legal", t3, usu1);

            comentarioRepository.saveAll(List.of(com1, com2, com3, com4));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void criarDadosH2(){
        Usuario usu1 = new Usuario("Fernando", "fer14@gmail.com", "12345678");
        Usuario usu2 = new Usuario("Ana", "anaa@gmail.com", "12345678");
        Usuario usu3 = new Usuario("Rob", "robert@gmail.com", "12345678");

        usuarioRepository.saveManual(usu1.getNome(), usu1.getEmail(), usu1.getSenha(), usu1.getDataCadastro());
        usuarioRepository.saveManual(usu2.getNome(), usu2.getEmail(), usu2.getSenha(), usu2.getDataCadastro());
        usuarioRepository.saveManual(usu3.getNome(),usu3.getEmail(),usu3.getSenha(),usu3.getDataCadastro());


        usu1 = usuarioRepository.findById((Long) 1L).orElse(null);
        usu2 = usuarioRepository.findById((Long) 2L).orElse(null);
        usu3 = usuarioRepository.findById((Long) 3L).orElse(null);

        Projeto p1 = new Projeto("Primeiro Projeto", usu1);
        Projeto p2 = new Projeto("Segundo Projeto", usu2);
        Projeto p3 = new Projeto("Terceiro Projeto", usu3);
        projetoRepository.saveAll(List.of(p1, p2, p3));

        p1 = projetoRepository.findById((Long) 1L).orElse(null);
        p2 = projetoRepository.findById((Long) 2L).orElse(null);
        p3 = projetoRepository.findById((Long) 3L).orElse(null);


        MembroProjeto mp1 = new MembroProjeto(p1, usu2, MembroProjeto.ADMIN);
        MembroProjeto mp2 = new MembroProjeto(p1, usu3, MembroProjeto.MEMBRO);
        MembroProjeto mp3 = new MembroProjeto(p2, usu1, MembroProjeto.MEMBRO);

        membroProjetoRepository.saveAll(List.of(mp1, mp2, mp3));

        Lista l1 = new Lista("Lista 1", 7, p1);
        Lista l2 = new Lista("Lista 2", 6, p1);
        Lista l3 = new Lista("Lista 1", 6, p2);
        Lista l4 = new Lista("Lista 1", 6, p3);
        l1.setId((Long) 1L);
        l2.setId((Long) 2L);
        l3.setId((Long) 3L);
        l4.setId((Long) 4L);

        listaRepository.saveAll(List.of(l1,l2,l3,l4));

        Prioridade pri1 = new Prioridade(p1, "Importante", "#FF0000");
        prioridadeRepository.save(pri1);
        pri1.setId((Long) 1L);

        Tarefa t1 = new Tarefa("Fazer o Teste", usu1, null, l1);
        t1.setTagPrioridade(pri1);
        Tarefa t2 = new Tarefa("Verificar", usu2, null, l1);
        Tarefa t3 = new Tarefa("Back-end", usu2, null, l4);

        tarefaRepository.saveAll(List.of(t1,t2,t3));
        t1.setId((Long) 1L);
        t2.setId((Long) 2L);
        t3.setId((Long) 3L);

        Comentario com1 = new Comentario("Legal",t1, usu1);
        Comentario com2 = new Comentario("louco",t1, usu2);
        Comentario com3 = new Comentario("Legal",t3, usu3);
        Comentario com4 = new Comentario("Legal",t3, usu1);

        comentarioRepository.saveAll(List.of(com1, com2, com3, com4));
    }
}
