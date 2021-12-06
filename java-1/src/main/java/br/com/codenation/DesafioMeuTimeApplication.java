package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.jogador.Jogador;
import br.com.codenation.time.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private final List<Time> times = new ArrayList<>();
	private final List<Jogador> jogadores = new ArrayList<>();

	public static void main(String[] args) {
		DesafioMeuTimeApplication app = new DesafioMeuTimeApplication();
		app.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
		System.out.println(app.buscarTimes());
		app.incluirJogador(1L, 1L, "teste", LocalDate.now(), 2, BigDecimal.TEN);
		app.incluirJogador(2L, 2L, "teste", LocalDate.now(), 2, BigDecimal.TEN);
	}

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		try {
			Busca.verificaTimeJaExiste(times, id);

			Time novoTime = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
			times.add(novoTime);
		} catch (IdentificadorUtilizadoException error) {
			throw new IdentificadorUtilizadoException();
		}
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		try {
			Busca.verificaTimeNaoExiste(times, idTime);

			boolean jogadorExistente = jogadores.stream().anyMatch(jogador -> jogador.getId() == id);
			if (jogadorExistente) throw new IdentificadorUtilizadoException();

			Jogador novoJogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
			jogadores.add(novoJogador);
		}
		catch (IdentificadorUtilizadoException error) {
			throw new IdentificadorUtilizadoException();
		}
	}

	public void definirCapitao(Long idJogador) {
		try {
			Busca.verificaJogador(jogadores, idJogador);

			Jogador jogadorEncontrado = jogadores.stream().filter(jogador -> jogador.getId() == idJogador)
					.collect(Collectors.toList()).get(0);

			jogadorEncontrado.setCaptain(true);
		}
		catch (JogadorNaoEncontradoException error) {
			throw new JogadorNaoEncontradoException();
		}
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		try {
			Busca.verificaTimeNaoExiste(times, idTime);

			Jogador jogadorEncontrado = jogadores.stream()
					.filter(jogador -> jogador.getIdTime() == idTime && jogador.isCaptain())
					.collect(Collectors.toList()).get(0);

			if (jogadorEncontrado != null) return jogadorEncontrado.getId();

			throw new CapitaoNaoInformadoException();
		}
		catch (TimeNaoEncontradoException e) {
			throw new TimeNaoEncontradoException();
		} catch (Exception error) {
			throw new CapitaoNaoInformadoException();
		}
	}

	public String buscarNomeJogador(Long idJogador) {
		try {
			Jogador jogador = Busca.buscaJogador(jogadores, idJogador);
			return jogador.getNome();
		}
		catch (JogadorNaoEncontradoException error) {
			throw new JogadorNaoEncontradoException();
		}
	}

	public String buscarNomeTime(Long idTime) {
		try {
			Time time = Busca.buscaTime(times, idTime);

			return time.getNome();
		}
		catch (TimeNaoEncontradoException error) {
			throw new TimeNaoEncontradoException();
		}
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		try {
			Busca.verificaTimeNaoExiste(times, idTime);

			List<Jogador> jogadoresDoTime = Busca.buscaJogadoresDoTime(jogadores, idTime);

			return jogadoresDoTime.stream()
					.map(Jogador::getId)
					.sorted()
					.collect(Collectors.toList());
		}
		catch (TimeNaoEncontradoException error) {
			throw new TimeNaoEncontradoException();
		}
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		try {
			Busca.verificaTimeNaoExiste(times, idTime);

			List<Jogador> jogadoresDoTime = Busca.buscaJogadoresDoTime(jogadores, idTime);

			return jogadoresDoTime.stream()
					.sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed())
					.map(Jogador::getId)
					.collect(Collectors.toList())
					.get(0);
		}
		catch (TimeNaoEncontradoException error) {
			throw new TimeNaoEncontradoException();
		}
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		try {
			Busca.verificaTimeNaoExiste(times, idTime);

			List<Jogador> jogadoresDoTime = Busca.buscaJogadoresDoTime(jogadores, idTime);

			return jogadoresDoTime.stream()
					.sorted(Comparator.comparing(Jogador::getId))
					.sorted(Comparator.comparing(Jogador::getDataNascimento))
					.map(Jogador::getId)
					.collect(Collectors.toList())
					.get(0);
		}
		catch (TimeNaoEncontradoException error) {
			throw new TimeNaoEncontradoException();
		}
	}

	public List<Long> buscarTimes() {
		List<Long> listaTimes = times.stream()
				.map(Time::getId)
				.sorted()
				.collect(Collectors.toList());

		return listaTimes;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		try {
			Busca.verificaTimeNaoExiste(times, idTime);

			List<Jogador> jogadoresDoTime = Busca.buscaJogadoresDoTime(jogadores, idTime);

			return jogadoresDoTime.stream()
					.sorted(Comparator.comparing(Jogador::getId))
					.sorted(Comparator.comparing(Jogador::getSalario).reversed())
					.map(Jogador::getId)
					.collect(Collectors.toList())
					.get(0);
		}
		catch (TimeNaoEncontradoException error) {
			throw new TimeNaoEncontradoException();
		}
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		try {
			Busca.verificaJogador(jogadores, idJogador);

			BigDecimal jogadorComMaiorSalario = jogadores.stream()
					.filter(jogador -> jogador.getId() == idJogador)
					.map(Jogador::getSalario)
					.collect(Collectors.toList())
					.get(0);

			return jogadorComMaiorSalario;
		}
		catch (JogadorNaoEncontradoException error) {
			throw new JogadorNaoEncontradoException();
		}
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Long> topJogadores = jogadores.stream()
				.sorted(Comparator.comparing(Jogador::getId))
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
				.map(Jogador::getId)
				.collect(Collectors.toList());

		if (topJogadores.size() == 0) return new ArrayList<>();
		return topJogadores.subList(0, top);
	}
}