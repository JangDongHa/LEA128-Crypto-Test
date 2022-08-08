package com.example.cryptospring.util.crypto.symm;


import com.example.cryptospring.util.crypto.BlockCipher;
import com.example.cryptospring.util.crypto.engine.LeaEngine;
import com.example.cryptospring.util.crypto.mode.CBCMode;
import com.example.cryptospring.util.crypto.mode.CTRMode;

public class LEA {
	private LEA() {
		throw new AssertionError();
	}

	public static final BlockCipher getEngine() {
		return new LeaEngine();
	}

	public static final class CBC extends CBCMode {
		public CBC() {
			super(getEngine());
		}
	}

	public static final class CTR extends CTRMode {
		public CTR() {
			super(getEngine());
		}
	}

}
