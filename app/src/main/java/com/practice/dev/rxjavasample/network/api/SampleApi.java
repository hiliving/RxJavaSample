package com.practice.dev.rxjavasample.network.api;
import com.practice.dev.rxjavasample.model.SampleBean;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by HY on 2017/4/13.
 */

public interface SampleApi {
    @GET("search")
    Observable<List<SampleBean>> search(@Query("q") String query);
}
