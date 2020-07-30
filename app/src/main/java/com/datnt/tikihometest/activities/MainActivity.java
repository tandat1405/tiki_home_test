package com.datnt.tikihometest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.datnt.tikihometest.R;
import com.datnt.tikihometest.adapter.FlashDealAdapter;
import com.datnt.tikihometest.adapter.QuickLinkAdapter;
import com.datnt.tikihometest.api.ApiUtils;
import com.datnt.tikihometest.api.HomeClient;
import com.datnt.tikihometest.model.Banner;
import com.datnt.tikihometest.model.BannerObject;
import com.datnt.tikihometest.dto.Bean;
import com.datnt.tikihometest.model.FlashDeal;
import com.datnt.tikihometest.model.QuickLink;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private HomeClient homeClient = ApiUtils.homeClient();
    private boolean isCalledBanner = false;
    private boolean isCalledQuickLink = false;
    private QuickLinkAdapter quickLinkAdapter;
    private FlashDealAdapter flashDealAdapter;
    private static String ERROR_BANNER = "Failed to load banners!";
    private static String ERROR_QUICKLINK = "Failed to load quick links!";
    private static String ERROR_FLASHDEAL = "Failed to load flash deals!";
    private ContentLoadingProgressBar progressBarBannerQuickLink, progressBarFlashDeal;
    private com.sunzn.banner.library.Banner bannerView;
    private RecyclerView recyclerQuickLink, recyclerFlashDeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarBannerQuickLink = findViewById(R.id.progress_banner_quicklink);
        progressBarFlashDeal = findViewById(R.id.progress_flash_deal);
        bannerView = findViewById(R.id.banner);
        recyclerQuickLink = findViewById(R.id.rv_quick_link);
        recyclerFlashDeal = findViewById(R.id.rv_flash_deal);
        getBanners();
        getQuickLinks();
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isCalledBanner = false;
                isCalledQuickLink = false;
                bannerView.setVisibility(View.GONE);
                recyclerQuickLink.setVisibility(View.GONE);
                recyclerFlashDeal.setVisibility(View.GONE);
                progressBarBannerQuickLink.show();
                getBanners();
                getQuickLinks();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void getBanners() {
        Log.i("Banner", "Start load");
        Call<Banner> call = homeClient.getBanners();
        call.enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                if (response.isSuccessful()) {
                    List<BannerObject> bannerObjects = response.body().getData();
                    List<Bean> beans = new ArrayList<>();
                    for(BannerObject bannerObject : bannerObjects){
                        beans.add(new Bean(bannerObject.getMobileUrl()));
                    }
                    com.sunzn.banner.library.Banner<Bean> bannerView = findViewById(R.id.banner);
                    bannerView.setBannerData(beans);
                    bannerView.setOnItemBindListener(new com.sunzn.banner.library.Banner.OnItemBindListener<Bean>() {
                        @Override
                        public void onItemBind(int i, Bean bean, ImageView imageView) {
                            Glide.with(imageView).load(bean.getUrl()).into(imageView);
                        }
                    });
                    bannerView.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(MainActivity.this, ERROR_BANNER, Toast.LENGTH_SHORT).show();
                }
                Log.i("Banner", "End load");
                isCalledBanner = true;
                hideLoadingBannerAndQuickLink();

            }

            @Override
            public void onFailure(Call<Banner> call, Throwable t) {
                Toast.makeText(MainActivity.this, ERROR_BANNER, Toast.LENGTH_SHORT).show();
                Log.i("Banner", "Start load");
                isCalledBanner = true;
                hideLoadingBannerAndQuickLink();
            }
        });
    }

    public void getQuickLinks(){
        Log.i("Quick Link", "Start load");
        Call<QuickLink> call = homeClient.getQuickLinks();
        call.enqueue(new Callback<QuickLink>() {
            @Override
            public void onResponse(Call<QuickLink> call, Response<QuickLink> response) {
                if (response.isSuccessful()) {
                    RecyclerView recyclerView = findViewById(R.id.rv_quick_link);
                    LinearLayoutManager horizontalLayoutManager
                            = new GridLayoutManager(MainActivity.this,2, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(horizontalLayoutManager);
                    quickLinkAdapter = new QuickLinkAdapter(MainActivity.this, response.body());
                    //quickLinkAdapter.setClickListener(this);
                    recyclerView.setAdapter(quickLinkAdapter);
                    recyclerQuickLink.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(MainActivity.this, ERROR_QUICKLINK, Toast.LENGTH_SHORT).show();
                }
                Log.i("Quick Link", "End load");
                isCalledQuickLink = true;
                hideLoadingBannerAndQuickLink();

            }

            @Override
            public void onFailure(Call<QuickLink> call, Throwable t) {
                Toast.makeText(MainActivity.this, ERROR_QUICKLINK, Toast.LENGTH_SHORT).show();
                Log.i("Quick Link", "End load");
                isCalledQuickLink = true;
                hideLoadingBannerAndQuickLink();

            }
        });
    }

    public void getFlashDeals(){
        Log.i("Flash deal", "Start load");
        Call<FlashDeal> call = homeClient.getFlashDeals();
        call.enqueue(new Callback<FlashDeal>() {
            @Override
            public void onResponse(Call<FlashDeal> call, Response<FlashDeal> response) {
                if (response.isSuccessful()) {
                    RecyclerView recyclerView = findViewById(R.id.rv_flash_deal);
                    LinearLayoutManager horizontalLayoutManager
                            = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(horizontalLayoutManager);
                    flashDealAdapter = new FlashDealAdapter(MainActivity.this, response.body());
                    //quickLinkAdapter.setClickListener(this);
                    recyclerView.setAdapter(flashDealAdapter);
                    recyclerFlashDeal.setVisibility(View.VISIBLE);

                }
                else {
                    Toast.makeText(MainActivity.this, ERROR_FLASHDEAL, Toast.LENGTH_SHORT).show();
                }
                Log.i("Flash Deal", "End load");
                progressBarFlashDeal.hide();

            }

            @Override
            public void onFailure(Call<FlashDeal> call, Throwable t) {
                Toast.makeText(MainActivity.this, ERROR_FLASHDEAL, Toast.LENGTH_SHORT).show();
                Log.i("Flash Deal", "End load");
                progressBarFlashDeal.hide();

            }
        });
    }
    public void hideLoadingBannerAndQuickLink(){
        if(!isCalledBanner || !isCalledQuickLink){
            return;
        }
        progressBarBannerQuickLink.hide();
        progressBarFlashDeal.show();
        getFlashDeals();
    }


}
