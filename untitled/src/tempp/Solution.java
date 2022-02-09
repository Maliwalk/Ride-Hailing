package tempp;

import java.util.List;

public class Solution {


    static TweetService tweetService=new TweetServiceImpl();
    public static void main(String[] args)
    {
        Long uid=1L;

        registerUser("ABC");
        registerUser("XYZ");

        followUser(1L,2L);
        postTweet(1L,"Hey");
        postTweet(2L,"Twwet2");
        System.out.println(getNewsFeed(1L,1).toString());





    }

    public static Long registerUser(String firstName){

        return tweetService.registerUser(firstName);
    }


    public static Boolean followUser(Long followeruserId, Long followeeuserId){
//        TweetService tweetService=new TweetServiceImpl();
         return tweetService.followUser(followeruserId,followeeuserId);
    }

    public static Boolean unfollowUser(Long userId, Long followeeuserId){
//        TweetService tweetService=new TweetServiceImpl();
        return tweetService.unFollowUser(userId,followeeuserId);
    }

    public static Long postTweet(Long userId, String message){

        return tweetService.postTweet(userId,message);
    }

    public static List<Tweet> getNewsFeed(Long userId, int n){

        return tweetService.getNewFeed(userId,n);
    }



//    public static void temp(){
//        int[][] arr= {{1,2},{3,4}};
//        System.out.println(arr.toString());
//    }

}
