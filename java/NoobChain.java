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

		//ArrayList에 블록 추가 (이전 값의 hash값 가지고 있기)
		blockChain.add(new Block("Hi im the first block", "0"));
		blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).hash));
		blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).hash));
		
		String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
		System.out.println(blockChainJson);
	}
	
	//Integrity 확인 - 위조 체크
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockChain.size(); i++) {
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i-1);
			// hash값이 옳은지 확인
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			// 이 전 블록과의 연결이 유효한지 확인
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
	
//	모든 노드가 올바른 블록체인을 공유하고 있다는 것을 입증하기 위해서 합의 알고리즘(pow)을 사용
}
