//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package it.unibs.fp.mylib;

public class BelleStringhe {
	private static final String SPAZIO = " ";
	private static final String CORNICE = "---------------------------------------------------";
	private static final String ACAPO = "\n";

	public BelleStringhe() {
	}

	public static String incornicia(String s) {
		StringBuffer res = new StringBuffer();
		res.append("---------------------------------------------------\n");
		res.append(s + "\n");
		res.append("---------------------------------------------------\n");
		return res.toString();
	}

	public static String incolonna(String s, int larghezza) {
		StringBuffer res = new StringBuffer(larghezza);
		int numCharDaStampare = Math.min(larghezza, s.length());
		res.append(s, 0, numCharDaStampare);

		for(int i = s.length() + 1; i <= larghezza; ++i) {
			res.append(" ");
		}

		return res.toString();
	}

	public static String centrata(String s, int larghezza) {
		StringBuffer res = new StringBuffer(larghezza);
		if (larghezza <= s.length()) {
			res.append(s.substring(larghezza));
		} else {
			int spaziPrima = (larghezza - s.length()) / 2;
			int spaziDopo = larghezza - spaziPrima - s.length();

			int i;
			for(i = 1; i <= spaziPrima; ++i) {
				res.append(" ");
			}

			res.append(s);

			for(i = 1; i <= spaziDopo; ++i) {
				res.append(" ");
			}
		}

		return res.toString();
	}

	public static String ripetiChar(char elemento, int larghezza) {
		StringBuffer result = new StringBuffer(larghezza);

		for(int i = 0; i < larghezza; ++i) {
			result.append(elemento);
		}

		return result.toString();
	}

	public static String rigaIsolata(String daIsolare) {
		StringBuffer result = new StringBuffer();
		result.append("\n");
		result.append(daIsolare);
		result.append("\n");
		return result.toString();
	}
}
