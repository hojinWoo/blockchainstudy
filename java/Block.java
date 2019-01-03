package blockchain;
import java.util.Date;

public class Block {
	public String hash;		//����� �ĺ��� �� �ִ� �����͵��� ������ �ؽ��� �����
	public String previousHash;	//�� �� ����� hash ��
	private String data;	//������ ���ڿ�(����� ������ ��� ����)
	private long timeStamp;	//Unix Tiem(1970/1/1 ���� ����� �� ������ �ð�)
	
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
				data
				);
		return calculatedhash;
	}
}
