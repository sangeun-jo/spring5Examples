package survey;

import java.util.List;


// 설문 응답 결과 
public class AnsweredData {
	private List<String> reponses; //응답 내용
	private Respondent res;  //응답자 정보 
	
	public List<String> getResponses(){
		return reponses; 
	}
	
	public Respondent getRes() {
		return res; 
	}
	
	public void setRes(Respondent res) {
		this.res = res;
	}
	
	public void setResponses(List<String> responses) {
		this.reponses = responses; 
	}
}
