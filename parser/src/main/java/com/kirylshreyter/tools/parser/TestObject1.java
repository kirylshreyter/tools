package com.kirylshreyter.tools.parser;

public class TestObject1 {
	private String herausgeber;
	private String nummer;
	private Double deckung;
	private String waehrung;
	private String string;
	private boolean bool;

	/**
	 * @return the herausgeber
	 */
	public String getHerausgeber() {
		return herausgeber;
	}

	/**
	 * @param herausgeber
	 *            the herausgeber to set
	 */
	public void setHerausgeber(String herausgeber) {
		this.herausgeber = herausgeber;
	}

	/**
	 * @return the nummer
	 */
	public String getNummer() {
		return nummer;
	}

	/**
	 * @param nummer
	 *            the nummer to set
	 */
	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	/**
	 * @return the deckung
	 */
	public Double getDeckung() {
		return deckung;
	}

	/**
	 * @param deckung
	 *            the deckung to set
	 */
	public void setDeckung(Double deckung) {
		this.deckung = deckung;
	}

	/**
	 * @return the waehrung
	 */
	public String getWaehrung() {
		return waehrung;
	}

	/**
	 * @param waehrung
	 *            the waehrung to set
	 */
	public void setWaehrung(String waehrung) {
		this.waehrung = waehrung;
	}

	/**
	 * @return the string
	 */
	public String getString() {
		return string;
	}

	/**
	 * @param string
	 *            the string to set
	 */
	public void setString(String string) {
		this.string = string;
	}

	/**
	 * @return the bool
	 */
	public boolean isBool() {
		return bool;
	}

	/**
	 * @param bool
	 *            the bool to set
	 */
	public void setBool(boolean bool) {
		this.bool = bool;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestObject1 [herausgeber=" + herausgeber + ", nummer=" + nummer + ", deckung=" + deckung + ", waehrung="
				+ waehrung + ", string=" + string + ", bool=" + bool + "]";
	}
}
