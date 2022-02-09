package tempp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserObject {
    Long userId;
    String firstName;

    public Long getUserId() {
        return userId;
    }

    public UserObject setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserObject setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public List<Tweet> getTweetList() {
        return tweetList;
    }

    public UserObject setTweetList(List<Tweet> tweetList) {
        this.tweetList = tweetList;
        return this;
    }

    public Map<Long, Boolean> getFollowees() {
        return followees;
    }

    public UserObject setFollowees(Map<Long, Boolean> followers) {
        this.followees = followers;
        return this;
    }

    List<Tweet> tweetList = new ArrayList<>();
    Map<Long,Boolean> followees =new HashMap<>();

}
