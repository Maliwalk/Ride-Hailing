package tempp;

import java.util.*;

public class DataSource {

    //    Map<Long, Map<Long, Boolean>> userFollowersMap = new HashMap<>();
    Map<Long, UserObject> userObjectMap = new HashMap<>();
    Long userAutoIncrementId = 1L;
    Long twitterAutoIncrementId = 1L;
    Long epochAutoIncrementId = 1L;



    public Map<Long, UserObject> getUserObjectMap() {
        return userObjectMap;
    }

    public DataSource setUserObjectMap(Map<Long, UserObject> userObjectMap) {
        this.userObjectMap = userObjectMap;
        return this;
    }

    public Long registerUser(String fName) {
        UserObject userObject = new UserObject();
        userObject.setFirstName(fName);
        userObject.setUserId(userAutoIncrementId);
        userObjectMap.put(userAutoIncrementId++, userObject);
        return userObject.userId;
    }

    public Boolean addFollower(Long userId, Long followweId) {


        if (userObjectMap.containsKey(userId) && userObjectMap.containsKey(followweId)) {
//           if ( userObjectMap.get(followweId).getFollowers().containsKey(followerId)){

//           }else{
            userObjectMap.get(userId).getFollowees().put(followweId, true);
        } else {
            //handle exception fo user not registerd
        }
        return true;
    }

    public Boolean removeFollower(Long userId, Long followweId) {


        if (userObjectMap.containsKey(userId) && userObjectMap.containsKey(followweId)) {

            userObjectMap.get(userId).getFollowees().put(followweId, false);
        } else {
            //handle exception fo user not registerd
        }
        return true;
    }

    public Long addTweet(Long userId, String message) {


        if (userObjectMap.containsKey(userId)) {
            Tweet tweet= new Tweet();
            tweet.setTweetId(twitterAutoIncrementId++);
            tweet.setTweetMessage(message);
            tweet.setEpochTime(epochAutoIncrementId++);

            userObjectMap.get(userId).getTweetList().add(tweet);
            return tweet.getTweetId();
        } else {
            //handle exception fo user not registerd
        }
        return null;

    }

    public List<Tweet> getNewsFeed(Long userId, int n) {

        Map<Long, Boolean> map = userObjectMap.get(userId).getFollowees();
        List<Tweet> list = new ArrayList<>();

        for (Map.Entry<Long, Boolean> entry : map.entrySet()) {
//            System.out.println("Key = " + entry.getKey() +
//                    ", Value = " + entry.getValue());

            if(entry.getValue()){
//                List<Tweet> tweetlist = userObjectMap.get(userId).getTweetList();
//                for(Log)
                list.addAll(userObjectMap.get(entry.getKey()).getTweetList());
            }



        }

        Collections.sort(list,new SortbyDate());
        return list.subList(0,n);





    }

    class SortbyDate implements Comparator<Tweet> {

        // Method
        // Sorting in ascending order of name
        public int compare(Tweet a, Tweet b)
        {

            return  a.epochTime.compareTo(b.epochTime) * (-1);
        }
    }



}
