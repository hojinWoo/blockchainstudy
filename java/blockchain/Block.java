package blockchain;
import java.util.Date;

/**
 * 참고 사이트
 * https://medium.com/caulink/자바로-블록체인-만들기-part-1-9ac8bb0423d
 * @author dnghwls7
 *
 */
public class Block {
	public String hash;		//블록을 식별할 수 있는 데이터들의 집합을 해싱한 결과값
	public String previousHash;	//이 전 블록의 hash 값
	private String data;	//임의의 문자열(블록의 데이터 담는 변수)
	private long timeStamp;	//Unix Tiem(1970/1/1 이후 경과된 초 단위의 시간)
	private int nonce;		//hash 계산 시 블록 채굴을 위해 사용되는 값
	
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
				Integer.toString(nonce) +
				data
				);
		return calculatedhash;
	}
	
//	PoW 알고리즘을 사용한 Mining
//	hash값의 추적은 hash값 첫 부분의 0 개수를 추적하는 과정이며 이를 위해서는 계속해서 다른 변수값을 블록에 넣으면서 조건을 만족할 때 까지 hashing하는 것이다. 
//	0의 개수가 많아질수록 추적은 어려워지며 ‘난이도(difficulty)가 높다’
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); // difficulty 크기 만큼의 char형 배열 선언, 0의 개수	
		while(!hash.substring( 0, difficulty).equals(target)) { // 0의 개수가 같을 때 까지
			nonce ++; // nonce를 증가
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
