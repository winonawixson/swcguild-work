package com.swcguild.sitelab.dao;

import java.util.Random;

/**
 *
 * @author apprentice
 */
public class LuckySevensStats {

    private int bankroll, rollCount, rollCountAtMax, maxBankroll;

    public LuckySevensStats(int bankroll) {
        this.bankroll = bankroll;
        rollCount = 0;
        rollCountAtMax = 0;
        maxBankroll = 0;
    }

    public int getBankroll() {
        return bankroll;
    }

    public void setBankroll(int bankroll) {
        this.bankroll = bankroll;
    }

    public int getRollCount() {
        return rollCount;
    }

    public int getRollCountAtMax() {
        return rollCountAtMax;
    }

    public int getMaxBankroll() {
        return maxBankroll;
    }

    public void getStats() {
        Random r = new Random();
        while (bankroll >= 1) {

            int d1, d2, diceTotal;

            d1 = r.nextInt(6) + 1;
            d2 = r.nextInt(6) + 1;
            rollCount++;
            diceTotal = d1 + d2;

            if (diceTotal == 7) {

                bankroll = bankroll + 4;

            } else {

                bankroll = bankroll - 1;
            }
            if (bankroll > maxBankroll) {
                maxBankroll = bankroll;
                rollCountAtMax = rollCount;
            }
        }
    }
}
