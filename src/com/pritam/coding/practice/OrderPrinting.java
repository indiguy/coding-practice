package com.pritam.coding.practice;

/**
 * Write a program to print 1 2 3 in order always using three different thread
 *
 * @author pribiswas
 *
 */
public class OrderPrinting {

	private static final int LOOP_COUNT = 5;

	private static class CompletionLock {
		private boolean firstCompleted;
		private boolean secondCompleted;

		/**
		 * @return the firstCompleted
		 */
		public boolean isFirstCompleted() {
			return firstCompleted;
		}

		/**
		 * @param firstCompleted
		 *            the firstCompleted to set
		 */
		public void setFirstCompleted(boolean firstCompleted) {
			this.firstCompleted = firstCompleted;
		}

		/**
		 * @return the secondCompleted
		 */
		public boolean isSecondCompleted() {
			return secondCompleted;
		}

		/**
		 * @param secondCompleted
		 *            the secondCompleted to set
		 */
		public void setSecondCompleted(boolean secondCompleted) {
			this.secondCompleted = secondCompleted;
		}

		/**
		 * When no one started manipulating this object
		 *
		 * @return
		 */
		public boolean isInInitialState() {
			return !firstCompleted && !secondCompleted;
		}

	}

	public static void main(String[] args) {

		final CompletionLock completionLock = new CompletionLock();

		new Thread(() -> {
			int i = 0;
			while (i < LOOP_COUNT) {
				synchronized (completionLock) {
					while (!completionLock.isInInitialState()) {
						try {
							completionLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					i++;
					System.out.print(1);
					completionLock.setFirstCompleted(true);
					completionLock.notifyAll();
				}
			}
		}).start();

		new Thread(() -> {
			int i = 0;
			while (i < LOOP_COUNT) {
				synchronized (completionLock) {
					while (!completionLock.isFirstCompleted()) {
						try {
							completionLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					i++;
					System.out.print(2);
					completionLock.setSecondCompleted(true);
					completionLock.setFirstCompleted(false);
					completionLock.notifyAll();
				}
			}
		}).start();

		new Thread(() -> {
			int i = 0;
			while (i < LOOP_COUNT) {
				synchronized (completionLock) {
					while (!completionLock.isSecondCompleted()) {
						try {
							completionLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					i++;
					System.out.print(3);
					completionLock.setSecondCompleted(false);
					completionLock.notifyAll();
				}
			}
		}).start();
	}

}
