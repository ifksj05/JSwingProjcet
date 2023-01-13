
public class RunnableStudy {

	public static void main(String[] args) {
		new RunnableStudy();
	}

	public RunnableStudy() {

		Thread tt1 = new Thread();

		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (int i = 0; i < 100; i++) {
					System.out.println("r1:" + i);
				}

			}
		};

		Runnable r2 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (int i = 0; i < 100; i++) {
					System.out.println("r2:" + i);
				}

			}
		};

		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);

		t1.start();
		t2.start();

	}

}
