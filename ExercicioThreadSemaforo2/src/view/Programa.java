package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCozinha;

public class Programa {
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int pratoId = 1; pratoId <= 5; pratoId++) {
			Thread prato = new ThreadCozinha(pratoId, semaforo);
			prato.start();
		}
	}
}
