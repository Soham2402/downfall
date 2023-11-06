import java.util.ArrayList;

public class Blockchain {
    private ArrayList <Block> chain;
    public Blockchain(){
        this.chain =   new ArrayList<Block>();
        Block genesis = new Block(0, String.valueOf(System.currentTimeMillis()),"Data", "00000");
        this.chain.add(genesis);
     }

     public void addBlock(int id, String timestamp,String data){
        Block previousBlock = this.chain.get(this.chain.size() -1);
        String hash = previousBlock.getHash();
        Block newBlock = new Block(1, timestamp, data, hash);
        this.chain.add(newBlock);
     }

     public Block getBlock(int index){
        return this.chain.get(index);
     }

     public boolean isValid(){
        int chainLen = this.chain.size();
        for(int i=1;i<chainLen;i++){
            Block currentBlock = this.chain.get(i);
            Block previousBlock = this.chain.get(i-1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
               return false;
           }
           if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
               return false;
           }
        }
        return true;
     }


     public static void main(String[] args) {
        Blockchain sohamcoin = new Blockchain();
        sohamcoin.addBlock(1,"current","data");
        sohamcoin.addBlock(2,"Next","data");
 
        System.out.println("Blockchain is valid: " + sohamcoin.isValid());
 
        // Tamper with the blockchain
        sohamcoin.getBlock(1).setData("Modi");

      //   // blockchain.blocks.get(1).setData("Modified Transaction");
        System.out.println("Blockchain is valid: " + sohamcoin.isValid());
     }
    
}

