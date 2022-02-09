package tempp;

import java.util.List;

public interface TweetService {

    public Long postTweet(Long userId, String message);
    public Boolean followUser(Long followerId,Long followweId);
    public Boolean unFollowUser(Long userId,Long followweId);
    public List<Tweet> getNewFeed(Long userId, int n);
    public Long registerUser(String fName);

}
