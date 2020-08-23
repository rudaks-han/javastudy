package rudaks.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RewardAccumulator {

    private String customerId;
    private double purchaseTotal;
    private int totalRewardPoints;
    private int currentRewardPoints;
    private int daysFromLastPurchase;

    //private double rewardPoints;

    public RewardAccumulator(Purchase purchase) {
        this.customerId = purchase.getCustomerId();
        this.purchaseTotal = purchase.getPrice() * (double) purchase.getQuantity();
        this.currentRewardPoints = (int) purchaseTotal;
        this.totalRewardPoints = (int) purchaseTotal;
        //this.rewardPoints =  purchaseTotal;
        //this.currentRewardPoints = rewardPoints;
        //this.totalRewardPoints = rewardPoints;
    }

    /*public RewardAccumulator(String customerId, int purchaseTotal, int rewardPoints) {
        this.customerId = customerId;
        this.purchaseTotal = purchaseTotal;
        this.currentRewardPoints = rewardPoints;
        this.totalRewardPoints = rewardPoints;
    }*/

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(double purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public int getCurrentRewardPoints() {
        return currentRewardPoints;
    }

    public void setCurrentRewardPoints(int currentRewardPoints) {
        this.currentRewardPoints = currentRewardPoints;
    }

    public int getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(int totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }

    public void addRewardPoints(double previousTotalPoints) {
        this.totalRewardPoints += previousTotalPoints;
    }

    public int getDaysFromLastPurchase() {
        return daysFromLastPurchase;
    }

    public void setDaysFromLastPurchase(int daysFromLastPurchase) {
        this.daysFromLastPurchase = daysFromLastPurchase;
    }


    /*
    public static Builder builder(Purchase purchase){return new Builder(purchase);}

    public static final class Builder {
        private String customerId;
        private double purchaseTotal;
        private int daysFromLastPurchase;
        private int rewardPoints;

        private Builder(Purchase purchase){
            this.customerId = purchase.getCustomerId();
            this.purchaseTotal = purchase.getPrice() * (double) purchase.getQuantity();
            this.rewardPoints = (int) purchaseTotal;
        }


        public RewardAccumulator build(){
            return new RewardAccumulator(customerId, purchaseTotal, rewardPoints);
        }

    }*/
}