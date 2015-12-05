import java.text.DecimalFormat;

public class Week1Demo {

	public static void main(String[] args) {
		long N[] = { 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768,
				65536, 131072 };
		double TN[] = { 0.000, 0.001, 0.005, 0.025, 0.121, 0.680, 3.421,
				18.044, 93.228, 487.282, 2550.077 };

		DecimalFormat df = new DecimalFormat("00.00");
		for (int i = 0; i < N.length; i++) {
			StdOut.println(df.format(TN[i] / N[i]));
		}
		
		StdOut.println("========================");
		for (int i = 0; i < N.length; i++) {
			StdOut.println(df.format((TN[i] / N[i]) * 100));
		}
	}

}
