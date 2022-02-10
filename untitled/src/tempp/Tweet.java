package tempp;

class Tweet {
    Long tweetId;

     public Long getEpochTime() {
         return epochTime;
     }

     public Tweet setEpochTime(Long epochTime) {
         this.epochTime = epochTime;
         return this;
     }

     Long epochTime;

     public Long getTweetId() {
         return tweetId;
     }

     @Override
     public String toString() {
         return tweetMessage;
     }

     public Tweet setTweetId(Long tweetId) {
         this.tweetId = tweetId;
         return this;
     }

     public String getTweetMessage() {
         return tweetMessage;
     }

     public Tweet setTweetMessage(String tweetMessage) {
         this.tweetMessage = tweetMessage;
         return this;
     }

     String tweetMessage;
}
