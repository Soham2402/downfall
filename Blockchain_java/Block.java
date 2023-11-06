import java.security.MessageDigest; 

class Block {
    private int index;
    private String timestamp;
    private String data;
    private String previousHash;
    private String hash;
  	
    public Block(int index, String d, String data, String previousBlock) {
        this.index = index;
        this.timestamp = d;
        this.data = data;
        this.previousHash = previousBlock;
        this.hash = calculateHash();
    }

    public String getHash(){
        return hash;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public String getData(){
        return data;
    }

    public String getPreviousHash(){
        return previousHash;
    }


    public void setData(String data){
        this.data = data;
    }
    

     String calculateHash(){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String toBeHashed = index+timestamp+data+previousHash;
            byte[] hashed =md.digest(toBeHashed.getBytes());
            // System.out.println(hashed);
            StringBuffer hashedString = new StringBuffer();

            for(int i = 0; i<hashed.length;i++){
                String h = Integer.toHexString(0xff & hashed[i]);
                if (h.length() == 1){
                    hashedString.append("0");
                }
                hashedString.append((h));
            }
           
            return hashedString.toString();
            
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
        
    }

    public static void main(String[] args) {
        Block block = new Block(1,"1.4566","soham","000000000");
        System.out.println(block);

        
    }
}


