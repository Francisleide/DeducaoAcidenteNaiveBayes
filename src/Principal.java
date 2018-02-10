import java.lang.reflect.Array;
import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		NaiveBayes nb = new NaiveBayes();
		ArrayList<Acidente> acidentes = new ArrayList<>();
		acidentes = nb.criaColunas("C:\\Users\\fran\\workspace\\NaiveBayesAcidentes\\Treinamentos.txt");
		//ValorPorcentagem vp = new ValorPorcentagem();
		
		
		//Atributo Tipo de acidente
		ArrayList<String> tipoAcidente = new ArrayList<>();
		ArrayList<Double> pTipoAcidente = new ArrayList<>();
		ValorPorcentagem vpTipoAcidente = new ValorPorcentagem(); 
		for(int i = 0; i < acidentes.size(); i++){
			tipoAcidente.add(acidentes.get(i).getTipoAcidente());
		}	
		vpTipoAcidente = nb.calculaPorcentagens(tipoAcidente);
		for(int i = 0; i< vpTipoAcidente.getPorcentagens().size(); i++){
			System.out.println("Tipo de acidente Porcentagem "+i+": "+vpTipoAcidente.getValores().get(i));
			System.out.println("Tipo de acidente Porcentagem "+i+": "+vpTipoAcidente.getPorcentagens().get(i));
			
		}
		
		System.out.println("------------------------------------------------------------------------");
		//Atributo Situação
		ArrayList<String> situacao = new ArrayList<>();
		ArrayList<Double> pSituacao = new ArrayList<>();
		ValorPorcentagem vpSituacao = new ValorPorcentagem();
		for(int i = 0; i < acidentes.size(); i++){
			situacao.add(acidentes.get(i).getSituacao());
		}	
		vpSituacao = nb.calculaPorcentagens(situacao);
		for(int i = 0; i < vpSituacao.getPorcentagens().size(); i++){
			System.out.println("Situação Porcentagem "+i+": "+vpSituacao.getPorcentagens().get(i));
			System.out.println("Situação Porcentagem "+i+": "+vpSituacao.getValores().get(i));
		}
		System.out.println("------------------------------------------------------------------------");
		
		//Atributo Condição Técnica
		ArrayList<String> condTecnica = new ArrayList<>();
		ArrayList<Double> pCondTecnica = new ArrayList<>();
		ValorPorcentagem vpCondTencica = new ValorPorcentagem();
		for(int i = 0; i < acidentes.size(); i++){
			condTecnica.add(acidentes.get(i).getCondTecnica());
		}	
		vpCondTencica = nb.calculaPorcentagens(condTecnica);
		for(int i = 0; i < vpCondTencica.getPorcentagens().size(); i++){
			System.out.println("Condição Técnica "+i+": "+vpCondTencica.getValores().get(i));
		}
		
		//Atributo Causa
		ArrayList<String> causa = new ArrayList<>();
		ArrayList<Double> pCausa = new ArrayList<>();
		ValorPorcentagem vpCausa = new ValorPorcentagem();
		for(int i = 0; i < acidentes.size(); i++){
			causa.add(acidentes.get(i).getCausa());
		}	
		vpCausa = nb.calculaPorcentagens(causa);
		for(int i = 0; i < vpCausa.getPorcentagens().size(); i++){
			System.out.println("Condição Técnica "+i+": "+vpCausa.getPorcentagens().get(i));
			System.out.println("Condição Técnica "+i+": "+vpCausa.getValores().get(i));
		}
		
	}
}
