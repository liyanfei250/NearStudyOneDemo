package bean;

/**
 * Created by LYF on 2016/9/2.
 */
public class User extends BaseBean {

    /**
     * userId : 2
     * nickName : 默认的用户昵称2
     * userName : abc2
     * userImg : http://img3.imgtn.bdimg.com/it/u=1309804105,3956020050&fm=21&gp=0.jpg
     */

    private UserinfosBean userinfos;

    public UserinfosBean getUserinfos() {
        return userinfos;
    }

    public void setUserinfos(UserinfosBean userinfos) {
        this.userinfos = userinfos;
    }

    public static class UserinfosBean {
        private String userId;
        private String nickName;
        private String userName;
        private String userImg;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }
    }
}
