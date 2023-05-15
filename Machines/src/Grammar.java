import java.util.List;
import java.util.ArrayList;

public class Grammar {
    
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

    @Override
	public String toString() {
		String toString = "";

        for (Production production : this.getProductionList()) {
            toString += production.toString() + "\n";
        }
        
        return toString;
	}

    public List<Symbol> FIRST(Symbol symbol) {
        
        List<Symbol> listFIRST = new ArrayList<>();

        return listFIRST;
    
    }

    //public List<Symbol> FOLLOW() {
    //    
    //}


}
