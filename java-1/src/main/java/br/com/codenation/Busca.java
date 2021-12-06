package br.com.codenation;

import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.jogador.Jogador;
import br.com.codenation.time.Time;

import java.util.List;
import java.util.stream.Collectors;

public class Busca {
    public static void verificaTimeJaExiste(List<Time> times, Long idTime) throws IdentificadorUtilizadoException {
        boolean timeExistente = times.stream().anyMatch(time -> time.getId() == idTime);

        if (timeExistente) throw new IdentificadorUtilizadoException();
    }

    public static void verificaTimeNaoExiste(List<Time> times, Long idTime) throws TimeNaoEncontradoException {
        boolean timeExistente = times.stream().anyMatch(time -> time.getId() == idTime);

        if (!timeExistente) throw new TimeNaoEncontradoException();
    }

    public static Time buscaTime(List<Time> times, Long idTime) throws TimeNaoEncontradoException {
        verificaTimeNaoExiste(times, idTime);

        Time timeEncontrado = times.stream().filter(time -> time.getId() == idTime)
                .collect(Collectors.toList()).get(0);

        if (timeEncontrado == null) throw new TimeNaoEncontradoException();

        return timeEncontrado;
    }

    public static void verificaJogador(List<Jogador> jogadores, Long idJogador){
        boolean jogadorExistente = jogadores.stream().anyMatch(jogador -> jogador.getId() == idJogador);

        if (!jogadorExistente) throw new JogadorNaoEncontradoException();
    }

    public static Jogador buscaJogador(List<Jogador> jogadores, Long idJogador) throws JogadorNaoEncontradoException {
        verificaJogador(jogadores, idJogador);

        Jogador jogadorEncontrado = jogadores.stream().filter(jogador -> jogador.getId() == idJogador)
                .collect(Collectors.toList()).get(0);

        if (jogadorEncontrado != null) return jogadorEncontrado;

        throw new JogadorNaoEncontradoException();
    }

    public static List<Jogador> buscaJogadoresDoTime(List<Jogador> jogadores, Long idTime) {
        return jogadores.stream().filter(jogador -> jogador.getIdTime() == idTime)
                .collect(Collectors.toList());

    }
}
