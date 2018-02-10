import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class NaiveBayes {

	public ValorPorcentagem calculaPorcentagens(ArrayList<String> coluna){
		ValorPorcentagem vp = new ValorPorcentagem();
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
		vp.setValores(valores);
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
		vp.setPorcentagens(percentValores);
		
		
	/*	for(int i=0; i< totalValores.size(); i++){
			System.out.println("total "+totalValores.get(i));
		}*/
		return vp;
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
	
	//eu quero saber do teste, qauntos (a combinação de dois elementos) tem no arq original
	public void naiveBayes(String nomeArqTeste, ValorPorcentagem vpTipoAcidente, ValorPorcentagem vpSituacao, ValorPorcentagem vpCausa){
		ArrayList<Acidente> acidentes = new ArrayList<>();
		Acidente acidente2 = new Acidente();
		try{
			FileReader meuArq = new FileReader(nomeArqTeste);
			BufferedReader lerArq = new BufferedReader(meuArq);
			FileWriter writer = new FileWriter("c:\\TestePosNaive.csv");
	        writer.append("TipoAcidente");
	        writer.append(',');
	        writer.append("CausaProvavel");
	        writer.append(',');
	        writer.append("SituacaoPista");
	        writer.append(',');
	        writer.append("CondTecnica");
	        writer.append(',');
	        writer.append("Predict");
			
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
		int totalTipoAcidenteCausa=0, totalSituacaoCausa=0, totalCondTecnicaCausa = 0;
		Double probCausa, probTipoAcidente, probSituacao, probCondTecnica;
		Double probAcidenteCausa, probSituacaoCausa, probCondTecnicaCausa, maiorProb=0.0, probTotal;
		
		int totalRegistros = acidentes.size();
		String tipoAcidente, situacao, condTecnica, causa;
		//fazer um for grandao que vai varrer todos elementos classificando!
		
		//qual a causa do primeiro elemento
		for(int z = 0; z<vpCausa.getValores().size(); z++){
			causa = vpCausa.getValores().get(z);
			probCausa = vpCausa.getPorcentagens().get(z);
			for(int i=0; i < acidentes.size(); i++){
				String causa2 =acidentes.get(i).getCausa(); 
				tipoAcidente = acidentes.get(i).getTipoAcidente();
				situacao = acidentes.get(i).getSituacao();
				condTecnica = acidentes.get(i).getCondTecnica();
				//causa = acidentes.get(i).getCausa(); <- não preciso porque preciso fazer com todos e descobrir o melhor!
				
				if(causa.equals(causa2)){
					for(int w=0; w<acidentes.size(); w++){
						if(tipoAcidente.equals(acidentes.get(w).getTipoAcidente())){
							totalTipoAcidenteCausa++;
						}
					}
					
					for(int w=0; w<acidentes.size(); w++){
						if(condTecnica.equals(acidentes.get(w).getCondTecnica())){
							totalCondTecnicaCausa++;
						}
					}
					for(int w=0; w<acidentes.size(); w++){
						if(situacao.equals(acidentes.get(w).getSituacao())){
							totalSituacaoCausa++;
						}
					}
					
					
				}
				probAcidenteCausa = (double)totalTipoAcidenteCausa/(double)totalRegistros;
				probCondTecnicaCausa = (double)totalCondTecnicaCausa/(double)totalRegistros;
				probSituacaoCausa =(double)totalSituacaoCausa/(double)totalRegistros;
				probTotal = probAcidenteCausa * probCondTecnicaCausa * probSituacaoCausa * probCausa;
				if(i==0){
					maiorProb = probTotal;
				}
				if(probTotal > maiorProb){
					maiorProb = probTotal;
					acidente2.setCausa(causa2);
					acidente2.setCondTecnica(condTecnica);
					acidente2.setSituacao(situacao);
					acidente2.setTipoAcidente(tipoAcidente);
					
					
				
			}
			
		}
			
			try{
				OutputStream os = (OutputStream)new FileOutputStream("c:\\TestePosNaive.csv");
			     String encoding = "UTF8";
			     OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
			     BufferedWriter bw = new BufferedWriter(osw);
			     bw.write(acidente2.getTipoAcidente());
			     bw.write(",");
			     bw.write(acidente2.getCausa());
			     bw.write(",");
			     bw.write(acidente2.getSituacao());
			     bw.write(",");
			     bw.write(acidente2.getCondTecnica());
			     bw.write(",");
			     bw.write(acidente2.getPredict());
			     
				} catch(IOException e){
			         e.printStackTrace();
			    } 
			
			
			
				
			
				
				//calcular P(causa|situacao)*p(situacao)/p(causa)
				
				
					//
				//	System.out.println("nome da primeira causa"+ causa);
					//verificar quantas vezes ocorre a combinação Causa|Situação
				
		}	
				
				
				
			}
	
}

