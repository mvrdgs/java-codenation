package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Estacionamento {

    private List<Carro> vagas = new ArrayList<>();

    public void estacionar(Carro carro) {
        if (vagas.size() >= 10) {
            try {
                Carro carThatNeedsToLeave = vagas
                        .stream()
                        .filter((car) -> car.getMotorista().getIdade() < 55)
                        .findFirst()
                        .get();

                vagas = vagas.stream()
                        .filter((car) -> !car.getMotorista().equals(carThatNeedsToLeave.getMotorista()))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                throw new EstacionamentoException("Nenhuma vaga disponível");
            }
        }
        vagas.add(carro);
    }

    public int carrosEstacionados() {
        return vagas.size();
    }

    public boolean carroEstacionado(Carro carro) {
        if (vagas.size() > 0) {
            return vagas
                    .stream()
                    .anyMatch((parkedCar) -> parkedCar.getMotorista().getNome()
                            .equals(carro.getMotorista().getNome()));
        }
        return false;
    }
}
