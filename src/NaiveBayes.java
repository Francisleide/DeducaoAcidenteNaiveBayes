import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class NaiveBayes {
	public ArrayList<Double> calculaPorcentagens(ArrayList<String> coluna){
		ArrayList<String> valores = new ArrayList<String>();
		int contem=0;
		ArrayList<Integer> totalValores = new ArrayList<Integer>();
		ArrayList<Double> percentValores = new ArrayList<Double>();
		valores.add(coluna.get(0));
		//totalValores.add(0, 0.0);
		for(int i=0; i<coluna.size(); i++){
			for(int j=0; j<valores.size(); j++){
				if(coluna.get(i).contains(valores.get(j))){
					//System.out.println("contm na posição "+i+" "+valores.get(j));
					contem++;
				//	System.out.println(contem);
				}
			}
			if(contem==0){
				valores.add(coluna.get(i));
				//System.out.println(coluna.get(i));
			}
			contem=0;
		}
		int cont=0;
		for(int i=0; i<valores.size(); i++){
			for(int j=0; j<coluna.size(); j++){
				if(valores.get(i).contains(coluna.get(j))){
					cont++;
				}
			}
			totalValores.add(cont);
			//System.out.println("cont: "+cont);
			//System.out.println("coluna size: "+coluna.size());
			Double x = (double) ((double)cont/(double)coluna.size());
			percentValores.add(x);
			cont=0;
		}
		
	/*	for(int i=0; i< totalValores.size(); i++){
			System.out.println("total "+totalValores.get(i));
		}*/
		return percentValores;
	}
	
	public ArrayList<Acidente> criaColunas(String nomeArq){
		ArrayList<Acidente> acidentes = new ArrayList<>();
		try{
			FileReader meuArq = new FileReader(nomeArq);
			BufferedReader lerArq = new BufferedReader(meuArq);
			String conteudo = lerArq.readLine();
			while(conteudo !=null){
				String objeto[] = new String[4];
				objeto = conteudo.split(",");
				
				Acidente acidente = new Acidente();
				
				acidente.setTipoAcidente(objeto[0]);
				acidente.setCausa(objeto[1]);
				acidente.setSituacao(objeto[2]);;
				acidente.setCondTecnica(objeto[3]);
				acidentes.add(acidente);
			
			conteudo = lerArq.readLine();
		}
		meuArq.close();
		
		}catch (Exception e){
			System.out.println("Erro na abertura do arquivo: "+ e.getMessage());
		}
		return acidentes;
	}
}

