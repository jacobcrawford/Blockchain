package Impl;

import Interfaces.Block;
import Interfaces.HashingAlgorithm;
import Interfaces.Transactions;

import java.math.BigInteger;

public class StandardBlock implements Block {


    private BigInteger nonce;
    private int hardnessParameter;
    private BigInteger previousHash;
    private int transactionLimit;
    private Transactions transactions;
    private int blockNumber;
    private HashingAlgorithm hashingAlgorithm;

    public StandardBlock(BigInteger nonce,
                         int hardnessParameter,
                         BigInteger previousHash,
                         int transactionLimit,
                         Transactions transactions,
                         int blockNumber, HashingAlgorithm hashingAlgorithm) {
        this.nonce = nonce;
        this.hardnessParameter = hardnessParameter;
        this.previousHash = previousHash;
        this.transactionLimit = transactionLimit;
        this.transactions = transactions;
        this.blockNumber=blockNumber;
        this.hashingAlgorithm = hashingAlgorithm;
    }

    @Override
    public Transactions getTransactions() {
        return transactions;
    }

    @Override
    public int getBlockNumber() {
        return blockNumber;
    }

    @Override
    public BigInteger getNonce() {
        return nonce;
    }

    @Override
    public int getHardnessParameter() {
        return hardnessParameter;
    }

    @Override
    public BigInteger getPreviousHash() {
        return previousHash;
    }

    @Override
    public int getTransactionLimit() {
        return transactionLimit;
    }

    @Override
    public BigInteger hash() {
        return new BigInteger(String.valueOf(hashingAlgorithm.hash(
                previousHash
                        + transactions.hashTransactions().toString()
                        + nonce)));
    }
}
