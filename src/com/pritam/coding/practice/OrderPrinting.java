package com.pritam.coding.practice;

public class OrderPrinting {

	private static final int LOOP_COUNT = 5;

	private static class CompletionLock {
		private boolean firstCompleted;
		private boolean secondCompleted;
		private boolean thirdCompleted;

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
		 * @return the thirdCompleted
		 */
		public boolean isThirdCompleted() {
			return thirdCompleted;
		}

		/**
		 * @param thirdCompleted
		 *            the thirdCompleted to set
		 */
		public void setThirdCompleted(boolean thirdCompleted) {
			this.thirdCompleted = thirdCompleted;
		}

		/**
		 * When no one started manipulating this object
		 *
		 * @return
		 */
		public boolean isInInitialState() {
			return !firstCompleted && !secondCompleted && !thirdCompleted;
		}

	}

	public static void main(String[] args) {

		final CompletionLock completionLock = new CompletionLock();

		new Thread(() -> {
			int i = 0;
			while (i < LOOP_COUNT) {
				synchronized (completionLock) {
					while (!completionLock.isInInitialState() && !completionLock.isThirdCompleted()) {
						try {
							completionLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					i++;
					System.out.print(1);
					completionLock.setFirstCompleted(true);
					completionLock.setThirdCompleted(false);
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
					completionLock.setThirdCompleted(true);
					completionLock.setSecondCompleted(false);
					completionLock.notifyAll();
				}
			}
		}).start();
	}

}
