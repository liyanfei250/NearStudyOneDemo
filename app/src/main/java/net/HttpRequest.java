package net;


import android.os.AsyncTask;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import bean.User;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Action1;
import util.UserInfoKeeper;

public class HttpRequest {
    // LeanCloud
    public static final String HOST = "http://103.21.118.30:8080/NewsService/";
    public static final String FILE_HOST = "";

    private static final String APP_ID_NAME = "X-LC-Id";
    private static final String API_KEY_NAME = "X-LC-Key";
    public static final String SESSION_TOKEN_KEY = "X-LC-Session";

    private static final String APP_ID_VALUE = "iaEH7ObIA4sPY8RSs3VCVXBg-gzGzoHsz";
    private static final String API_KEY_VALUE = "dXfhXIVyeWMN2czJkd4ehwzs";


    private static Retrofit retrofit;
    private static OkHttpClient httpClient;

    public static OkHttpClient getHttpClient() {
        return httpClient;
    }

    static {
        // OkHttpClient
        httpClient = new OkHttpClient();

        // 统一添加的Header
        httpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader(APP_ID_NAME, APP_ID_VALUE)
                        .addHeader(API_KEY_NAME, API_KEY_VALUE)
                        .addHeader(SESSION_TOKEN_KEY, UserInfoKeeper.getToken())
                        .build();
                return chain.proceed(request);
            }
        });

        // log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);

        // Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create()) // gson
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // rxjava 响应式编程
                .callbackExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .build();
    }

    public interface BmobService {
        // 注册,登录用户
        @POST("MainClass")
        Observable<User> login(
                @Query("requestType") String requestType,
                @Query("username") String username,
                @Query("password") String password);

        /*// 手机获取验证码
        @POST("/1/requestSmsCode")
        Observable<Object> requestSmsCode(
                @Body Map<String, Object> mobilePhoneNumber);

        // 手机验证注册
        @POST("/1/users")
        Observable<User> userRegist(
                @Body User user);

        // 忘记密码重置
        @PUT("/1/resetPasswordBySmsCode/{smsCode}")
        Observable<Object> resetPasswordBySmsCode(
                @Path("smsCode") String smsCode,
                @Body Map<String, Object> password);

        // 旧密码修改新密码
        @POST(" /1/updateUserPassword/{objectId}")
        Observable<User> updateUserPassword(
                @Path("smsCode") String smsCode,
                @Body UpdatePswRequest updatePswRequest);

        // 根据昵称搜索用户
        @GET("/1/classes/_User")
        Observable<ListResponse<User>> getUserByName(
                @Query("limit") int perPageCount,
                @Query("skip") int page,
                @Query("where") String where);

        // 动态图收藏用户列表
        @GET("/1/classes/_User")
        Observable<ListResponse<User>> getGifFavUsers(
                @Query("where") String where);

        // 获取用户详情
        @GET("/1/users/{objectId}")
        Observable<User> getCurrentUser(
                @Path("objectId") String userId);

        // 获取用户详情
        @GET("/1/users/{objectId}")
        Observable<User> getUserById(
                @Path("objectId") String userId);

        // 修改用户详情(注意, 提交什么参数修改什么参数)
        @PUT("/1/users/{objectId}")
        Observable<BaseEntity> updateUserById(
                @Path("objectId") String userId,
                @Body Map<String, Object> updateInfo);

        // 上传图片接口
        @POST("/1/files/{fileName}")
        Observable<FileUploadResponse> fileUpload(
                @Path("fileName") String fileName,
                @Body RequestBody image);

        // 查询app更新信息
        @GET("/1/classes/AppUpdateInfo")
        Observable<ListResponse<AppUpdateInfo>> getAppUpdateInfo();

        @Streaming
        @GET
        Observable<ResponseBody> downloadFile(@Url String fileUrl);

        // 提交意见反馈
        @POST("/1/classes/FeedBack")
        Observable<BaseEntity> addFeedBack(
                @Body FeedBack feedBack);

        ////////
        // 云函数
        @GET("/1/functions/spider")
        Observable<BaseEntity> cloudSpider();

        @GET("/1/functions/add")
        Observable<BaseEntity> cloudAdd();

        ////////

        // 添加设计资源
        @POST("/1/classes/DesignRes")
        Observable<BaseEntity> addDesignRes(
                @Body DesignRes entity);

        // 修改设计资源
        @PUT("/1/classes/DesignRes/{objectId}")
        Observable<BaseEntity> updateDesignRes(
                @Path("objectId") String objectId,
                @Body Map<String, Object> updateInfo);

        // 查询设计资源
        @GET("/1/classes/DesignRes")
        Observable<ListResponse<DesignRes>> getDesignRes(
                @Query("limit") int perPageCount,
                @Query("skip") int page,
                @Query("where") String where,
                @Query("include") String include);*/

    }

    public static BmobService getApiService() {
        BmobService service = retrofit.create(BmobService.class);
        return service;
    }

    /**
     * 查询设计资源
     */
    /*public static Observable<ListResponse<DesignRes>> getDesignRes(int page) {
        BmobService service = getApiService();
        String where = "{}";
        return service.getDesignRes(CommonConstants.COUNT_OF_PAGE,
                (page - 1) * CommonConstants.COUNT_OF_PAGE, where, null);
    }*/

    /**
     * 查询设计资源
     *
     * @param page
     * @param name 搜索名称
     */
    /*public static Observable<ListResponse<DesignRes>> getDesignRes(int page, String name) {
        BmobService service = getApiService();
        String whereName = "{}";
        if (!TextUtils.isEmpty(name)) {
            whereName = "{\"name\":{\"$regex\":\".*" + name + ".*\"}}";
        }
        String where = whereName;
        return service.getDesignRes(CommonConstants.COUNT_OF_PAGE,
                (page - 1) * CommonConstants.COUNT_OF_PAGE, where, null);
    }*/


    //////////////////////////////

    /**
     * 登录用户
     *
     * @param username 用户名
     * @param password 密码
     */
    public static Observable<User> login(String logORreg, String username, String password) {
        BmobService service = getApiService();
        return service.login(logORreg,username, password)
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        // 保存登录用户数据以及token信息
                        UserInfoKeeper.setCurrentUser(user);
                        // 保存自动登录使用的信息
                        UserInfoKeeper.saveLoginData(user.getUserinfos().getUserId(), user.getUserinfos().getUserId());
                    }
                });
    }

    /**
     * 使用token自动登录
     *
     * @param loginData size为2的数组, 第一个为当前用户id, 第二个为当前用户token
     */
    /*public static Observable<User> loginByToken(final String[] loginData) {
        BmobService service = getApiService();
        // 这种自动登录方法其实是使用token去再次获取当前账号数据
        return service.getUserById(loginData[0])
                .doOnNext(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        // TODO 获取用户信息接口不会返回token
                        user.setSessionToken(loginData[1]);
                        // 保存登录用户数据以及token信息
                        UserInfoKeeper.setCurrentUser(user);
                        // 保存自动登录使用的信息
                        UserInfoKeeper.saveLoginData(user.getObjectId(), user.getSessionToken());
                    }
                });
    }
*/
    /**
     * 根据昵称模糊搜索用户,分页(默认每页数量为CommonConstants.COUNT_OF_PAGE)
     *
     * @param searchKey 搜索昵称
     * @param page      页数,从1开始
     */
   /* public static Observable<ListResponse<User>> getUserByName(String searchKey, int page) {
        BmobService service = getApiService();
        String where = "{\"username\":{\"$regex\":\"" + searchKey + ".*\"}}";
        return service.getUserByName(CommonConstants.COUNT_OF_PAGE,
                (page - 1) * CommonConstants.COUNT_OF_PAGE, where);
    }*/

    /**
     * 上传图片
     *
     * @param call    上传成功回调
     * @param context
     * @param uri     图片uri
     * @param reqW    上传图片需要压缩的宽度
     * @param reqH    上传图片需要压缩的高度
     * @param call
     */
    /*public static void fileUpload(final Context context, Uri uri, int reqW, int reqH, final Subscriber<FileUploadResponse> call) {
        final BmobService service = getApiService();
        final String filename = "avatar_" + System.currentTimeMillis() + ".jpg";

        // 先从本地获取图片,利用Glide压缩图片后获取byte[]
        Glide.with(context).load(uri).asBitmap().toBytes().into(
                new SimpleTarget<byte[]>(reqW, reqH) {
                    @Override
                    public void onResourceReady(final byte[] resource, GlideAnimation<? super byte[]> glideAnimation) {
                        // 上传图片
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), resource);

                        Observable<FileUploadResponse> observable = service.fileUpload(filename, requestBody);
                        ObservableDecorator.decorate(observable)
                                .subscribe(call);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        call.onError(new Throwable("图片解析失败"));
                    }
                });
    }*/

}

