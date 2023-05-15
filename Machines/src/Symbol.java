public class Symbol {
    
    private String symbol;
    private boolean isTerminal; // se "false" è un non terminale, se "true" è un terminale
    private boolean isEpsilon;  // se "true" è la stringa vuota

    public Symbol(String symbol) { // devo verificare se sia terminale o non terminale

        if (symbol.isEmpty()) throw new IllegalArgumentException();
        this.setSymbol(symbol);

        if (symbol.toLowerCase().equals(symbol)) { // un simbolo per essere terminale deve interamente essere lowercase
            this.setIsTerminal(true);   // è un terminale?
            if (symbol.equals("\"")) {
                this.setIsEpsilon(true); // inoltre, è epsilon?
            }
        } else {
            this.setIsTerminal(false);  // è un non terminale
        }

    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setIsTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    public boolean getIsTerminal() {
        return this.isTerminal;
    }

    public void setIsEpsilon(boolean isEpsilon) {
        this.isEpsilon = isEpsilon;
    }

    public boolean getIsEpsilon() {
        return this.isEpsilon;
    }

    @Override
	public String toString() {
		return "" + this.symbol;
	}

}
