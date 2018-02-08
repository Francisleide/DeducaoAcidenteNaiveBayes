import java.lang.reflect.Array;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		NaiveBayes nb = new NaiveBayes();
		ArrayList<Acidente> acidentes = new ArrayList<>();
		acidentes = nb.criaColunas("C:\\Users\\fran\\workspace\\NaiveBayesAcidentes\\Treinamentos.txt");
		
		//Atributo Tipo de acidente
		ArrayList<String> tipoAcidente = new ArrayList<>();
		ArrayList<Double> pTipoAcidente = new ArrayList<>();
		for(int i = 0; i < acidentes.size(); i++){
			tipoAcidente.add(acidentes.get(i).getTipoAcidente());
		}	
		pTipoAcidente = nb.calculaPorcentagens(tipoAcidente);
		for(int i = 0; i < pTipoAcidente.size(); i++){
			System.out.println("Tipo de acidente Porcentagem "+i+": "+pTipoAcidente.get(i));
		}
		
		//Atributo Situação
		ArrayList<String> situacao = new ArrayList<>();
		ArrayList<Double> pSituacao = new ArrayList<>();
		for(int i = 0; i < acidentes.size(); i++){
			situacao.add(acidentes.get(i).getSituacao());
		}	
		pSituacao = nb.calculaPorcentagens(situacao);
		for(int i = 0; i < pSituacao.size(); i++){
			System.out.println("Situação Porcentagem "+i+": "+pSituacao.get(i));
		}
		
		//Atributo Condição Técnica
		ArrayList<String> condTecnica = new ArrayList<>();
		ArrayList<Double> pCondTecnica = new ArrayList<>();
		for(int i = 0; i < acidentes.size(); i++){
			condTecnica.add(acidentes.get(i).getCondTecnica());
		}	
		pCondTecnica = nb.calculaPorcentagens(condTecnica);
		for(int i = 0; i < pCondTecnica.size(); i++){
			System.out.println("Condição Técnica "+i+": "+pCondTecnica.get(i));
		}
		
		//Atributo Causa
		ArrayList<String> causa = new ArrayList<>();
		ArrayList<Double> pCausa = new ArrayList<>();
		for(int i = 0; i < acidentes.size(); i++){
			causa.add(acidentes.get(i).getCausa());
		}	
		pCausa = nb.calculaPorcentagens(causa);
		for(int i = 0; i < pCausa.size(); i++){
			System.out.println("Condição Técnica "+i+": "+pCausa.get(i));
		}
		
	}
}
