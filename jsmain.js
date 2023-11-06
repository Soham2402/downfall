const SHA256 = require("crypto-js/sha256");

class Block {
    constructor(index, data, previousHash = " ") {
        var today = new Date();
        this.index = index;
        this.timestamp = (today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate()).toString();
        this.data = data;
        this.previousHash = previousHash;
        this.hash = this.calculateHash();
    }

    calculateHash() {
        return SHA256(this.index + this.previousHash + JSON.stringify(this.data) + this.timestamp).toString();
    }
}

class Blockchain {
    constructor() {
        this.chain = [this.createGenesisBlock()];
    }
    createGenesisBlock() {
        return new Block(0, "23/02/01", "testdata", "0");
    }
    getLatestBlock() {
        return this.chain[this.chain.length - 1];
    }

    addBlock(newBlock) {
        newBlock.previousHash = this.getLatestBlock().hash;
        newBlock.hash = newBlock.calculateHash();
        this.chain.push(newBlock);
    }

    isChainValid() {
        for (let i = 1; i < this.chain.length; i++) {
            var currentBlock = this.chain[i];
            var previousBlock = this.chain[i - 1];

            // Check if current block is valid
            if (currentBlock.hash !== currentBlock.calculateHash()) {
                return false;
            }
            if (currentBlock.previousHash !== previousBlock.hash) {
                return false;
            }
        }
        return true;
    }
}

var sohamCoin = new Blockchain();
const blockOne = new Block(1, { "amount": 4 });
const blockTwo = new Block(2, { "amount": 5 });
sohamCoin.addBlock(blockOne);
sohamCoin.addBlock(blockTwo);
console.log(sohamCoin);
console.log("Is chain valid? " + sohamCoin.isChainValid());
sohamCoin.chain[1].data = { "amount": 40 };
console.log("Is chain valid after modifying data? " + sohamCoin.isChainValid());
