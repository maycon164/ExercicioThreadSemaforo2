package controller;
import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread {

	private int pratoId;
	private Semaphore semaforo;
	private String nomePrato;
	private int tempo;

	public ThreadCozinha(int pratoId, Semaphore semaforo) {
		this.pratoId = pratoId;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		receberPedido();
		cozinharPrato();
		//Seção Critica
		
		try {
			semaforo.acquire();
			entregarPrato();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}		
	}

	private void receberPedido() {
		if (pratoId % 2 == 0) {
			nomePrato = "Lasanha a Bolonhesa";
			tempo = (int) (Math.random() * 601) + 600;
		} else {
			nomePrato = "Sopa de Cebola";
			tempo = (int) (Math.random() * 301) + 500;
		}
	}

	private void cozinharPrato() {
		int percentual = 0;
		while(percentual < 100) {
			try {
				sleep(100);
				percentual += tempo / 100;
				System.out.println("#" + pratoId + " - " + nomePrato + " cozinhou: " + percentual +"%");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}

	private void entregarPrato() {
		try {
			System.out.println("#" + pratoId + " - " + nomePrato + " está sendo entregue");
			sleep(500);
			System.out.println("#" + pratoId + " - " + nomePrato + " prato entregue");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
