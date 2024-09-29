package src;

import src.enumeration.Estado;
import java.util.Random;
import static src.enumeration.Estado.NOVO;

public class Processo {
    private String pid;
    private Estado estado;
    private int tempoRestante;

    public Processo() {
        this.pid = gerarPidAleatorio();
        this.estado = NOVO;
        this.tempoRestante = gerarTempoRestanteAleatorio();
    }

    private String gerarPidAleatorio() {
        return String.valueOf(1000 + new Random().nextInt(9000));
    }

    private int gerarTempoRestanteAleatorio() {
        return 5 + new Random().nextInt(6); // tempo entre 5 e 10
    }

    public String getPid() {
        return pid;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getTempoRestante() {
        return tempoRestante;
    }

    public void reduzirTempo(int quantum) {
        this.tempoRestante -= quantum;
        if (this.tempoRestante < 0) {
            this.tempoRestante = 0;
        }
    }

    public void mostrar() {
        System.out.println("PID: " + pid + ", Estado: " + estado + ", Tempo Restante: " + tempoRestante);
    }
}

