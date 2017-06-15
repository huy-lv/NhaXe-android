package com.bich.hp.nhaxe.ConnectInternet;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;



public class RetrofitClient {
    static String BASEURL = "https://quanlynhaxe-huylv177.c9users.io";
    private static Retrofit retrofit = null;

    public static APIInterface getAPIInterface() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(httpClient.build())
                    .build();
        }
        return retrofit.create(APIInterface.class);
    }

    public interface APIInterface {
        @GET("DiemDi.php")
        Call<DiaDiemResponse> getDiaDiem(@Query("ham") String diemDiOrDiemDen, @Query("diemdi") String diemdi);

        //        @GET("DiemDi.php")
//        Call<ResponseBody> getDiaDiem1(@Query("ham") String diemDiOrDiemDen, @Query("diemdi")String diemdi);
        @GET("lotrinh.php")
        Call<LoTrinhResponse> getLoTrinh();

        @GET("phancong.php")
        Call<PhanCongResponse> getPhanCong();

        @FormUrlEncoded
        @POST("vexe.php")
        Call<BaseResponse> addVexe(@Field("malotrinh") int malotrinh,
                                   @Field("sdt") String sdt,
                                   @Field("tongtien") String tongtien,
                                   @Field("tenkh") String tenkh,
                                   @Field("giokh") String giokh,
                                   @Field("ngaykh") String ngaykh,
                                   @Field("maghe") String maghe,
                                   @Field("tenghe") String tenghe);

        @GET("getVexeByPhone.php")
        Call<VexeResponse> getVeXeByPhone(@Query("sdt")String sdt);

        @GET("deleteVexeById.php")
        Call<BaseResponse> deleteVexeById(@Query("mave")int mave);

        @FormUrlEncoded
        @POST("dangnhap.php")
        Call<LoginResponse> dangnhap(@Field("sdt")String sdt, @Field("matkhau") String matkhau);

        @GET("getGheByMaTuyenXe.php")
        Call<GheResponse> getGheByMaTuyenXe(@Query("matuyenxe") String matuyenxe);

        @FormUrlEncoded
        @POST("setGhe.php")
        Call<BaseResponse> setGheDaChon(@Field("maghe")String maghe, @Field("tenkh")String tenkh);
    }
}