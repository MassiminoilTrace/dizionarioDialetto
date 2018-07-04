package main;

import distanzaParole.VettoreParola;

public class ProvaDistanzParoleMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VettoreParola v1 = new VettoreParola("ciao");
		VettoreParola v2 = new VettoreParola("cial");
		VettoreParola v3 = new VettoreParola("robe");
		System.out.println(v1.distanzaA(v2));
		System.out.println(v2.distanzaA(v3));
		System.out.println(v1.distanzaA(v3));
	}

}
