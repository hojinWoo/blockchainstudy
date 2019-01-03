package blockchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;


public class NoobChain {
	public static ArrayList<Block> blockChain = new ArrayList<Block>();
	public static void main(String[] args) {
		//sample test
		/*Block genesisBlock = new Block("Hi im the first block", "0");
		System.out.println("Hash for block 1 : " + genesisBlock.hash);
		
		Block secondBlock = new Block("Yo im the second block",genesisBlock.hash);
		System.out.println("Hash for block 2 : " + secondBlock.hash);
		
		Block thirdBlock = new Block("Hey im the third block",secondBlock.hash);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);*/

		//ArrayList�� ��� �߰� (���� ���� hash�� ������ �ֱ�)
		blockChain.add(new Block("Hi im the first block", "0"));
		blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).hash));
		blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).hash));
		
		String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println(blockChainJson);
	}
	
	//Integrity Ȯ�� - ���� üũ
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockChain.size(); i++) {
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i-1);
			// hash���� ������ Ȯ��
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			// �� �� ��ϰ��� ������ ��ȿ���� Ȯ��
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
	
//	��� ��尡 �ùٸ� ���ü���� �����ϰ� �ִٴ� ���� �����ϱ� ���ؼ� ���� �˰���(pow)�� ���
}
