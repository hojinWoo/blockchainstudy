package blockchain;
import java.util.Date;

public class Block {
	public String hash;		//블록을 식별할 수 있는 데이터들의 집합을 해싱한 결과값
	public String previousHash;	//이 전 블록의 hash 값
	private String data;	//임의의 문자열(블록의 데이터 담는 변수)
	private long timeStamp;	//Unix Tiem(1970/1/1 이후 경과된 초 단위의 시간)
	
	public Block(String data, String previousHash){
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();	//hash 계산	
	}
	
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256(
				previousHash +
				Long.toString(timeStamp) +
				data
				);
		return calculatedhash;
	}
}
