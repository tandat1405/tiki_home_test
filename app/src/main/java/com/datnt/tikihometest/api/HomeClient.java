package com.datnt.tikihometest.api;

import com.datnt.tikihometest.model.Banner;
import com.datnt.tikihometest.model.FlashDeal;
import com.datnt.tikihometest.model.QuickLink;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeClient {
    @GET("v2/home/banners/v2")
    Call<Banner> getBanners();

    @GET("shopping/v2/widgets/quick_link")
    Call<QuickLink> getQuickLinks();

    @GET("v2/widget/deals/hot")
    Call<FlashDeal> getFlashDeals();
}
