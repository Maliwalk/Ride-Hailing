package tempp;

import java.util.List;

public class TweetServiceImpl implements TweetService {
    DataSource dataSource ;

    TweetServiceImpl(){
         dataSource = new DataSource();
    }
    @Override
    public Long postTweet(Long userId, String message) {
        return dataSource.addTweet(userId, message);
    }


    @Override
    public Boolean followUser(Long followerId, Long followweId) {

//        DataSource dataSource = new DataSource();

        return dataSource.addFollower(followerId, followweId);
    }

    @Override
    public Boolean unFollowUser(Long userId,Long followweId) {
        return dataSource.removeFollower(userId, followweId);

    }

    @Override
    public List<Tweet> getNewFeed(Long userId, int n) {
        return dataSource.getNewsFeed(userId, n);

    }

    @Override
    public Long registerUser(String fName) {
//        DataSource dataSource = new DataSource();

        return dataSource.registerUser(fName);
    }
}
