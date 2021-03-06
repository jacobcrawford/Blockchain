package Impl;

import Impl.Hashing.SHA256;
import Interfaces.*;


import java.math.BigInteger;
/*
* This class is meant to implement a node, that holds the whole blockchain.
* It uses the SHA256 hashing algorithm
* */
public class FullNode implements Node {
    private HashingAlgorithm hashingAlgorithm = new SHA256();
    private BlockChain blockChain;

    public FullNode(BlockChain blockChain) {
        this.blockChain=blockChain;
    }

    @Override
    public void mine(BigInteger previousBlockHash, Transactions transactions) {
        //Set the hardness parameter
        //TODO make hardness parameter change
        int hardness = 20;
        //Set the hardness value, by getting the bitsize of the hashing algorithm and shifting right by the hardness parameter.
        BigInteger hardValue = BigInteger.valueOf(2).pow(hashingAlgorithm.getBitSize()).shiftRight(hardness);

        //set nonce
        int nonce = 0;
        BigInteger hash;
        do{
            hash = new BigInteger(String.valueOf(hashingAlgorithm.hash(
                    previousBlockHash.toString()
                            + transactions.hashTransactions().toString()
                            + nonce)));
            nonce++;
        } while(hash.compareTo(hardValue)>0);
        blockChain.addBlock(new StandardBlock(new BigInteger(Integer.toString(nonce)),hardness,previousBlockHash,10,new ArrayListTransactions(),blockChain.getBlockNumber()+1,hashingAlgorithm ));
    }

    @Override
    public boolean validateTransactions(Transactions transactions) {
        return false;
    }

    @Override
    public Transactions getPendingTransactions() {
        return null;
    }


    @Override
    public boolean validateBlock(Block incomingBlock) {
        return false;
    }

    @Override
    public void removeTransaction(Transaction transaction) {
    }

    @Override
    public BlockChain getBlockChain() {
        return blockChain;
    }

    @Override
    public BigInteger hashBlock(Block block) {
        return hashingAlgorithm.hash(block.toString());
    }
}
