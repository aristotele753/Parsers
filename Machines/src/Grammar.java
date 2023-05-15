import java.util.List;
import java.util.ArrayList;

public class Grammar {
    
    //Map<Symbol, Set<Symbol>> FIRSTSet = new HashMap<>();
    private List<Production> productionList = new ArrayList<>();

    public Grammar() {

    }

    public Grammar(List<Production> productionList) {
        for (Production production : productionList)
            this.productionList.add(production);
    }

    public List<Production> getProductionList() {
        return this.productionList;
    }

    public void addProduction(Production production) {
        this.productionList.add(production);
    }

    public Production getProduction(int index) {
        return this.productionList.get(index);
    }

    /*public Set<Symbol> FIRST(Symbol symbol) {
        
        Set<Symbol> FIRST = new HashSet<>();

        for (Production production : getProductionList()) {
            
            if (production.getHead().equals(symbol)) { // se il symbol è in testa, si cerca nella produzione il primo terminale 
                
                for (Symbol bodySymbol : production.getBody()) {
                    
                    if (bodySymbol.getIsTerminal() && !bodySymbol.getIsEpsilon()) { 
                        FIRST.add(bodySymbol);
                        break; // appena si incontra un simbolo terminale diverso da epsilon, si mette nei FIRST e si passa alla prossima produzione, con un break
                    } 
                    // se si giunge a questo blocco di codice, vuol dire che nella produzione ci sono solo non terminali, e bisogna vedere i FIRST di questi
                    if (bodySymbol.)
                    FIRST(bodySymbol);

                }

            }

        }
        
    }*/

    public boolean deriveEpsilon(Symbol nonTerminal) {

        if (nonTerminal.getIsTerminal()) throw new IllegalArgumentException();

        for (int i = 0; i < getProductionList().size(); i++) {

            if (getProductionList().size() == 1)                 // vedo se la produzione ha la forma "nonTerminal -> epsilon"
                return produceEpsilon(getProductionList().get(0).getHead());
        
            Production tempProduction = getProduction(i);        // in caso contrario, significa che la forma "nonTerminal -> Y1, Y2, ..."
            boolean pass = false;

            if (tempProduction.getHead().equals(nonTerminal)) {  // se la testa della produzione è "nonTerminal -> ... "

                for (Symbol symbol : tempProduction.getBody()) { // se nel corpo della produzione i è presente un terminale, non può derivare epsilon
                    if (symbol.getIsTerminal() && !symbol.getIsEpsilon())
                        pass = true;                             // se il simbolo è un terminale, si imposta il flag a true per saltare la produzione i
                }
                if (pass) continue;                              // passa alla produzione i + 1 

                /*
                    Se il continue si è superato, significa che il corpo è costituito SOLO da non terminali che POTREBBERO
                    derivare epsilon, si avrà una produzione nella forma "nonTerminal -> Y1 Y2 ... Yn": nonTerminal
                    deriva epsilon solo se tutte le Yi derivano epsilon; dunque, la procedura si richiama RICORSIVAMENTE
                    finché non si giunge al caso base "Yn -> epsilon". Infatti, se l'ultimo non terminale Yn supera tutti i
                    controlli, significa che tutti i precedenti Yi derivano anch'essi epsilon. 
                */

                for (Symbol symbol : tempProduction.getBody()) { // sono tutti non terminali

                    // caso base, Yn -> epsilon
                    // passo ricorsivo
                    //else return deriveEpsilon(symbol);

                }

            }

        }

        return false;    
    
    }

    private boolean produceEpsilon(Symbol nonTerminal) {

        try {
            if (nonTerminal.getIsTerminal()) throw new IllegalArgumentException();   
        } catch (IllegalArgumentException e) {
            System.out.println("Eccezione in produceEpsilon");
        }

        for (int i = 0; i < getProductionList().size(); i++) {
            Production tempProduction = getProduction(i); 

            // deve essere nel formato: nonTerminal -> eps
            if (tempProduction.getHead().equals(nonTerminal)) { // se la produzione ha "nonTerminal -> ..."
                if (tempProduction.getBody().size() == 1) {     // se il corpo ha un solo elemento...
                    if (tempProduction.getBody().get(0).getIsEpsilon())
                        return true;                            // e quell'elemento è è un terminale epsilon, allora nonTerminale produce true
                    else
                        return false;
                }
            }
        }

        return false;    
    
    }

    //public List<Symbol> FOLLOW() {
    //    
    //}

    @Override
	public String toString() {
		String toString = "";

        for (Production production : getProductionList()) {
            toString += production.toString() + "\n";
        }
        
        return toString;
	}

}
