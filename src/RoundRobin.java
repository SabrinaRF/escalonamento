package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import src.enumeration.Estado;

public class RoundRobin{
    private static final int QUANTUM = 3;
    private static final int NUMERO_DE_PROCESSOS = 5;

    public static void main(String[] args) {
        List<Processo> processos = inicializarProcessos(NUMERO_DE_PROCESSOS);
        int iteracoes = 0;

        while (!todosProcessosForamTerminados(processos)) {
            iteracoes++;
            System.out.println("\n-- Iteração " + iteracoes + " --");

            for (Processo processo : processos) {
                alterarEstadoDoProcesso(processo);

                if (processo.getEstado() == Estado.EXECUTANDO) {
                    System.out.println("PID: " + processo.getPid() + ", Estado: " + processo.getEstado() + ", Tempo Restante: " + processo.getTempoRestante());
                    processo.reduzirTempo(QUANTUM);

                    if (processo.getTempoRestante() <= 0) {
                        processo.setEstado(Estado.FINALIZADO);
                    } else {
                        processo.setEstado(Estado.PRONTO);
                    }
                } else {
                    processo.mostrar();
                }
            }
        }

        System.out.println("\nTodos processos foram finalizados! Total de " + iteracoes + " iterações.");
    }

    private static List<Processo> inicializarProcessos(int n) {
        List<Processo> processos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            processos.add(new Processo());
        }
        return processos;
    }

    private static void alterarEstadoDoProcesso(Processo processo) {
        Random random = new Random();
        double alterarEstado = random.nextDouble();
        if (alterarEstado < 0.5) {
            proximoEstado(processo);
        }
    }

    private static void proximoEstado(Processo processo) {
        switch (processo.getEstado()) {
            case NOVO:
                processo.setEstado(Estado.PRONTO);
                break;
            case PRONTO:
                if (new Random().nextDouble() < 0.5) {
                    if (!existeProcessoExecutando()) {
                        processo.setEstado(Estado.EXECUTANDO);
                    }
                } else {
                    processo.setEstado(Estado.BLOQUEADO);
                }
                break;
            case BLOQUEADO:
                processo.setEstado(Estado.PRONTO);
                break;
            default:
                break;
        }
    }

    private static boolean existeProcessoExecutando() {
        return false;
    }

    private static boolean todosProcessosForamTerminados(List<Processo> processos) {
        for (Processo processo : processos) {
            if (processo.getEstado() != Estado.FINALIZADO) {
                return false;
            }
        }
        return true;
    }
}
