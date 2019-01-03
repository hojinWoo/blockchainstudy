package blockchain;
import java.util.Date;

/**
 * ���� ����Ʈ
 * https://medium.com/caulink/�ڹٷ�-���ü��-�����-part-1-9ac8bb0423d
 * @author dnghwls7
 *
 */
public class Block {
	public String hash;		//����� �ĺ��� �� �ִ� �����͵��� ������ �ؽ��� �����
	public String previousHash;	//�� �� ����� hash ��
	private String data;	//������ ���ڿ�(����� ������ ��� ����)
	private long timeStamp;	//Unix Tiem(1970/1/1 ���� ����� �� ������ �ð�)
	private int nonce;		//hash ��� �� ��� ä���� ���� ���Ǵ� ��
	
	public Block(String data, String previousHash){
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();	//hash ���	
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
	
//	PoW �˰����� ����� Mining
//	hash���� ������ hash�� ù �κ��� 0 ������ �����ϴ� �����̸� �̸� ���ؼ��� ����ؼ� �ٸ� �������� ��Ͽ� �����鼭 ������ ������ �� ���� hashing�ϴ� ���̴�. 
//	0�� ������ ���������� ������ ��������� �����̵�(difficulty)�� ���١�
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); // difficulty ũ�� ��ŭ�� char�� �迭 ����, 0�� ����	
		while(!hash.substring( 0, difficulty).equals(target)) { // 0�� ������ ���� �� ����
			nonce ++; // nonce�� ����
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
