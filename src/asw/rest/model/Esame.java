package asw.rest.model;

import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Esame {
	private int id;
	private int id_docente;
	private String corso;
	private String aula;
	private String data;
		
	public Esame (int id,int id_docente,String corso,String aula,String data){
		this.corso=corso;
		this.aula=aula;
		this.data =data;
		this.id=id;
		this.id_docente = id_docente;
	}
	public Esame(){}
	
	public void setCorso(String corso){
	      this.corso = corso;
	}
	public void setAula(String aula){
	      this.aula = aula;
	}
	public void setData(int giorno, int mese, int anno ){
	      this.data = ""+giorno+"/"+mese+"/"+anno+"";
	}
	public void setData(String data) {
		this.data = data;	
	}
	public void setId(int id ){
	      this.id = id;
	}
	public void setIdDocente(int id ){
	      this.id_docente = id;
	}
	public String getCorso(){
		return this.corso;
	}
	public String getAula(){
		return this.aula;
	}
	public String getData(){
		return this.data;
	}
	public int getId(){
		return this.id;
	}
	public int getIdDocente(){
		return this.id_docente;
	}
	public String toString(){
		return "Appello del "+this.data+" di "+this.corso+" presso l'aula "+this.aula+".";
	}
	
	public boolean verificaDataUtile(){
		boolean dataUtile = true;
		
		Calendar cal = new GregorianCalendar();
		  int giorno = cal.get(Calendar.DAY_OF_MONTH);
		  int mese = cal.get(Calendar.MONTH) + 1;
		  int anno = cal.get(Calendar.YEAR);
		  String ge=this.data.substring(0, 2);
		  String me=this.data.substring(3, 5);
		  String ae=this.data.substring(6, 10);
		  int giornoEsame = Integer.parseInt(ge); 
		  int meseEsame = Integer.parseInt(me); 
		  int annoEsame = Integer.parseInt(ae);
		  if((annoEsame<anno)
				  || (annoEsame==anno && meseEsame<mese)
				  || (annoEsame==anno && meseEsame<mese && giornoEsame<=giorno))
			  dataUtile=false;
		  
		  
		return dataUtile;
	}
	
	
	
}